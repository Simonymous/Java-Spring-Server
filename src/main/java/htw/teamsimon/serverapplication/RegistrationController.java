package htw.teamsimon.serverapplication;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import htw.teamsimon.serverapplication.models.UserAuthenticator;
import htw.teamsimon.serverapplication.models.UserModel;

@RestController
public class RegistrationController {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParameter(MissingServletRequestParameterException exception) {
        return exception.getMessage();
    }

    @GetMapping("/register")
    @ResponseBody
    public String getAccount(@RequestParam(name = "name") String name, @RequestParam String password) {
        UserAuthenticator userAuthenticator = new UserAuthenticator();
        ArrayList<UserModel> userList = userAuthenticator.getUsers();

        AtomicBoolean userExists = new AtomicBoolean(false);

        userList.forEach(u -> {
            if (u.getName().equals(name)) {
                userExists.set(true);
            }
        });

        if (userExists.get() == false) {
            RegistrationHelper registerHelper = new RegistrationHelper();
            return registerHelper.registerUser(name, password);
        } else
            return "Username already exists";
    }
}
