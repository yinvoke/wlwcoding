package com.microservice.backend.controller;

import java.util.HashMap;

public class BaseController {
    public HashMap setResponse(Object status,Object message, Object obj){
        HashMap hashMap = new HashMap();
        hashMap.put("status",status);
        hashMap.put("message",message);
        hashMap.put("data",obj);
        return hashMap;
    }
}
