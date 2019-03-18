package com.ribbon.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/ribbon")
    public String helloConsumer() {
        String ret = restTemplate.getForEntity("http://testController/hello", String.class).getBody();
        return ret;
    }
}
