package com.server.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServerDemoApplication {

	public static void main(String[] args) {
		//-Dspring.profiles.active=node1
		//-Dspring.profiles.active=node2
		SpringApplication.run(ServerDemoApplication.class, args);
	}
}
