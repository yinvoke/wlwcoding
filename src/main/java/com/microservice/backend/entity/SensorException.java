package com.microservice.backend.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="exception")
public class SensorException {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Date time;
    private Long status;

    @ManyToOne
    @JoinColumn(name="sensor_id")
    private Sensor sensor;

    public SensorException(){}

    public SensorException(String description, Date time, Long status, Sensor sensor) {
        this.description = description;
        this.time = time;
        this.status = status;
        this.sensor = sensor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
