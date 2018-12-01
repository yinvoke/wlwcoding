package com.microservice.backend.controller;


import com.microservice.backend.entity.GatewayException;
import com.microservice.backend.service.GatewayExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/gatewayException")
public class GatewayExceptionController extends BaseController{
    @Autowired
    GatewayExceptionService gatewayExceptionService = null;

    @RequestMapping(path="",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public HashMap getAllGatewayException(){
        List<GatewayException> gatewayExceptions = new ArrayList<>();
        HashMap map = new HashMap();
        try {
            gatewayExceptions = gatewayExceptionService.findall();
        }catch (Exception e){
            map = this.setResponse("success","",gatewayExceptions);
        }

        map = this.setResponse("success","",gatewayExceptions);
        return  map;
    }
}
