package com.example.rest.API_template_demo.Entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {

    @Autowired
    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";
    //private final String sessionId;


    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    public List<User> getAllUsers() {// GET получение всех пользователей
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null
                        , new ParameterizedTypeReference<List<User>>() {
                        });

        //List<User> allUsers = responseEntity.getBody();

        HttpHeaders headers = responseEntity.getHeaders(); // извлеч-е session id из заголовка Set-Cookie
        String setCookieHeader = headers.getFirst(HttpHeaders.SET_COOKIE);
        String sessionId = setCookieHeader.split(";")[0]; //извлекаем JSESSION ID
        System.out.println("Session ID: " + sessionId);

        return responseEntity.getBody();

    }

//    public List<User> getAllUsers() {
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL, String.class);
//        responseEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
//
//        //String sessionId = setCookieHeader.split(";")[0]; //извлекаем JSESSION ID
//        System.out.println("Session ID: " + sessionId);
//
//        return null;
//    }


//    public String saveUser() throws JsonProcessingException { // POST & PUT добавление\изменение пользователя
//        HttpHeaders headersWithCookie = new HttpHeaders();
//        headersWithCookie.set("Cookie", sessionId);
//       //headersWithCookie.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
//        headersWithCookie.setContentType(MediaType.APPLICATION_JSON);
//
//        //User newUser = new User(3L, "James", "Brown", (byte) 33);
//        String userJs_1 = "{\"id\":3,\"name\":\"James\",\"lastName\":\"Brown\",\"age\":33}";
//
//        HttpEntity<String> request = new HttpEntity<>(userJs_1, headersWithCookie);
//
//        ResponseEntity<String> saveResponse = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
//        String code_1 = saveResponse.getBody();
//        System.out.println("Code part_1" + code_1);
//
//        //return extractCode(saveResponse);
//    }
//
//    public String updateUser() throws JsonProcessingException {
//        HttpHeaders headersWithCookie = new HttpHeaders();
//        headersWithCookie.set("Cookie", sessionId);
//        headersWithCookie.setContentType(MediaType.APPLICATION_JSON);
//
//        String userJs_2 = "{\"id\":3,\"name\":\"Thomas\",\"lastName\":\"Shelby\",\"age\":33}";
//
//        HttpEntity<String> request = new HttpEntity<>(userJs_2, headersWithCookie);
//
//        ResponseEntity<String> updateResponse =
//                restTemplate.exchange(URL + "/3", HttpMethod.PUT, request, String.class);
//        String code_2 = updateResponse.getBody();
//        System.out.println("Code part_2" + code_2);
//
//        //return extractCode(updateResponse);
//
//    }

//    public String deleteUser(Long id) throws JsonProcessingException { // DELETE
//        HttpHeaders headersWithCookie = new HttpHeaders();
//        headersWithCookie.set("Cookie", sessionId);
//
//        HttpEntity<String> request = new HttpEntity<>(headersWithCookie);
//
//        ResponseEntity<String> deleteResponse =
//                restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, request, String.class);
//
//        //return extractCode(deleteResponse);
//    }

//    private String extractCode(ResponseEntity<String> response) throws JsonProcessingException {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonNode = objectMapper.readTree(response.getBody());
//            return jsonNode.get("code").asText();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
//
//    public String getFinalCode() throws JsonProcessingException {
//        try {
//            StringBuilder finalCode = new StringBuilder();
//
//            finalCode.append(saveUser());
//            finalCode.append(updateUser());
//            finalCode.append(deleteUser(3L));
//        } catch (Exception e) {
//            e.printStackTrace();
//        return finalCode.toString();
//    }
}



