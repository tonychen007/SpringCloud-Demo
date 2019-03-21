package com.ribbon.demo.controller;

import com.ribbon.demo.service.RibbonSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {

    @Autowired
    private RibbonSerivce ribbonService;

    @RequestMapping("/ribbon")
    public String ribbonConsumer(@RequestParam(value = "name", required = true) String username) {
        return ribbonService.ribbonService(username);
    }

    @RequestMapping("/ribbon-command")
    public String ribbonConsumerCommand() {
        return ribbonService.ribbonServiceCommand();
    }
}