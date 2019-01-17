
package com.vg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
@EnableScheduling
@MapperScan(basePackages = { "com.vg.mapper.*" })
public class StartApplication {

	public static void main(String[] args) {

		SpringApplication.run(StartApplication.class, args);

	}

}
