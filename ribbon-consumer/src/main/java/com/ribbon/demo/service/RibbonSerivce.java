package com.ribbon.demo.service;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ribbon.demo.command.StringCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class RibbonSerivce {

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

    public String helloServiceCommand() {
        String name = "tony-command";
        StringCommand command = new StringCommand(
                com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")),
                restTemplate, name);

        StringCommand futureCommand = new StringCommand(
                com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")),
                restTemplate, name);

        String ret1 = command.execute();
        Future<String> future = futureCommand.queue();
        String ret2 = null;

        try {
            ret2 = future.get();
        } catch (InterruptedException | ExecutionException e) {

        }

        return "sync:" + ret1 + ", future:" + ret2;
    }
}