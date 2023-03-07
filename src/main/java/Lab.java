public class Lab {

    public static String fizzBuzz(int number) {
        if ((number % 3 == 0) && (number % 5 == 0)) {
            // if divisible by both 3 and 5
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            // if divisible by 3
            return "Fizz";
        } else if (number % 5 == 0) {
            // if divisible 5
            return "Buzz";
        }
        else {
            // Will return a String with the number as its value
            return Integer.toString(number);
        }
    }
}
