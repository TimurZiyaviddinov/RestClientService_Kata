package org.example;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Consumer {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://94.198.50.185:7081/api/users";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String sessionID = response.getHeaders().getFirst("Set-cookie");
        System.out.println(sessionID);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.COOKIE, sessionID);
        User newUser = new User(3L, "James", "Brown", (byte) 24);
        HttpEntity<User> request1 = new HttpEntity<>(newUser, httpHeaders);
        ResponseEntity<String> code1 = restTemplate.exchange(url, HttpMethod.POST, request1, String.class);


        User updateUser = new User(3L, "Thomas", "Shelby", (byte) 24);
        HttpEntity<User> request2 = new HttpEntity<>(updateUser, httpHeaders);
        ResponseEntity<String> code2 = restTemplate.exchange(url, HttpMethod.PUT, request2, String.class);

        ResponseEntity<String> code3 = restTemplate.exchange(url + "/3", HttpMethod.DELETE, request2, String.class);

        System.out.println(code1.getBody() + code2.getBody() + code3.getBody());
    }
}
