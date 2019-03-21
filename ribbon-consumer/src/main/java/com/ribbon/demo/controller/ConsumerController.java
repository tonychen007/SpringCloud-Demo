package com.ribbon.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @RequestMapping("/ribbon")
    public String helloConsumer(@RequestParam(value = "name", required = true) String username) {
        String ret = restTemplate.getForEntity("http://testController/hello?name={1}", String.class, username).getBody();
        return ret;
    }
}
