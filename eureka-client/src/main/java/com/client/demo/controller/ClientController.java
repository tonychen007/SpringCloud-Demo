package com.client.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class ClientController {
    private static final Logger logger = Logger.getLogger(ClientController.class.getName());

    @Autowired
    private Registration reg;

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private Environment env;

    @RequestMapping("/hello")
    public String getHello(HttpServletRequest req, HttpServletResponse res, @RequestParam(value = "name") String name) throws IOException {
        String realIP = req.getHeader("X-Real-IP");
        String ForwaordFor = req.getHeader("X-Forwarded-For");

        Cookie cookie = new Cookie("name","tony");
        cookie.setDomain("tony");
        res.addCookie(cookie);
        String srvId = reg.getServiceId();
        List<ServiceInstance> instances = client.getInstances(srvId);
        ServiceInstance instance = null;
        for (ServiceInstance inst : instances) {
            String host = env.getProperty("eureka.instance.hostname");
            int port = Integer.parseInt(env.getProperty("server.port"));
            if (inst.getHost().equals(host) && inst.getPort() == port) {
                instance = inst;
                break;
            }
        }

        assert (instance != null);
        logger.info("/hello, host:" + instance.getHost() + ", port:" + instance.getPort() + ", service id:" + instance.getServiceId());

        //res.sendRedirect("t2");
        String url = req.getRequestURL().toString();
        res.setHeader("Location",url);
        return name + "hello";
    }
}
