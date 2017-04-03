package com.ray.controller.schedule;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Ray on 2017/3/31.
 */
public class Schedule {
    @Scheduled(fixedRate = 100)
    public void out(){
        System.out.println("aaa");
    }
}
