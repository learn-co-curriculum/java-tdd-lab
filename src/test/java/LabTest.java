import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LabTest {

    @Test
    void testFizzBuzzNotDivisible() {
        // Test to a number that is not divisible by 3 or 5
        assertEquals("7", Lab.fizzBuzz(7));
    }

    @Test
    void testFizzBuzzDivisibleBy3() {
        // Test a number that is divisible by 3 only
        assertEquals("Fizz", Lab.fizzBuzz(3));
    }

    @Test
    void testFizzBuzzDivisibleBy5() {
        // Test a number that is divisible by 5 only
        assertEquals("Buzz", Lab.fizzBuzz(5));
    }

    @Test
    void testFizzBuzzDivisibleBy3And5() {
        // Test a number that is divisible by both 3 and 5
        assertEquals("FizzBuzz", Lab.fizzBuzz(15));
    }
}
