package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class TestController {
    private static final Logger logger = Logger.getLogger(TestController.class.getName());

    @Autowired
    private Registration reg;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hello")
    public String getHello(HttpServletRequest req, HttpServletResponse res) {
        String srvId = reg.getServiceId();
        ServiceInstance instance = client.getInstances(srvId).get(0);

        logger.info("/hello, host:" + instance.getHost() + ", service id:" + instance.getServiceId());

        return "hello";
    }
}
