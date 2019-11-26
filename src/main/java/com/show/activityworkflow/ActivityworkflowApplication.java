package com.show.activityworkflow;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(value = {"com.show"})
public class ActivityworkflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityworkflowApplication.class, args);
	}

}
