package com.microservice.backend.controller;


import com.microservice.backend.entity.GatewayException;
import com.microservice.backend.service.GatewayExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/gatewayException")
public class GatewayExceptionController extends BaseController{
    @Autowired
    GatewayExceptionService gatewayExceptionService;

    @RequestMapping(value="",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public HashMap getAllGatewayException(){
        List<GatewayException> gatewayExceptions = new ArrayList<GatewayException>();
        HashMap map = new HashMap();
        try {
            gatewayExceptions = gatewayExceptionService.findAll();
        }catch (Exception e){
            map = this.setResponse("errror","db error",null);
            return map;
        }

        map = this.setResponse("success",null,gatewayExceptions);
        return  map;
    }

    @RequestMapping(path = "/{timetamp}",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap getDataGatewayException(@PathVariable("timetamp") String timetamp){
        List<GatewayException> gatewayExceptions = new ArrayList<GatewayException>();
        String[] s = timetamp.split("@");
        long dateFromTamp = Long.parseLong(s[0])*1000;
        long dateToTamp = Long.parseLong(s[1])*1000;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap map = new HashMap();
        try {
            gatewayExceptions = gatewayExceptionService.findByTime(simpleDateFormat.format(dateFromTamp), simpleDateFormat.format(dateToTamp),30);
        }catch (Exception e){
            map = this.setResponse("error","db error",null);
            return map;
        }

        map = this.setResponse("success",null,gatewayExceptions);
        return  map;
    }
}
