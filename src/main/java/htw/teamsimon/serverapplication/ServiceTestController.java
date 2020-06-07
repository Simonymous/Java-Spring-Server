package htw.teamsimon.serverapplication;

import htw.teamsimon.serverapplication.models.AuthenticatedUserList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceTestController {
    @GetMapping("/serviceTest")
    @ResponseBody
    public String getService(@RequestParam(name = "token") String token) {
        AuthenticatedUserList authenticatedUserList = AuthenticatedUserList.getInstance();
        if(authenticatedUserList.getUser(token) != null) {
            return "OK! "+authenticatedUserList.getUser(token);
        } else {
            return "No User found!";
        }

    }
}
