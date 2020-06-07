package htw.teamsimon.serverapplication;

import htw.teamsimon.serverapplication.models.AuthenticatedUserList;
import htw.teamsimon.serverapplication.models.UserAuthenticator;
import htw.teamsimon.serverapplication.models.UserModel;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthenticationController {

    @GetMapping("/authenticate")
    @ResponseBody
    public String getToken(@RequestParam(name = "name") String name, @RequestParam String password) {
        UserAuthenticator userAuthenticator = new UserAuthenticator();
        UserModel user = userAuthenticator.authenticateUser(name,password);
        if(user != null) {
            TokenHelper tokenHelper = new TokenHelper();
            String token = tokenHelper.generateToken();
            AuthenticatedUserList authenticatedUserList = AuthenticatedUserList.getInstance();
            authenticatedUserList.addUser(token,user);
            return ("User: "+user+ " Authenticated! Key: "+token);
        } else return "Invalid Credentials";


    }
}
