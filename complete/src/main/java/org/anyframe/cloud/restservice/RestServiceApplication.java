package org.anyframe.cloud.restservice;

import org.anyframe.cloud.apm.swagger.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"org.anyframe.cloud.restservice", "org.anyframe.cloud.web"})
@Import({ SwaggerConfiguration.class })
public class RestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}
}
