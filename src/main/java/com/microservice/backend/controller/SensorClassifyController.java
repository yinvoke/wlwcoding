package com.microservice.backend.controller;

import com.microservice.backend.entity.SensorClassify;
import com.microservice.backend.service.SensorClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/classify")
public class SensorClassifyController extends BaseController {
    @Autowired
    SensorClassifyService sensorClassifyService;

    @RequestMapping(path="",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public HashMap add(HttpServletRequest request){
        String name = request.getParameter("name");
        SensorClassify sensorClassify = new SensorClassify(name,1L);
        HashMap map = new HashMap();
        try{
            sensorClassifyService.insert(sensorClassify);
        }catch (Exception e){
            map = this.setResponse("error","db error",null);
        }

        map = this.setResponse("success","",sensorClassify);

        return map;
    }

    @RequestMapping(path="",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public HashMap getAllClassify(){
        List<SensorClassify> sensorClassifies = new ArrayList<>();
        HashMap map = new HashMap();
        try {
            sensorClassifies = sensorClassifyService.findAll();
        }catch (Exception e){
            map = this.setResponse("error","db error",null);
        }

        map = this.setResponse("success","",sensorClassifies);
        int length = sensorClassifies.size();
        for(int i=0;i<length;i++){
            sensorClassifies.get(i).setSensors(null);
        }
        return  map;
    }

    @RequestMapping(path="/{id}",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public HashMap getById(@PathVariable("id") long id){
        SensorClassify sensorClassify = new SensorClassify();
        HashMap map = new HashMap();
        try{
            sensorClassify = sensorClassifyService.findById(id);
        }catch (Exception e){
            map = this.setResponse("error","db error","");
            return map;
        }

        if(sensorClassify.getId() == null){
            map = this.setResponse("error","id error or sensorclassify don't exist","");
            return map;
        }

        map = this.setResponse("success","",sensorClassify);
        return  map;
    }

    @RequestMapping(path="/sensors/{id}",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public HashMap getSensorsById(@PathVariable("id") long id){
        SensorClassify sensorClassify = new SensorClassify();
        HashMap map = new HashMap();
        try{
            sensorClassify = sensorClassifyService.findById(id);
        }catch (Exception e){
            map = this.setResponse("error","db error","");
            return map;
        }

        if(sensorClassify.getId() == null){
            map = this.setResponse("error","id error or sensorclassify don't exist","");
            return map;
        }

        map = this.setResponse("success","",sensorClassify.getSensors());
        return  map;
    }



}
