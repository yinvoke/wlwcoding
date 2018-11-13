package com.microservice.backend.controller;

import com.microservice.backend.common.utils.ErrorResponseUtil;
import com.microservice.backend.entity.Gateway;
import com.microservice.backend.entity.Sensor;
import com.microservice.backend.service.GatewayService;
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

    @RequestMapping(value="" ,method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addGateway(HttpServletRequest request){
        String ip = request.getParameter("ip");
        String port = request.getParameter("port");
        String description =  request.getParameter("description");
        String location = request.getParameter("location");
        Gateway gateway = new Gateway(ip,port,description,location,1L);
        try{
            gatewayService.inset(gateway);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ErrorResponseUtil.setResponse("400", e.getMessage());
        }

        return ErrorResponseUtil.setResponse("200", "Add gateway success");
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



}
