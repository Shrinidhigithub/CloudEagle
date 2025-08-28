package com.dropboxBusiness.dropbox_business_api;

import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DropboxBusinessApiApplication implements CommandLineRunner {

	// Replace with your Team Access Token
	private static final String ACCESS_TOKEN = "sl.u.AF-rDNy9LGeHfGu1u-hLbPnZLzdgHUP5jitWxlJjDaUqm5L7fGlWCjUNc5lgT_0aWwhuV2e_fpW_xKiVj_ttdMxeq1Wmc69_Zft3VN6jQwog44v-xGVhlJLasCu6tYYps6rvPyW05e1wVUiXxfxbFjSwW4Qw-FLWGZXaP0kHxK0E8X4qvu6OcEFNAUF7hqWNjZtpulzpNy8fnCAYReKtRBatXuLyN8izwwPFJu4-dQ48B5HizWfcXvZIknlq8wDtXZGCGAS7l9x1AkOLU1K7LyD661mtEyorQ3Wgeyr4PL7ndWoc4tyBRVpVkStf9t6uoUPHKlLRWwydjsDYJ5dHCyHolG9lCPr6rSNLtWqZWkivWEHes1WXp9k6qS-sNWoquVblr-dqTjhmuzow-nZHK9gwpEo8_bpogQgdRsX6gNjQDKwZLju-ndbf5nj8lsxAJDcAwJsBlm4zbOVVIjqAOBUAPgd0Aw0yPaWAik8z-auoYUXhUA8UkvwH6uvbmdXjySwgUSz1of1KhxKknR_W6fjKQNJS8aAcCSCD9UUo9v6Ymt5pZtvsmmG-Wiik-tmfORAf-T4pOhm3-4QQRLzGdwZdMTerKc9sr_WC1d9kPB3zs5tLXeMYI7o1pxt8SewHuM8JEDoNH6b4Bg8wQbY3ck_8lqo3-gu7pjJVbDOktzQaJ7R3auCcAQMHMF4wLD69_es3_cenx6uQ95MEAmke9wGJjn15cNdRnWXpToJRaBuemAZKUtYKMvFLxB7Oy2BZOhb7lUDxHWJo7DhLl1TI8YzxLnZL7a1nL5n-42v08OWIlhgJ7PZnzg40idOAQuFkhp85W8rrEn7omZnuj6EiW8KtgbUGiMIC7HOs38M4xwx9MNu7uFVeLU1ayZ_kugRxu0WAhy4ZV4tPsQiWeDMaVw1JvSASaCROTxx1GjB4RVjiGe88tu_5VghddZbP0XeIogORFH0YBRXSy4_jslXs8IGTCDOVX69w2OuriqpjeFBO93HhNUNz-qzIM6OqHJ_7cFGnPhoKPt447FbAsaJFJJ_g1nDZuZuKi5OkcW278M18V_OesAtEiblJA1rYrYOD2COLrChNyvpR80O7RxhAugfYbZdDw_rmWjZcQrPDi3lSIkJBmJHfPMeXT6oLu83TuLU";

	private static final String TEAM_INFO_URL = "https://api.dropboxapi.com/2/team/get_info";

	public static void main(String[] args) {
		SpringApplication.run(DropboxBusinessApiApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// ✅ Instantiate RestTemplate here
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(ACCESS_TOKEN);

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
