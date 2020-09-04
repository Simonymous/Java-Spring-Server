package htw.teamsimon.serverapplication;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Before;
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
public class PrimeTest {

    final String baseUrl = "http://localhost:";

    String token;

    @LocalServerPort
    final int definedPort = 8080;

    @Before
    public void authenticationGetToken() throws URISyntaxException {
        String request = "/authenticate?name=admin&password=admin";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        int length = response.getBody().length();
        token = response.getBody().toString().substring(length - 101, length);
    }

    @Test
    public void primeSuccessTest() throws URISyntaxException {
        String request = "/prime?number=17&token=" + token;
        String result = "Number 17 is prime";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }

    @Test
    public void primeFailureTest() throws URISyntaxException {
        String request = "/prime?number=4&token=" + token;
        String result = "Number 4 is not prime";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }

    @Test
    public void primeMissingNumberTest() throws URISyntaxException {
        String request = "/prime?token=" + token;
        String result = "Required int parameter 'number' is not present";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }

    @Test
    public void primeMissingTokenTest() throws URISyntaxException {
        String request = "/prime?number=17";
        String result = "Required String parameter 'token' is not present";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }

    @Test
    public void primeNotIntTest() throws URISyntaxException {
        String request = "/prime?number=test&token=" + token;
        String result = "Number must be int";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }

    @Test
    public void primeLessZeroTest() throws URISyntaxException {
        String request = "/prime?number=-5&token=" + token;
        String result = "Number must be bigger than zero";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }

    @Test
    public void primeInvalidTokenTest() throws URISyntaxException {
        String request = "/prime?number=17&token=" + "wrongToken";
        String result = "Invalid Token";

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }
}