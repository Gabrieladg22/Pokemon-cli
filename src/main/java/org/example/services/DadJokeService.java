package org.example.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class DadJokeService {

    public final String API_URL = "https://icanhazdadjoke.com";

    //RestClient.create() -- sets up the restClient object so we can use it to Query an API
    private RestClient restClient = RestClient.create();

    public String getDadJoke(){
        String response = restClient
                .get()
                .uri(API_URL)
                .header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN_VALUE)
                // This heder is added to make the response come back in plain text
                .retrieve()
                .body(String.class);

        return response;
    }
}
