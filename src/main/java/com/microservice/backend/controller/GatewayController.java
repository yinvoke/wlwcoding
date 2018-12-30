package com.microservice.backend.controller;

import com.microservice.backend.common.utils.ErrorResponseUtil;
import com.microservice.backend.entity.Gateway;
import com.microservice.backend.entity.Sensor;
import com.microservice.backend.entity.SensorClassify;
import com.microservice.backend.service.GatewayService;
import com.microservice.backend.service.SensorClassifyService;
import com.microservice.backend.service.SensorService;
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

    @Autowired
    SensorService sensorService;

    @RequestMapping(value="" ,method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap addGateway (HttpServletRequest request){
        String ip = request.getParameter("ip");
        String port = request.getParameter("port");
        String description =  request.getParameter("description");
        String location = request.getParameter("location");
        Gateway gateway = new Gateway(ip,port,description,location,1L);
        HashMap map = new HashMap();
        if (port.equals("") || description.equals("") || ip.equals("")){
            map = this.setResponse("error","some info null",null);
            return map;
        }
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

    @RequestMapping(value="" ,method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap updateGateway (HttpServletRequest request){
        long id = Long.parseLong(request.getParameter("id"));
        String ip = request.getParameter("ip");
        String port = request.getParameter("port");
        String description =  request.getParameter("description");
        String location = request.getParameter("location");
        Gateway gateway = gatewayService.findById(id);
        HashMap map = new HashMap();
        int flag = 0;
        if(ip.length()!=0)
            gateway.setIp(ip);
        else
            flag = 1;
        if (port.length()!=0)
            gateway.setPort(port);
        else
            flag = 1;
        if(description.length()!=0)
            gateway.setDescription(description);
        else
            flag = 1;
        if (location.length()!=0)
            gateway.setLocation(location);
        else
            flag = 1;
        if(flag == 1){
            map = this.setResponse("error","some data null",null);
            return map;
        }
        try{
            gatewayService.update(gateway);
        }catch (Exception e){
            System.out.println(e.getMessage());
            map = this.setResponse("error","db error",null);
            return map;
        }
        map = this.setResponse("success",null,gateway);
        return map;
    }

    @RequestMapping(path="/{id}",method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap deleteGateway(@PathVariable("id") long id){
        Gateway gateway = gatewayService.findById(id);
        HashMap map = null;
        if (gateway == null){
            map = this.setResponse("error","id error,not this gateway",null);
            return map;
        }
        List<Sensor> sensors = gateway.getSensors();
        for(int i=0;i<sensors.size();i++){
            Sensor sensor = sensors.get(i);
            sensor.setStatus(0L);
            sensorService.update(sensor);
        }
        gateway.setStatus(0L);
        try{
            gatewayService.update(gateway);
        }catch (Exception e){
            System.out.println(e.getMessage());
            map = this.setResponse("error","db error",null);
            return map;
        }

        map = this.setResponse("success",null,null);
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
