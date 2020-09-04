package htw.teamsimon.serverapplication;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class RegistrationTest {

    final String baseUrl = "http://localhost:";

    @LocalServerPort
    final int definedPort = 8080;

    @Test
    public void registrationSuccessTest() throws URISyntaxException {
        String request = "/register?name=test&password=test";
        String result = "Registration of user test successfull";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }

    @Test
    public void registrationMissingNameTest() throws URISyntaxException {
        String request = "/register?password=test";
        String result = "Required String parameter 'name' is not present";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }

    @Test
    public void registrationMissingPasswordTest() throws URISyntaxException {
        String request = "/register?name=test";
        String result = "Required String parameter 'password' is not present";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }

    @Test
    public void registrationExistsTest() throws URISyntaxException {
        String request = "/register?name=admin&password=admin";
        String result = "Username already exists";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }
}