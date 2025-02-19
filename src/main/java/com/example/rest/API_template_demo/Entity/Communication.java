package com.example.rest.API_template_demo.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    protected String sessionId;
    private final String URL = "http://94.198.50.185:7081/api/users";


    @Autowired
    private final RestTemplate restTemplate;

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
        sessionId = setCookieHeader.split(";")[0]; //извлекаем JSESSION ID
        System.out.println("Session ID: " + sessionId);

        return responseEntity.getBody();

    }


    public String saveUser(User user) {
        HttpHeaders headersWC = new HttpHeaders();
        headersWC.add("Cookie", sessionId);


        HttpEntity<User> saveRequest = new HttpEntity<>(user, headersWC);

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.POST, saveRequest, String.class);
        return responseEntity.getBody();
    }

    public String updateUser(User user) {
        HttpHeaders headersWC = new HttpHeaders();
        headersWC.add("Cookie", sessionId);

        HttpEntity<User> updateRequest = new HttpEntity<>(user, headersWC);

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.PUT, updateRequest, String.class);
        return responseEntity.getBody();
    }

    public String deleteUser(Long id) {
        HttpHeaders headersWC = new HttpHeaders();
        headersWC.add("Cookie", sessionId);

        HttpEntity<User> deleteRequest = new HttpEntity<>(headersWC);

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id,
                HttpMethod.DELETE, deleteRequest, String.class);
        return responseEntity.getBody();

    }


}








