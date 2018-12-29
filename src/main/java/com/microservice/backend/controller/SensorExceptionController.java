package com.microservice.backend.controller;

import com.microservice.backend.common.utils.DownloadFileUtil;
import com.microservice.backend.entity.SensorException;
import com.microservice.backend.service.SensorExceptionService;
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

    @RequestMapping(value="/page/{page}",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public HashMap getAllSensorExceptionByPage(@PathVariable("page")int page){
        Page<SensorException> sensorExceptions;
        HashMap map = new HashMap();
        Pageable pageable = new PageRequest(page,10, Sort.Direction.DESC,"time");
        try {
            sensorExceptions = sensorExceptionService.findAll(pageable);
        }catch (Exception e){
            map = this.setResponse("errror","db error",null);
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
            sensorExceptions = sensorExceptionService.findByTime(simpleDateFormat.format(dateFromTamp), simpleDateFormat.format(dateToTamp),30);
        }catch (Exception e){
            map = this.setResponse("error","db error",null);
            return map;
        }

        map = this.setResponse("success",null,sensorExceptions);
        return  map;
    }

    @RequestMapping(value="/download",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object downloadSensorException() throws IOException{
        //读取数据
        List<SensorException> sensorExceptions = new ArrayList<SensorException>();
        HashMap map = new HashMap();
        ResponseEntity<InputStreamResource> response = null;
        //命名
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String filename = dateFormat.format(date)+"@sensorException.txt";
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
            sensorExceptions = sensorExceptionService.findAll();
            System.out.println("查询结果成功！");
            PrintStream ps = new PrintStream(new FileOutputStream(file),true,"utf-8");
            for(int i = 0; i < sensorExceptions.size(); i++) {
                ps.append("Id:"+sensorExceptions.get(i).getId()+"\t");
                ps.append("Description:"+sensorExceptions.get(i).getDescription()+"\t");
                ps.append("Time:"+sensorExceptions.get(i).getTime()+"\t");
                ps.append("Status:"+sensorExceptions.get(i).getStatus()+"\t");
                ps.append("Sensor_Id:"+sensorExceptions.get(i).getSensor().getId()+"\t");
                ps.append("Sensor_Description:"+sensorExceptions.get(i).getSensor().getDescription()+"\t");
                ps.append("Sensor_Location:"+sensorExceptions.get(i).getSensor().getLocation()+"\t");
                ps.append("Sensor_Factory:"+sensorExceptions.get(i).getSensor().getFactory()+"\t");
                ps.append("Sensor_Install_time:"+sensorExceptions.get(i).getSensor().getInstall_time()+"\t");
                ps.append("Sensor_Produce_date:"+sensorExceptions.get(i).getSensor().getProduce_date()+"\t");
                ps.append("Sensor_Maintenance_time:"+sensorExceptions.get(i).getSensor().getMaintenance_time()+"\t");
                ps.append("Sensor_Status:"+sensorExceptions.get(i).getSensor().getStatus()+"\t");
                ps.append("Sensor_SensorClassifyId:"+sensorExceptions.get(i).getSensor().getSensorClassify().getId()+"\t");
                ps.append("Sensor_SensorClassifyName:"+sensorExceptions.get(i).getSensor().getSensorClassify().getName()+"\t");
                ps.append("Sensor_SensorClassifyStatus:"+sensorExceptions.get(i).getSensor().getSensorClassify().getStatus()+"\n");
            }
            ps.close();
            System.out.println("表格内容写入成功！");

            response = DownloadFileUtil.download("downloadfiles",filename,"SensorExceptions");
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
