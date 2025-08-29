package com.dropboxBusiness.dropbox_business_api;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Base64;

@Component
public class DropboxClient {

    @Value("${dropbox.refresh.token}")
    private String refreshToken;

    @Value("${dropbox.app.key}")
    private String appKey;

    @Value("${dropbox.app.secret}")
    private String appSecret;

    private String currentAccessToken;

    public String getAccessToken() {
        if (currentAccessToken == null) {
            currentAccessToken = fetchAccessToken();
        }
        return currentAccessToken;
    }

    private String fetchAccessToken() {
        try {
            String url = "https://api.dropboxapi.com/oauth2/token";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            String auth = appKey + ":" + appSecret;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            headers.set("Authorization", "Basic " + encodedAuth);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            String body = "grant_type=refresh_token&refresh_token=" + refreshToken;

            HttpEntity<String> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                JSONObject jsonResponse = new JSONObject(response.getBody());
                String newToken = jsonResponse.getString("access_token");
               // System.out.println("Generated new Dropbox Access Token: " + newToken);
                return newToken;
            } else {
                throw new RuntimeException("Failed to refresh token. Status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching access token: " + e.getMessage(), e);
        }
    }
}
