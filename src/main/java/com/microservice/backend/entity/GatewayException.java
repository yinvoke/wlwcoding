package com.microservice.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="GagewayException")
public class GatewayException {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private Long status;

    @ManyToOne
    @JoinColumn(name="gateway_id")
    private Gateway gateway;

    public GatewayException() {
    }

    public GatewayException(String description, Date time, Long status, Gateway gateway) {
        this.description = description;
        this.time = time;
        this.status = status;
        this.gateway = gateway;
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

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }
}
