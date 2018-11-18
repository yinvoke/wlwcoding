package com.microservice.backend.controller;

import com.microservice.backend.entity.Gateway;
import com.microservice.backend.entity.Sensor;
import com.microservice.backend.entity.SensorClassify;
import com.microservice.backend.service.GatewayService;
import com.microservice.backend.service.SensorClassifyService;
import com.microservice.backend.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api/sensor")
public class SensorController extends BaseController{
    @Autowired
    SensorService sensorService;

    @Autowired
    GatewayService gatewayService;

    @Autowired
    SensorClassifyService sensorClassifyService;

    @RequestMapping(path="",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap addSensor(HttpServletRequest request){
        String description =  request.getParameter("description");
        String location = request.getParameter("location");
        String factory = request.getParameter("factory");
        String install_time_str = request.getParameter("install_time");
        long in_time = Long.parseLong(install_time_str);
        Date  install_time= new Date(in_time);
        String gate_id_str = request.getParameter("gate_id");
        long gate_id = Long.parseLong(gate_id_str);
        String classify_id_str = request.getParameter("classify_id");
        long classify_id = Long.parseLong(classify_id_str);
        String maintenance_time_str = request.getParameter("maintenance_time");
        Date maintenance_time = new Date(Long.parseLong(maintenance_time_str));
        String produce_date_str = request.getParameter("produce_date");
        Date produce_date = new Date(Long.parseLong(produce_date_str));

        Gateway gateway = gatewayService.findById(gate_id);
        SensorClassify classify = sensorClassifyService.findById(classify_id);

        Sensor sensor = new Sensor(description,location,factory,install_time,produce_date,maintenance_time,1L,gateway,classify);

        HashMap map = new HashMap();
        try{
            sensorService.insert(sensor);
        }catch (Exception e){
            System.out.println(e.getMessage());
            map = this.setResponse("error","db error",null);
            return map;
        }
        map = this.setResponse("success","",sensor);
        return map;
    }

    @RequestMapping(path="/{id}",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap getSensorById(@PathVariable("id") long id){
        Sensor sensor = new Sensor();
        HashMap map = new HashMap();
        try{
            sensor = sensorService.findById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            map = this.setResponse("error","db error",null);
            return map;
        }
        if(sensor == null){
            map = this.setResponse("error","db error",null);
            return map;
        }

        map = this.setResponse("success","",sensor);
        return map;
    }

}
