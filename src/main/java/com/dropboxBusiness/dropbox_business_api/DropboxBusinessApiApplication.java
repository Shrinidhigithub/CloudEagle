package com.dropboxBusiness.dropbox_business_api;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DropboxBusinessApiApplication implements CommandLineRunner {

	@Autowired
	private DropboxClient dropboxClient;


	// Replace with your Team Access Token
	//private static final String ACCESS_TOKEN = "dropbox.access.token";

	private static final String TEAM_INFO_URL = "https://api.dropboxapi.com/2/team/get_info";

	public static void main(String[] args) {
		SpringApplication.run(DropboxBusinessApiApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// Instantiate RestTemplate here

		String accessToken = dropboxClient.getAccessToken();

		if (accessToken == null || accessToken.isEmpty()) {
			System.out.println("Error: DROPBOX_ACCESS_TOKEN is not set.");
			return;
		}

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(accessToken);

		HttpEntity<String> entity = new HttpEntity<>("null",headers);

		try {
			ResponseEntity<String> response = restTemplate.exchange(
					TEAM_INFO_URL,
					HttpMethod.POST,
					entity,
					String.class
			);

			if (response.getStatusCode() == HttpStatus.OK) {
				JSONObject jsonResponse = new JSONObject(response.getBody());
				String teamName = jsonResponse.getString("name");
				System.out.println("Team/Organization Name: " + teamName);
			} else {
				System.out.println("Failed to fetch team info. Status: " + response.getStatusCode());
			}
		} catch (Exception e) {
			System.out.println("Error calling Dropbox API: " + e.getMessage());
		}
	}
}
