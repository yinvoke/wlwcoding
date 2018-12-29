package com.microservice.backend.controller;


import com.microservice.backend.common.utils.DownloadFileUtil;
import com.microservice.backend.entity.GatewayException;
import com.microservice.backend.entity.SensorException;
import com.microservice.backend.service.GatewayExceptionService;
import com.microservice.backend.service.SensorExceptionService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/download")
public class DownloadController extends BaseController{
    @Autowired
    SensorExceptionService sensorExceptionService;
    @Autowired
    GatewayExceptionService gatewayExceptionService;



    @RequestMapping(value="/GatewayException",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object downloadGatewayException() throws IOException {
        //读取数据
        List<GatewayException> gatewayExceptions = new ArrayList<GatewayException>();
        HashMap map;
        ResponseEntity<InputStreamResource> response = null;
        //命名
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String filename = dateFormat.format(date)+"@gatewayException"+".xls";
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
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("gateway");
            HSSFRow row0 = sheet.createRow(0);
            sheet.setDefaultColumnWidth(15);
            row0.createCell(0).setCellValue("Id");
            row0.createCell(1).setCellValue("Description");
            row0.createCell(2).setCellValue("Time");
            row0.createCell(3).setCellValue("Status");
            row0.createCell(4).setCellValue("Gateway_Id");
            row0.createCell(5).setCellValue("Gateway_Ip");
            row0.createCell(6).setCellValue("Gateway_Port");
            row0.createCell(7).setCellValue("Gateway_Description");
            row0.createCell(8).setCellValue("Gateway_Location");
            row0.createCell(9).setCellValue("Gateway_Status");
            System.out.println("表头创建完成");
            for(int i = 0; i < gatewayExceptions.size(); i++) {
                HSSFRow row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(gatewayExceptions.get(i).getId());
                row.createCell(1).setCellValue(gatewayExceptions.get(i).getDescription());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                row.createCell(2).setCellValue(sdf.format(gatewayExceptions.get(i).getTime()));
                row.createCell(3).setCellValue(gatewayExceptions.get(i).getStatus());
                row.createCell(4).setCellValue(gatewayExceptions.get(i).getGateway().getId());
                row.createCell(5).setCellValue(gatewayExceptions.get(i).getGateway().getIp());
                row.createCell(6).setCellValue(gatewayExceptions.get(i).getGateway().getPort());
                row.createCell(7).setCellValue(gatewayExceptions.get(i).getGateway().getDescription());
                row.createCell(8).setCellValue(gatewayExceptions.get(i).getGateway().getLocation());
                row.createCell(9).setCellValue(gatewayExceptions.get(i).getGateway().getStatus());
            }
            System.out.println("表格创建成功");
            wb.write( new FileOutputStream(url.getPath()+File.separator+filename));
            System.out.println("文件录入成功");
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


    @RequestMapping(value="/SensorException",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object downloadSensorException() throws IOException {
        //读取数据
        List<SensorException> sensorExceptions = new ArrayList<SensorException>();
        HashMap map;
        ResponseEntity<InputStreamResource> response = null;
        //命名
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String filename = dateFormat.format(date)+"@sensorException"+".xls";
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
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("sensor");
            HSSFRow row0 = sheet.createRow(0);
            sheet.setDefaultColumnWidth(20);
            row0.createCell(0).setCellValue("Id");
            row0.createCell(1).setCellValue("Description");
            row0.createCell(2).setCellValue("Time");
            row0.createCell(3).setCellValue("Status");
            row0.createCell(4).setCellValue("Sensor_Id");
            row0.createCell(5).setCellValue("Sensor_Description");
            row0.createCell(6).setCellValue("Sensor_Location");
            row0.createCell(7).setCellValue("Sensor_Factory");
            row0.createCell(8).setCellValue("Sensor_Install_time");
            row0.createCell(9).setCellValue("Sensor_Produce_date");
            row0.createCell(10).setCellValue("Sensor_Maintenance_time");
            row0.createCell(11).setCellValue("Sensor_Status");
            row0.createCell(12).setCellValue("Sensor_SensorClassify_Id");
            row0.createCell(13).setCellValue("Sensor_SensorClassify_Name");
            row0.createCell(14).setCellValue("Sensor_SensorClassify_Status");
            System.out.println("表头创建完成");
            for(int i = 0; i < sensorExceptions.size(); i++) {
                HSSFRow row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(sensorExceptions.get(i).getId());
                row.createCell(1).setCellValue(sensorExceptions.get(i).getDescription());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                row.createCell(2).setCellValue(sdf.format(sensorExceptions.get(i).getTime()));
                row.createCell(3).setCellValue(sensorExceptions.get(i).getStatus());
                row.createCell(4).setCellValue(sensorExceptions.get(i).getSensor().getId());
                row.createCell(5).setCellValue(sensorExceptions.get(i).getSensor().getDescription());
                row.createCell(6).setCellValue(sensorExceptions.get(i).getSensor().getLocation());
                row.createCell(7).setCellValue(sensorExceptions.get(i).getSensor().getFactory());
                row.createCell(8).setCellValue(sensorExceptions.get(i).getSensor().getInstall_time());
                row.createCell(9).setCellValue(sensorExceptions.get(i).getSensor().getProduce_date());
                row.createCell(10).setCellValue(sensorExceptions.get(i).getSensor().getMaintenance_time());
                row.createCell(11).setCellValue(sensorExceptions.get(i).getSensor().getStatus());
                row.createCell(12).setCellValue(sensorExceptions.get(i).getSensor().getSensorClassify().getId());
                row.createCell(13).setCellValue(sensorExceptions.get(i).getSensor().getSensorClassify().getName());
                row.createCell(14).setCellValue(sensorExceptions.get(i).getSensor().getSensorClassify().getStatus());
            }
            System.out.println("表格创建成功");
            wb.write( new FileOutputStream(url.getPath()+File.separator+filename));
            System.out.println("文件录入成功");
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
