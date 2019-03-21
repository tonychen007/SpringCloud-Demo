package com.feign.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("testService")
public interface FeignService {

    @RequestMapping("/hello")
    String testHello(@RequestParam(name = "name") String name);
}
