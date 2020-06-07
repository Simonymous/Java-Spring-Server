package htw.teamsimon.serverapplication;

import htw.teamsimon.serverapplication.models.UserAuthenticator;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthenticationController {

    @GetMapping("/authenticate")
    @ResponseBody
    public String getToken(@RequestParam(name = "name") String name, @RequestParam String password) {
        UserAuthenticator userAuthenticator = new UserAuthenticator();
        if(userAuthenticator.authenticateUser(name,password)) {
            TokenHelper tokenHelper = new TokenHelper();
            String token = tokenHelper.generateToken();
            return ("Name: " + name + ", Password: " + password+ ", Authenticated! Key: "+token);
        } else return "Invalid Credentials";


    }
}
