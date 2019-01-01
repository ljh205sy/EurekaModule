package com.vrv.EurekaProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={"com.vrv.common.filter","com.vrv.EurekaProvider"})
public class EurekaProvider {

	public static void main(String[] args) {
		SpringApplication.run(EurekaProvider.class, args);
	}
}
