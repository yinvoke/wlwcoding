package com.microservice.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sensor_classify")
public class SensorClassify implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long status;

    @JsonIgnore
    @OneToMany(mappedBy = "sensorClassify")
    private List<Sensor> sensors;

    public SensorClassify(){}

    public SensorClassify(String name, Long status) {
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public List<Sensor> getSensors() {
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
}
