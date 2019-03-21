package com.ribbon.demo.command;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

public class StringCommand extends HystrixCommand<String> {

    private final static String TEST_SERVICE = "testService";

    private RestTemplate restTemplate;
    private String name;

    public StringCommand(Setter setter, RestTemplate restTemplate, String name) {
        super(setter);
        this.restTemplate = restTemplate;
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForEntity("http://" + TEST_SERVICE + "/hello?name={1}", String.class, name).getBody();
    }
}
