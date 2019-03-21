package com.ribbon.demo.controller;

import com.ribbon.demo.service.ConsumerSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerSerivce consumerSerivce;

    @RequestMapping("/ribbon")
    public String helloConsumer(@RequestParam(value = "name", required = true) String username) {
        return consumerSerivce.helloService(username);
    }
}