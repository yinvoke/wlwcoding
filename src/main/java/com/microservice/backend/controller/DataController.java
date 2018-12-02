package com.microservice.backend.controller;

import com.microservice.backend.entity.Data;
import com.microservice.backend.entity.Sensor;
import com.microservice.backend.service.DataService;
import com.microservice.backend.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/data")
public class DataController extends BaseController {

    @Autowired
    DataService dataService;

    @Autowired
    SensorService sensorService;

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
}
