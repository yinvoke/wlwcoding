package com.microservice.backend.controller;

import com.microservice.backend.entity.Data;
import com.microservice.backend.entity.Gateway;
import com.microservice.backend.entity.Sensor;
import com.microservice.backend.entity.SensorClassify;
import com.microservice.backend.service.GatewayService;
import com.microservice.backend.service.SensorClassifyService;
import com.microservice.backend.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/sensor")
public class SensorController extends BaseController{
    @Autowired
    SensorService sensorService;

    @Autowired
    GatewayService gatewayService;

    @Autowired
    SensorClassifyService sensorClassifyService;

    /*
        格式化日期格式，依赖注入类
     */
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

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
        String produce_date_str = request.getParameter("produce_date");
        Gateway gateway = gatewayService.findById(gate_id);
        SensorClassify classify = sensorClassifyService.findById(classify_id);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  //前端传的时间格式
        Date maintenance_time = new Date();
        Date produce_date = new Date();
        HashMap map = new HashMap();
        try{
            maintenance_time = format.parse(maintenance_time_str);
            produce_date = format.parse(produce_date_str);
        }catch (Exception e){
            map = this.setResponse("error","date format error",null);
            return map;
        }
        Sensor sensor = new Sensor(description,location,factory,install_time,produce_date,maintenance_time,1L,gateway,classify);

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

    @RequestMapping(path="",method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap updateSensor(HttpServletRequest request,Sensor sensor){
        long id = Long.parseLong(request.getParameter("id"));
        Sensor _sensor = sensorService.findById(id);
        HashMap map = new HashMap();
        if (_sensor == null){
            map = this.setResponse("error","id error,not this sensor",null);
            return map;
        }
        long classify_id = Long.parseLong(request.getParameter("classify_id"));
        SensorClassify classify = sensorClassifyService.findById(classify_id);
        sensor.setSensorClassify(classify);

        long gate_id = Long.parseLong(request.getParameter("gate_id"));
        Gateway gateway = gatewayService.findById(id);
        sensor.setGateway(gateway);
        sensor.setStatus(1L);
        try{
            sensorService.update(sensor);
        }catch (Exception e){
            map = this.setResponse("error","db error",null);
            return map;
        }
        map = this.setResponse("success","",sensor);
        return map;
    }

    @RequestMapping(path="/{id}",method = RequestMethod.DELETE,produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap deleteSensorById(@PathVariable("id") long id){
        Sensor sensor = sensorService.findById(id);
        HashMap map = new HashMap();
        if (sensor == null){
            map = this.setResponse("error","id error,not this sensor",null);
            return map;
        }
        sensor.setStatus(0L);
        try{
            sensorService.update(sensor);
        }catch (Exception e){
            map = this.setResponse("error","db error",null);
            return map;
        }
        map = this.setResponse("success","",null);
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

    @RequestMapping(path="data/{id}",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap getSensorDataById(@PathVariable("id") long id){
        Sensor sensor = sensorService.findById(id);
        HashMap map = null;
        if (sensor == null){
            map = this.setResponse("error","id error,no this sensor",null);
            return map;
        }

        List<Data> datas = sensor.getDatas();
        map = this.setResponse("success","",datas);
        return map;
    }

}
