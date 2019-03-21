package com.feign.demo.controller;

import com.feign.demo.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FeignController {

    @Autowired
    private FeignService feignService;

    @RequestMapping("/feign")
    public String feignConsumer(HttpServletRequest req) {
        String name = req.getParameter("name");
        String ret = feignService.testHello(name);
        return ret;
    }
}
