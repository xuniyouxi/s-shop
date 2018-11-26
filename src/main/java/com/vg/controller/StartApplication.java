package com.vg.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages= {"com.vg.controller","com.vg.serviceimpl"})
@EnableAutoConfiguration
@MapperScan(basePackages= {"com.vg.mapper"})
public class StartApplication extends SpringBootServletInitializer{

	
	public static void main(String[] args) {
		 SpringApplication.run(StartApplication.class, args);
	}
	
	@Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

}
