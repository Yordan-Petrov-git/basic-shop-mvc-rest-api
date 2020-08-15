package com.shop.advance.academy.yordan.petrov.git.shop.slo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SlowController {


    //TODO FOR DELETION TESTING ONLY
    @TrackLatency(latency = "operation1")
    @GetMapping("/operation1")
    public String operation1() {
        return "operation1";
    }

    //TODO FOR DELETION TESTING ONLY
    @TrackLatency(latency = "operation2")
    @GetMapping("/operation2")
    public String operation2() throws InterruptedException {
        //emulate hard processing
        Thread.sleep(new Random().nextInt(1000));
        return "operation2";
    }

}
