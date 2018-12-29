package com.microservice.backend.controller;


import com.microservice.backend.common.utils.DownloadFileUtil;
import com.microservice.backend.entity.GatewayException;
import com.microservice.backend.service.GatewayExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
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

    @RequestMapping(value="/page/{page}",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap getAllGatewayExceptionByPage(@PathVariable("page")int page){
        Page<GatewayException> gatewayExceptions;
        HashMap map = new HashMap();
        Pageable pageable = new PageRequest(page,10, Sort.Direction.DESC,"time");
        try {
            gatewayExceptions = gatewayExceptionService.findAll(pageable);
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

    @RequestMapping(value="/download",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object downloadGatewayException() throws IOException {
        //读取数据
        List<GatewayException> gatewayExceptions = new ArrayList<GatewayException>();
        HashMap map;
        ResponseEntity<InputStreamResource> response = null;
        //命名
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String filename = dateFormat.format(date)+"@gatewayException.txt";
        //创建文件
        URL url = this.getClass().getClassLoader().getResource("downloadfiles");
        System.out.println(url.getPath());
        File file = new File(url.getPath(),filename);
        if(!file.exists()){
            file.createNewFile();
            System.out.println("文件创建成功！");
        }
        //写入内容
        try {
            gatewayExceptions = gatewayExceptionService.findAll();
            System.out.println("查询结果成功！");
            PrintStream ps = new PrintStream(new FileOutputStream(file),true,"utf-8");
            for(int i = 0; i < gatewayExceptions.size(); i++) {
                ps.append("Id:"+gatewayExceptions.get(i).getId()+"\t");
                ps.append("Description:"+gatewayExceptions.get(i).getDescription()+"\t");
                ps.append("Time:"+gatewayExceptions.get(i).getTime()+"\n");
                ps.append("Status:"+gatewayExceptions.get(i).getStatus()+"\t");
                ps.append("Gateway_Id:"+gatewayExceptions.get(i).getGateway().getId()+"\t");
                ps.append("Gateway_Ip:"+gatewayExceptions.get(i).getGateway().getIp()+"\t");
                ps.append("Gateway_Port:"+gatewayExceptions.get(i).getGateway().getPort()+"\t");
                ps.append("Gateway_Description:"+gatewayExceptions.get(i).getGateway().getDescription()+"\t");
                ps.append("GatewayStatus:"+gatewayExceptions.get(i).getGateway().getStatus()+"\t");
            }
            ps.close();
            response = DownloadFileUtil.download("downloadfiles",filename,"GatewayExceptions");
            System.out.println("下载成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("下载失败");
            map = this.setResponse("error","download error",null);
            return  map;
        }
        return response;
    }
}
