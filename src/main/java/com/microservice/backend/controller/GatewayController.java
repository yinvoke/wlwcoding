package com.microservice.backend.controller;

import com.microservice.backend.common.utils.ErrorResponseUtil;
import com.microservice.backend.entity.Gateway;
import com.microservice.backend.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/gateway")
public class GatewayController {
    @Autowired
    GatewayService gatewayService;

    @RequestMapping(value="/" ,method = RequestMethod.POST, produces = "application/json; charset=utf-8")
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
    public String getGatewayById(@PathVariable("id") long id){
        Gateway gateway = new Gateway();
        System.out.println(id);
        try{
            gateway = gatewayService.findById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ErrorResponseUtil.setResponse("400", e.getMessage());
        }

        if (gateway == null){
            return ErrorResponseUtil.setResponse("400", "get gateway error");
        }
        return  ErrorResponseUtil.setResponse("200", "Gateway:"+gateway.getDescription());
    }



}
