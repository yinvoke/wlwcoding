package com.microservice.backend.controller;

import com.microservice.backend.entity.SensorException;
import com.microservice.backend.service.SensorExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/sensorException")
public class SensorExceptionController extends BaseController{
    @Autowired
    SensorExceptionService sensorExceptionService = null;

    @RequestMapping(value="",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public HashMap getAllSensorException(){
        List<SensorException> sensorExceptions = new ArrayList<SensorException>();
        HashMap map = new HashMap();
        try {
            sensorExceptions = sensorExceptionService.findAll();
        }catch (Exception e){
            map = this.setResponse("error","db error",null);
            return map;
        }

        map = this.setResponse("success",null,sensorExceptions);
        return  map;
    }

    @RequestMapping(path = "/{timetamp}",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap getAllSensorException(@PathVariable("timetamp") String timetamp){
        List<SensorException> sensorExceptions = new ArrayList<SensorException>();
        String[] s = timetamp.split("@");
        long dateFromTamp = Long.parseLong(s[0])*1000;
        long dateToTamp = Long.parseLong(s[1])*1000;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap map = new HashMap();
        try {
            sensorExceptions = sensorExceptionService.findByTime(simpleDateFormat.format(dateFromTamp), simpleDateFormat.format(dateToTamp));
        }catch (Exception e){
            map = this.setResponse("error","db error",null);
            return map;
        }

        map = this.setResponse("success",null,sensorExceptions);
        return  map;
    }
}
