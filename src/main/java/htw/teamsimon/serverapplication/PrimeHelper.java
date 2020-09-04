package htw.teamsimon.serverapplication;

public class PrimeHelper {

    public PrimeHelper() {
    }

    public String isPrime(int number) {
        for (int i = 2; i <= number / 2; ++i) {
            if (number % i == 0) {
                return String.format("Number %s is not prime", number);
            }
        }

        return String.format("Number %s is prime", number);
    }
}
