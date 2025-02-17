package com.example.rest.API_template_demo;

import com.example.rest.API_template_demo.Configuration.MyConfig;
import com.example.rest.API_template_demo.Entity.Communication;
import com.example.rest.API_template_demo.Entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class RestApiTemplateDemoApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(MyConfig.class);

		Communication communication = context.getBean("communication"
		, Communication.class);
		List<User> allUsers = communication.getAllUsers();
		System.out.println(allUsers);

		//cookie = responseHeaders.getFirst("Set-Cookie");
	}



}
