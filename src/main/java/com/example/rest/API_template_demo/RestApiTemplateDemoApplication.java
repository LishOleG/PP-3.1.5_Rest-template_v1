package com.example.rest.API_template_demo;

import com.example.rest.API_template_demo.Configuration.MyConfig;
import com.example.rest.API_template_demo.Entity.Communication;
import com.example.rest.API_template_demo.Entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.boot.web.servlet.server.Session.SessionTrackingMode.URL;
import static org.springframework.boot.web.servlet.server.Session.SessionTrackingMode.valueOf;

@SpringBootApplication
public class RestApiTemplateDemoApplication {

	public static void main(String[] args) throws JsonProcessingException {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(MyConfig.class);

		Communication communication = context.getBean("communication"
		, Communication.class);

		List<User> allUsers = communication.getAllUsers();
		System.out.println(allUsers);

//		String finalCode = communication.getFinalCode();
//		System.out.println("Итоговый код: " + finalCode);



	}



}
