package htw.teamsimon.serverapplication;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import htw.teamsimon.serverapplication.models.AuthenticatedUserList;

@RestController
public class PrimeController {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException exception) {
        return "Number must be int";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParameter(MissingServletRequestParameterException exception) {
        return exception.getMessage();
    }

    @GetMapping("/prime")
    @ResponseBody
    public String getPrime(@RequestParam(name = "number") int number, @RequestParam(name = "token") String token) {
        AuthenticatedUserList authenticatedUserList = AuthenticatedUserList.getInstance();
        if (authenticatedUserList.getUser(token) != null) {
            if (number <= 0) {
                return "Number must be bigger than zero";
            }

            PrimeHelper primeHelper = new PrimeHelper();
            boolean isPrime = primeHelper.isPrime(number);

            return String.format("Number %s tested %s to being prime", number, Boolean.toString(isPrime));
        }

        return "Invalid Token";
    }
}
