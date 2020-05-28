package com.dragon.study.bytebuddy.timer;

import com.dragon.study.bytebuddy.advice.AdviceProfiled;
import com.dragon.study.bytebuddy.annotation.EnableMetrics;
import com.dragon.study.bytebuddy.bean.Person;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by dragon on 16/3/28.
 */
@Component
@EnableMetrics
public class TimerPerson {

    @Autowired
    private Person person;

    //  @Scheduled(fixedDelay = 5000L, initialDelay = 1000L)
    public void httpClientTest() {
        OkHttpClient client = new OkHttpClient();
        String response;
        try {
            response = getResponse(client, "http://enjoy.ricebook.com/");
        } catch (IOException e) {
            e.printStackTrace();
            response = "request error";
        }

        System.out.println(response);
    }

    private String getResponse(OkHttpClient client, String url) throws IOException {
        Request request = new Request.Builder().url("http://enjoy.ricebook.com/").build();

        Response response = client.newCall(request).execute();
        return String.valueOf(response.code());
    }

    //  @Count(name = "test.count")
//  @Scheduled(fixedDelay = 3000L, initialDelay = 1000L)
    public void testCount() {
        System.out.println("begin test count");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end test count");
    }


    @Scheduled(fixedDelay = 3000L, initialDelay = 3000L)
    public void testAdvice() {
        System.out.println("begin test advice");
        AdviceProfiled adviceProfiled = new AdviceProfiled();
        adviceProfiled.profile(1000, "test");
        System.out.println("end test advice");
    }


    public static void main(String[] args) {
        TimerPerson person = new TimerPerson();
        person.httpClientTest();
    }
}
