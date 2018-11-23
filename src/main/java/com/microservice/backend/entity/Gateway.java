package com.microservice.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="gateway")
public class Gateway implements Serializable {
    private static final long serialVersionUID = 6222176558369919436L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;
    private String port;
    private String description;
    private String location;
    private Long status;

    @JsonIgnore
    @OneToMany(mappedBy = "gateway")
    private List<Sensor> sensors;

    public List<Sensor> getSensors() {  //返回status为1的传感器
        List<Sensor> valid_sensors = new ArrayList<>(this.sensors.size());
        for(Sensor sensor:this.sensors){
            if(sensor.getStatus() == 1)
                valid_sensors.add(sensor);
        }
        return valid_sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public Gateway(){}
    public Gateway(String ip, String port, String description, String location, Long status) {
        this.ip = ip;
        this.port = port;
        this.description = description;
        this.location = location;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

}
