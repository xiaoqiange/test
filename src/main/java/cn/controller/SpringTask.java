package cn.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTask {
    @Scheduled(cron = "0/3 * * * * ?")
    public void job() { 
        System.out.println("任务进行中。。。"); 
    }
}
