package htw.teamsimon.serverapplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import htw.teamsimon.serverapplication.models.AuthenticatedUserList;

@RestController
public class PrimeController {

    @GetMapping("/prime")
    @ResponseBody
    public String getPrime(@RequestParam(name = "number") int number, @RequestParam(name = "token") String token) {
        AuthenticatedUserList authenticatedUserList = AuthenticatedUserList.getInstance();
        if (authenticatedUserList.getUser(token) != null) {
            PrimeHelper primeHelper = new PrimeHelper();
            boolean isPrime = primeHelper.isPrime(number);
            return Boolean.toString(isPrime);
        } else {
            return "Invalid Token!";
        }

    }
}
