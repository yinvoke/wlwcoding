package com.microservice.backend.controller;

import com.microservice.backend.entity.Data;
import com.microservice.backend.entity.Gateway;
import com.microservice.backend.entity.Sensor;
import com.microservice.backend.service.GatewayService;
import com.microservice.backend.service.SensorService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RequestMapping("/api/file")
@RestController
public class FileController {
    @Autowired
    GatewayService gatewayService;

    @Autowired
    SensorService sensorService;

    @RequestMapping(value="/gateway")
    public String getGatwayInfo(HttpServletRequest request, HttpServletResponse response){
        long id = Long.parseLong(request.getParameter("id"));
        Gateway gateway = gatewayService.findById(id);
        List<Sensor> sensors = gateway.getSensors();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        this.createGatewayTitle(workbook,sheet,gateway,sensors);

        String fileName = "网关"+gateway.getId()+".xls";
        try {
            this.buildExcelFile(fileName, workbook);
            this.buildExcelDocument(fileName,workbook,response);
            this.delFile("",fileName);
        }catch (Exception e){
            System.out.print(e.getMessage());
            return "error";
        }
        //删除文件
        return "success";
    }

    @RequestMapping(value="/sensor")
    public String getSensorInfo(HttpServletRequest request, HttpServletResponse response){
        long id = Long.parseLong(request.getParameter("id"));
        Sensor sensor = sensorService.findById(id);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        this.createSensorTitle(workbook,sheet,sensor);
        String fileName = "Sensor"+sensor.getId()+".xls";

        try {
            this.buildExcelFile(fileName, workbook);
            this.buildExcelDocument(fileName,workbook,response);
            this.delFile("",fileName);
        }catch (Exception e){
            System.out.print(e.getMessage());
            return "error";
        }
        //删除文件
        return "success";
    }

    //删除文件
    public void delFile(String path,String filename)throws Exception{
        File file=new File(path+"/"+filename);
        if(file.exists()&&file.isFile())
            file.delete();
    }

    //生成excel文件
    protected void buildExcelFile(String filename,HSSFWorkbook workbook) throws Exception{
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    protected void buildExcelDocument(String filename,HSSFWorkbook workbook,HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
//        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "utf-8"));
        response.setHeader("Content-Disposition", "attachment;filename="+filename);
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    private void createSensorTitle(HSSFWorkbook workbook, HSSFSheet sheet,Sensor sensor){
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0,6*256);
        sheet.setColumnWidth(1,50*256);
        sheet.setColumnWidth(2,30*256);
        sheet.setColumnWidth(3,30*256);
        sheet.setColumnWidth(4,20*256);
        sheet.setColumnWidth(5,20*256);
        sheet.setColumnWidth(6,20*256);
        sheet.setColumnWidth(7,20*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        //时间日期格式
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        String sensorTitle[] = {"ID","传感器描述","传感器厂家","传感器安装时间","传感器位置","传感器类型"};
        int length = sensorTitle.length;
        for (int i=0;i<length;i++) {
            cell = row.createCell(i);
            cell.setCellValue(sensorTitle[i]);
            cell.setCellStyle(style);
        }
        //添加传感器信息
        HSSFRow sensorRow = sheet.createRow(1);
        sensorRow.createCell(0).setCellValue(sensor.getId());
        sensorRow.createCell(1).setCellValue(sensor.getDescription());
        sensorRow.createCell(2).setCellValue(sensor.getFactory());
        HSSFCell sensorCell = sensorRow.createCell(3);  //设置时间格式
        sensorCell.setCellValue(sensor.getInstall_time());
        sensorCell.setCellStyle(dateStyle);
        sensorRow.createCell(4).setCellValue(sensor.getLocation());
        sensorRow.createCell(5).setCellValue(sensor.getSensorClassify().getName());

    }

    private void createGatewayTitle(HSSFWorkbook workbook, HSSFSheet sheet, Gateway gateway, List<Sensor> sensors){
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0,6*256);
        sheet.setColumnWidth(1,50*256);
        sheet.setColumnWidth(2,30*256);
        sheet.setColumnWidth(3,30*256);
        sheet.setColumnWidth(4,20*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);



        HSSFCell cell;
        String strs[] = {"ID","网关描述","网关IP","网关端口","网关位置"};
        int length = strs.length;
        for (int i=0;i<length;i++) {
            cell = row.createCell(i);
            cell.setCellValue(strs[i]);
            cell.setCellStyle(style);
        }

        // 添加网关信息
        HSSFRow gatewayRow = sheet.createRow(1);
        gatewayRow.createCell(0).setCellValue(gateway.getId());
        gatewayRow.createCell(1).setCellValue(gateway.getDescription());
        gatewayRow.createCell(2).setCellValue(gateway.getIp());
        gatewayRow.createCell(3).setCellValue(gateway.getPort());
        gatewayRow.createCell(4).setCellValue(gateway.getLocation());

        HSSFRow sensorTitleRow = sheet.createRow(3);
        String sensorTitle[] = {"ID","传感器描述","传感器厂家","传感器安装时间","传感器位置"};
        for(int i=0;i<sensorTitle.length;i++){
            sensorTitleRow.createCell(i).setCellValue(sensorTitle[i]);
        }

        HSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));



        int rowNum = 4;
        for(Sensor sensor:sensors){
            HSSFRow sensorRow = sheet.createRow(rowNum);
            sensorRow.createCell(0).setCellValue(sensor.getId());
            sensorRow.createCell(1).setCellValue(sensor.getDescription());
            sensorRow.createCell(2).setCellValue(sensor.getFactory());
            HSSFCell sensorCell = sensorRow.createCell(3);  //设置时间格式
            sensorCell.setCellValue(sensor.getInstall_time());
            sensorCell.setCellStyle(dateStyle);
            sensorRow.createCell(4).setCellValue(sensor.getLocation());
            rowNum++;
        }
    }

    @RequestMapping(value="/test")
    public String getGateway(HttpServletRequest request, HttpServletResponse response){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(1,12*256);
        sheet.setColumnWidth(3,17*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);


        cell = row.createCell(1);
        cell.setCellValue("显示名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);

        HSSFRow useRow = sheet.createRow(1);
        useRow.createCell(0).setCellValue(1);
        useRow.createCell(1).setCellValue("吴多智");
        useRow.createCell(2).setCellValue("wuduozhi");

        String fileName = "导出excel的例子.xls";
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(fileName);
            workbook.write(fos);
            fos.flush();
            fos.close();
        }catch (Exception e){
            System.out.print(e.getMessage());
        }


        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            return "error";
        }
        return "success";
    }


}
