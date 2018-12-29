package com.microservice.backend.controller;

import com.microservice.backend.common.utils.UUIDUtils;
import com.microservice.backend.entity.*;
import com.microservice.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/data")
public class DataController extends BaseController {

    @Autowired
    DataService dataService;

    @Autowired
    SensorExceptionService sensorExceptionService;

    @Autowired
    SensorService sensorService;

    @Autowired
    GatewayService gatewayService;

    @Autowired
    GatewayExceptionService gatewayExceptionService;
    //redis缓存
    @Autowired
    private RedisTemplate<String,Data> redisTemplate;

    @RequestMapping(path="/{id}",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap addDatas(@PathVariable("id") long id){
        Sensor sensor = sensorService.findById(id);
        HashMap map = null;
        if (sensor == null){
            map = this.setResponse("error","no this sensor",null);
            return map;
        }
        List<Data> datas = new ArrayList<>();
        long now = System.currentTimeMillis();
        Random random =new Random();
        float ran = 0;
        for (int i=0;i<30;i++){
            ran = random.nextFloat();
            now += i*200;
            Date d = new Date(now);
            Data tmp = new Data(new Float(ran),d,1L,sensor);
            System.out.print(tmp.getData());
            datas.add(tmp);
        }
        try{
            dataService.inserts(datas);
        }catch (Exception e){
            System.out.print(e.toString());
            map = this.setResponse("error","db error",null);
            return map;
        }
        map = this.setResponse("success","",datas);
        return map;
    }

    @RequestMapping(path="/http",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap receive(@RequestBody Map jsonParam){
        List jsonDatas = (ArrayList)jsonParam.get("datas");
        List<Data> sensorDatas = new ArrayList<>();
        ListOperations<String,Data> operations = redisTemplate.opsForList(); //redis
        for(int i=0;i< jsonDatas.size();i++){
            HashMap hashMap = (HashMap)jsonDatas.get(i);
            Data tmp = this.formateData(Float.parseFloat(hashMap.get("data").toString()),Long.parseLong(hashMap.get("sensor_id").toString()),Long.parseLong(hashMap.get("time").toString()));

            //模拟产生传感器异常
            if(Float.parseFloat(hashMap.get("data").toString()) < 0.6){
                this.createSensorException(Long.parseLong(hashMap.get("sensor_id").toString()));
            }

            //模拟产生网关异常
            if(Float.parseFloat(hashMap.get("data").toString()) < 0.25){
                this.createGatewayException(Long.parseLong(hashMap.get("gateway_id").toString()));
            }
            operations.rightPush("sensors",tmp);  //加入缓存
            sensorDatas.add(tmp);
        }
        HashMap map = new HashMap();
        try{
//            String key = UUIDUtils.getUUID();
//            ListOperations operations = redisTemplate.opsForList();
//            operations.rightPush("sensors",sensorDatas);
            dataService.inserts(sensorDatas);
        }catch (Exception e){
            System.out.print(e.toString());
            map = this.setResponse("error","db error",null);
            return map;
        }
        map = this.setResponse("success","",sensorDatas);
        return map;
    }

    private Data formateData(Float sensorData,Long sensor_id,Long time){
        Sensor sensor = sensorService.findById(sensor_id);
        Date date = new Date(time);
        Data data = new Data(sensorData,date,1L,sensor);
        return  data;
    }

    private void createGatewayException(long gateway_id){
        Gateway gateway = gatewayService.findById(gateway_id);
        String mesage[] = {"未接收到数据","连接异常","网络异常","数据异常"};
        Date date = new Date();
        Random random = new Random();
        int index = random.nextInt(4);
        GatewayException gatewayException = new GatewayException(mesage[index],date,1L,gateway);
        try{
            gatewayExceptionService.insert(gatewayException);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void createSensorException(Long sensor_id){
        Sensor sensor = sensorService.findById(sensor_id);
        String mesage[] = {"未接收到数据","连接异常","网络异常","数据异常"};
        Date date = new Date();
        Random random = new Random();
        int index = random.nextInt(4);
        SensorException sensorException = new SensorException(mesage[index],date,1L,sensor);
        try{
            sensorExceptionService.insert(sensorException);
        }catch (Exception e){
            System.out.print(e.toString());
        }

    }
}

