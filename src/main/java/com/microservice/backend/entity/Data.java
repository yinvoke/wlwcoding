package com.microservice.backend.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float data;
    private Date time;
    private Long status;

    @ManyToOne
    @JoinColumn(name="sensor_id")
    private Sensor sensor;

    public Data(){}

    public Data(Float data, Date time, Long status, Sensor sensor) {
        this.data = data;
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

    public Float getData() {
        return data;
    }

    public void setData(Float data) {
        this.data = data;
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
