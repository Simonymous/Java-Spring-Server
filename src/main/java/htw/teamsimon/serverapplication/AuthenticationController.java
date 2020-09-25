package htw.teamsimon.serverapplication;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import htw.teamsimon.serverapplication.models.AuthenticatedUserList;
import htw.teamsimon.serverapplication.models.UserAuthenticator;
import htw.teamsimon.serverapplication.models.UserModel;

@RestController
public class AuthenticationController {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParameter(MissingServletRequestParameterException exception) {
        return exception.getMessage();
    }

    @GetMapping("/authenticate")
    @ResponseBody
    public String getToken(@RequestParam(name = "name") String name, @RequestParam(name = "password") String password) {
        UserAuthenticator userAuthenticator = new UserAuthenticator();
        UserModel user = userAuthenticator.authenticateUser(name, password);

        if (user != null) {
            TokenHelper tokenHelper = new TokenHelper();
            String token = tokenHelper.generateToken();

            AuthenticatedUserList authenticatedUserList = AuthenticatedUserList.getInstance();
            authenticatedUserList.addUser(token, user);

            return String.format("User: %s authenticated. Token: %s", user, token);
        } else
            return "Invalid Credentials";
    }
}
