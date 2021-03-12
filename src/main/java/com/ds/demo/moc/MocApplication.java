package com.ds.demo.moc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MocApplication {

	public static void main(String[] args) {
		SpringApplication.run(MocApplication.class, args);
	}
}
