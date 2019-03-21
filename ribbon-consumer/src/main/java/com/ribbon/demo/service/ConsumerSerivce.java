package com.ribbon.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerSerivce {

    private final static String TEST_SERVICE = "testService";

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloServiceFallback",
            commandProperties = @HystrixProperty(
                    name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"))
    public String helloService(String name) {
        String ret = restTemplate.getForEntity("http://" + TEST_SERVICE + "/hello?name={1}", String.class, name).getBody();
        return ret;
    }

    public String helloServiceFallback(String name) {
        return "helloServiceFallback";
    }
}