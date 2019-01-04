package com.microservice.backend.schedule;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microservice.backend.common.utils.UUIDUtils;
import com.microservice.backend.entity.Data;
import com.microservice.backend.entity.Gateway;
import com.microservice.backend.entity.Sensor;
import com.microservice.backend.service.DataService;
import com.microservice.backend.service.GatewayService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    GatewayService gatewayService;

    @Autowired
    DataService dataService;

    @Autowired
    private RedisTemplate<String,Data> redisTemplate;


    @Scheduled(fixedDelay = 60 * 1000L)
    public void sendData() {
        List<Gateway> gateways = gatewayService.findAll();
        HashMap map = new HashMap();
//        Gateway gateway = gateways.get(0);
        List<Sensor> sensors = new ArrayList<>();
        long now = System.currentTimeMillis();
        for (Gateway gateway:gateways){
            List<Sensor> tmp = gateway.getSensors();
            sensors.addAll(tmp);
        }
        Random random =new Random();
        ArrayList datas = new ArrayList();
        for (Sensor sensor : sensors){
            HashMap tmp = new HashMap();
            Float ran = random.nextFloat();
            tmp.put("data",ran);
            tmp.put("time",now);
            tmp.put(("sensor_id"),sensor.getId());
            tmp.put("gateway_id",sensor.getGateway().getId());
            datas.add(tmp);
        }

        map.put("datas",datas);

        // Use this builder to construct a Gson instance when you need to set configuration options other than the default.
        GsonBuilder gsonMapBuilder = new GsonBuilder();

        Gson gsonObject = gsonMapBuilder.create();

        String json =gsonObject.toJson(map);
        String url = "http://127.0.0.1:8080/api/data/http";
        try{
            String res = this.post(url,json);
//            System.out.println(res);
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    //定时任务，从redis中取出数据，并存入数据库
    @Scheduled(fixedDelay = 60 * 3 * 1000L)
    public void saveData(){
        try{
            ListOperations<String,Data> operations = redisTemplate.opsForList();
            Data data = null;
            while((data = operations.leftPop("sensors")) != null){
                System.out.println(data);
                dataService.insert(data);
            }

        }catch (Exception e){
            System.out.print(e.toString());

        }
    }

}
