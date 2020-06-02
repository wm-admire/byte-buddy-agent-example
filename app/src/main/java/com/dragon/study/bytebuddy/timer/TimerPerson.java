package com.dragon.study.bytebuddy.timer;

import com.dragon.study.bytebuddy.advice.AdviceProfiled;
import com.dragon.study.bytebuddy.annotation.EnableMetrics;
import com.dragon.study.bytebuddy.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by dragon on 16/3/28.
 */
@Component
@EnableMetrics
public class TimerPerson {

    @Autowired
    private Person person;


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
}
