package com.example.rest.API_template_demo.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";

    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers() {// GET получение всех пользователей
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null
                        , new ParameterizedTypeReference<List<User>>() {
                        });

        HttpHeaders headers = responseEntity.getHeaders(); // извлеч-е session id из заголовка Set-Cookie
        String setCookieHeader = headers.getFirst(HttpHeaders.SET_COOKIE);
        String sessionId = setCookieHeader.split(";") [0]; //извлекаем JSESSION ID
        System.out.println("Session ID: " + sessionId);

        return responseEntity.getBody();
    }

//    public User getUser (Long id){
//
//        return  null;
//    }


    public void saveUser(User user) { // POST & PUT добавление\изменение пользователя

    }

    public void deleteUser(Long id) { // DELETE

    }


}
