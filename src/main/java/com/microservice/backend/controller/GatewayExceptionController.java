package com.microservice.backend.controller;


import com.microservice.backend.entity.GatewayException;
import com.microservice.backend.service.GatewayExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/gatewayException")
public class GatewayExceptionController extends BaseController{
    @Autowired
    GatewayExceptionService gatewayExceptionService;

    @RequestMapping(value="",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
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

    @RequestMapping(path = "/{dataFrom}{dataTo}",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap getDataGatewayException(@PathVariable("dataFrom") String dataFrom, @PathVariable("dataTo") String dataTo){
        List<GatewayException> gatewayExceptions = new ArrayList<GatewayException>();
        HashMap map = new HashMap();
        try {
            gatewayExceptions = gatewayExceptionService.findByTime(dataFrom, dataTo);
        }catch (Exception e){
            map = this.setResponse("error","db error",null);
            return map;
        }

        map = this.setResponse("success",null,gatewayExceptions);
        return  map;
    }
}