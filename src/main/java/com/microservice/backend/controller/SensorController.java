package com.microservice.backend.controller;

import com.microservice.backend.common.utils.ErrorResponseUtil;
import com.microservice.backend.entity.Gateway;
import com.microservice.backend.entity.Sensor;
import com.microservice.backend.service.GatewayService;
import com.microservice.backend.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {
    @Autowired
    SensorService sensorService;

    @Autowired
    GatewayService gatewayService;

    @RequestMapping(path="",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addSensor(HttpServletRequest request){
        String description =  request.getParameter("description");
        String location = request.getParameter("location");
        String factory = request.getParameter("factory");
        String install_time_str = request.getParameter("install_time");
        String gate_id_str = request.getParameter("gate_id");
        long in_time = Long.parseLong(install_time_str);
        Date  install_time= new Date(in_time);
        long gate_id = Long.parseLong(gate_id_str);
        Gateway gateway = gatewayService.findById(gate_id);

        Sensor sensor = new Sensor(description,location,factory,install_time,install_time,install_time,1L,gateway);
        try{
            sensorService.insert(sensor);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ErrorResponseUtil.setResponse("400", e.getMessage());
        }
        return ErrorResponseUtil.setResponse("200", "Add sensor success");
    }

    @RequestMapping(path="/{id}",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getSensorById(@PathVariable("id") long id){
        Sensor sensor = new Sensor();
        try{
            sensor = sensorService.findById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ErrorResponseUtil.setResponse("400", e.getMessage());
        }
        if(sensor == null){
            return ErrorResponseUtil.setResponse("400", "get sensor error");
        }
        Gateway gateway = sensor.getGateway();
        System.out.println(gateway);
        return  ErrorResponseUtil.setResponse("200", "Gateway:"+sensor.getDescription());
    }
}
