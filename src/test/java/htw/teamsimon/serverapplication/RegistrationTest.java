package htw.teamsimon.serverapplication;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class RegistrationTest {

    final String baseUrl = "http://localhost:";

    @LocalServerPort
    final int definedPort = 8080;

    static String testUsername;

    @BeforeClass
    public static void setUpUsername() {
        Random random = new Random();
        testUsername = String.format("test%s", random.nextInt());
    }

    @Test
    @Order(1)
    public void registrationSuccessTest() throws URISyntaxException {

        String request = String.format("/register?name=%s&password=test", testUsername);
        String result = String.format("Registration of user %s successfull", testUsername);

        RestTemplate restTemplate = new RestTemplate();

        final String testUrl = baseUrl + definedPort + request;
        URI uri = new URI(testUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains(result));
    }

    @Test
    @Order(2)
    public void authenticationAfterRegistrationTest() throws URISyntaxException {
        String request = String.format("/authenticate?name=%s&password=test", testUsername);
        String result = String.format("User: UserModel{name='%s', password='test'} authenticated. Token:",
                testUsername);

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