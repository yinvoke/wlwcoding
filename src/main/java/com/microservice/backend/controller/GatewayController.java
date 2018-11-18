package com.microservice.backend.controller;

import com.microservice.backend.common.utils.ErrorResponseUtil;
import com.microservice.backend.entity.Gateway;
import com.microservice.backend.entity.Sensor;
import com.microservice.backend.entity.SensorClassify;
import com.microservice.backend.service.GatewayService;
import com.microservice.backend.service.SensorClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/gateway")
public class GatewayController extends BaseController{
    @Autowired
    GatewayService gatewayService;

    @Autowired
    SensorClassifyService sensorClassifyService;

    @RequestMapping(value="" ,method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap addGateway (HttpServletRequest request){
        String ip = request.getParameter("ip");
        String port = request.getParameter("port");
        String description =  request.getParameter("description");
        String location = request.getParameter("location");
        Gateway gateway = new Gateway(ip,port,description,location,1L);
        HashMap map = new HashMap();
        try{
            gatewayService.inset(gateway);
        }catch (Exception e){
            System.out.println(e.getMessage());
            map = this.setResponse("error","db error",null);
            return map;
        }
        map = this.setResponse("success",null,gateway);
        return map;
    }

    @RequestMapping(path="/{id}",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap getGatewayById(@PathVariable("id") long id){
        Gateway gateway = new Gateway();
        HashMap map = new HashMap();
        try{
            gateway = gatewayService.findById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            map = this.setResponse("error","db error",null);
            return map;
        }
        if (gateway == null){
            map = this.setResponse("error","db error",null);
            return map;
        }
        List<Sensor> sensros = gateway.getSensors();
        System.out.println(sensros);

        map = this.setResponse("success","",gateway);
        return map;
    }

    @RequestMapping(value="" ,method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    public HashMap getAllGateway(){
        List<Gateway> gateways = new ArrayList<Gateway>();
        HashMap map = new HashMap();
        try{
            gateways = gatewayService.findAll();
        }catch (Exception e){
            map = this.setResponse("error","db error",null);
            return map;
        }
        map = this.setResponse("success",null,gateways);
        return map;
    }

    @RequestMapping(value="/sensors/{id}" ,method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    public HashMap getAllSensors(@PathVariable("id") long id){
        Gateway gateway = new Gateway();
        HashMap map = new HashMap();
        try{
            gateway = gatewayService.findById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            map = this.setResponse("error","db error",null);
            return map;
        }
        if (gateway == null){
            map = this.setResponse("error","db error",null);
            return map;
        }
        List<Sensor> sensros = gateway.getSensors();
        map = this.setResponse("success","",sensros);
        return map;
    }

    @RequestMapping(path = "/classify/{id}",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    public HashMap getAllClassify(@PathVariable("id") long id){
        HashMap map = new HashMap();
        List<SensorClassify> sensorClassifies = null;
        try {
            sensorClassifies = sensorClassifyService.findSensorClassifiesByGateway(id);
        }catch (Exception e){
            System.out.print(e.getMessage());
            map =  this.setResponse("error","db error",null);
            return map;
        }
        map = this.setResponse("success","",sensorClassifies);
        return map;
    }


}
