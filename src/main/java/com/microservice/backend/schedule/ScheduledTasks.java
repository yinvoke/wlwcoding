package com.microservice.backend.schedule;

import okhttp3.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "* * * * * *")
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
        String json = "{\"a\":\"hhhh\"}";
        try {
            post("http://127.0.0.1:8080/api/schedule/10", json);
        }catch (Exception e){

        }
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
