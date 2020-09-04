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
public class AuthenticationTest {

    final String baseUrl = "http://localhost:";

    @LocalServerPort
    final int definedPort = 8080;

    @Test
    public void authenticationSuccessTest() throws URISyntaxException {
        String request = "/authenticate?name=admin&password=admin";
        String result = "User: UserModel{name='admin', password='admin'} authenticated. Token:";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }

    @Test
    public void authenticationMissingNameTest() throws URISyntaxException {
        String request = "/authenticate?password=admin";
        String result = "Required String parameter 'name' is not present";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }

    @Test
    public void authenticationMissingPasswordTest() throws URISyntaxException {
        String request = "/authenticate?name=admin";
        String result = "Required String parameter 'password' is not present";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }

    @Test
    public void authenticationInvalidCredentialsTest() throws URISyntaxException {
        String request = "/authenticate?name=admin&password=password";
        String result = "Invalid Credentials";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }
}