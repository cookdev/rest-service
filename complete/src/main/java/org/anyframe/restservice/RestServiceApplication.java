package org.anyframe.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(
		basePackages = {"org.anyframe.restservice",
				"org.anyframe.cloud.apm.swagger",
				"org.anyframe.web",
				"org.anyframe.data.h2"
		}
)
public class RestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}
}
