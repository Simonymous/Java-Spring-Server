package htw.teamsimon.serverapplication;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ServerapplicationApplicationTests {

	final String baseUrl = "http://localhost:8080/";

	@Test
	void registrationTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		String url = baseUrl + "register?name=test&password=test";
		URI uri = new URI(url);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("Registration of user test successfull"));
	}
}