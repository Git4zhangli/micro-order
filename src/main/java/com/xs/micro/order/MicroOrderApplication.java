package com.xs.micro.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.StopWatch;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ImportResource({ "classpath:bytetcc-supports-springcloud.xml" })
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
@EnableAsync
@EnableSwagger2
public class MicroOrderApplication {
	
	public static void main(String[] args) {
		StopWatch sw = new StopWatch();
		sw.start();
		SpringApplication.run(MicroOrderApplication.class, args);
		sw.stop();

		System.out.println("--------------------------------------------------------");
		System.out.printf("------------Service is started. cost:%s s---------------\n", sw.getTotalTimeSeconds());
		System.out.println("--------------------------------------------------------");
	}
}
