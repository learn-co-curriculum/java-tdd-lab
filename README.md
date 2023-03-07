# TDD Code-Along

## Learning Goals

- Practice TDD.

## Introduction

In this code-along lab, we will practice programming using test-driven
development.

Fork and clone this repository. When you do, you will see a file called
`Lab.java` in the `java-tdd-lab/src/main/java` directory. You will also see the
`LabTest.java` in the `java-tdd-lab/src/test/java` directory. All the unit
testing will be written in the `LabTest.java` and all the code that should be
tested will be in `Lab.java`.

Let's consider our "FizzBuzz" example from the previous lab, with one more
level of complexity. Say the user wants to specify the maximum number and have
the program print 1 to n (where n is the input). We will iterate over the
sequence of numbers and print out its FizzBuzz-based replacement. For example,
if we pass the method the integer 15, we would get an output like this:

```text
1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz
```

## Instructions

When you look at the files in this repository, you should see something that
looks like the solution to our last lab. Let's reuse that to integrate this new
functionality:

```java
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
```

```java
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

```

Follow along in this code-along to see how we can practice TDD.

## Test Driven Development Cycle

Now let's approach this new problem with TDD! Consider the following steps:

1. Write a simple test to test this functionality.
2. Run the test (which should fail on the first run since we haven't written any
   code yet).
3. Write the code to pass the test we just wrote.
4. Run the test again to see if it passed.
5. Repeat the process again.

Go ahead and add a test method to the `LabTest.java` file that looks like
this:

```java
@Test
void testSingularFizzBuzzStringList() {
    String expectedResult = "1";
    assertEquals(expectedResult, Lab.fizzBuzzStringList(1));
}
```

Now run just that test method. You should see it fail since we have not yet
written a method called `fizzBuzzStringList()` yet.

So let's write that method now! Create a method called `fizzBuzzStringList()`
that takes an integer parameter and returns a `String`.

```java
public static String fizzBuzzStringList(int maxNumber) {
    return "1";
}
```

The code above is not sustainable to the entire problem since it simply returns
a `String` with the value 1. But the point of TDD is to implement small pieces at
a time and then build and refactor them as you go.

If you run your test again, it should pass this time! Yippee! So now what?

According to the TDD cycle and steps above, you will repeat this process until
you have finished developing.

## The Disabled Annotation

Sometimes when we see our tests failing before we implement the code, we may
feel a little disheartened. We may just want to ignore that unit test for now
until we have implemented it.

If we decide we want to do that, we can use the `@Disabled` annotation. Go ahead
and add the annotation to the unit test we just wrote:

```java
@Disabled
@Test
void testSingularFizzBuzzStringList() {
    String expectedResult = "1";
    assertEquals(expectedResult, Lab.fizzBuzzStringList(1));
}
```

When you do this, you might also notice that it adds a new import statement too
to the test class:

```java
import org.junit.jupiter.api.Disabled;
```

Now run all the tests in `FizzBuzzTest` and see what happens.

![disable-test](https://curriculum-content.s3.amazonaws.com/java-mod-3/tdd-lab/disable-test.png)

Notice how it ran the other 4 tests and skipped over the one that we added the
`@Disabled` annotation to! This is a great annotation to use when we write a
test but have not yet implemented the code to go along with it yet. For the
purposes of this code-along on, and to show the process of test-driven
development, we will show the failed tests first and then add and remove the
`@Disabled` annotation.

**Important Note:** when you use this annotation, it is crucial that after you
implement the code that you remove the `@Disabled` annotation as we want to
ensure that all of our tests are passing and working as expected. You should
only really use this annotation when you are writing the code and ensuring that
it compiles and runs. Remove the `@Disabled` annotation when you are ready to
actually have it test your code.

Go ahead and remove the `@Disabled` annotation from the test.

## Repeat the TDD Steps

So let's go ahead and write another test:

```java
@Test
void testMediumFizzBuzzStringList() {
    String expectedResult = "1 2 Fizz 4 Buzz";
    assertEquals(expectedResult, Lab.fizzBuzzStringList(5));
}
```

If you run just this test, you'll see it fails since the code is currently only
considering one value. Since it fails, we will go ahead and add the `@Disabled`
annotation temporarily.

```java
@Disabled
@Test
void testMediumFizzBuzzStringList() {
    String expectedResult = "1 2 Fizz 4 Buzz";
    assertEquals(expectedResult, Lab.fizzBuzzStringList(5));
}
```

Now let's go back and refactor the code again!

```java
public static String fizzBuzzStringList(int maxNumber) {
    // Initialize result to an empty string
    String result = "";

    // Create a for loop that will iterate through numbers 1 to maxNumber inclusively
    for (int number = 1; number <= maxNumber; number++) {
        // Call the fizzBuzz method we created before
        result = result + fizzBuzz(number) + " ";
    }

    return result;
}
```

Before we continue any further in our TDD cycle, let's take a moment to just
walk through this code:

- The `maxNumber` parameter represents how many times we want to iterate
  calling the `fizzBuzz(int number)` method.
- We initialize the result `String` that we will return to an empty string, i.e.
  "".
- We will iterate through a `for` loop, initializing `number` to 1 since we want
  to print the FizzBuzz results for 1 to `maxNumber`. We'll also set the boolean
  expression to `number <= maxNumber` to include `maxNumber` in the resulting
  `String`.
  - Call the `fizzBuzz(int number)` method we implemented in the previous lab.
  - Assign `result` to `result + fizzBuzz(number) + " "` to create the list of
    FizzBuzz values separated by a space.
- Return the `result`. 

Now that we understand the code above, let's run the
`testMediumFizzBuzzStringList()` method again. Remove the `@Disabled` annotation
and run just that test method.

Uh-oh, looks like the test failed. Let's look at the differences to see why.
Click the "<Click to see difference>" link under the `AssertionFailedError`:

![fizzbuzz-difference](https://curriculum-content.s3.amazonaws.com/java-mod-1/tdd-lab/fizzbuzz-space.PNG)

It looks like the "Actual" result has an extra space at the end of the `String`.

Per TDD, we'll go back and refactor our method then!

```java
public static String fizzBuzzStringList(int maxNumber) {
    // Initialize result to an empty string
    String result = "";

    // Create a for loop that will iterate through numbers 1 to maxNumber inclusively
    for (int number = 1; number <= maxNumber; number++) {
        // If it is the last FizzBuzz value we are printing, don't add the space
        if (number == maxNumber) {
            result = result + fizzBuzz(number);
        } else {
            result = result + fizzBuzz(number) + " ";
        }
    }

    return result;
}
```

We can add a condition to make sure that the extra space is not added at the
end by checking for when `number` is equal to the `maxNumber`.

Now let's re-run the test again. This time, we should see it pass!

Go back and double check that the `testSingularFizzBuzzStringList()` method
still passes too. It should!

Great! The tests are passing! Let's add another test:

```java
@Test 
void testLargeFizzBuzzStringList() {
    String expectedResult = "1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz";
    assertEquals(expectedResult, Lab.fizzBuzzStringList(15));
}
```

Try running just this test and see what happens.

Wow, it passes! You don't have to refactor anything!

There is a very important observation to make here. Your test that validates the
processing of the array does not need to go through every single combination of
the "Fizzbuzz Scenarios", because it's re-using the `fizzBuzz()` method. So as
long as that method works properly _and_ the `fizzBuzzStringList()` method
accurately iterates through the `for` loop and passes the correct values to the
`fizzBuzz()` method, all the "FizzBuzz scenarios" will be covered successfully.

## Edge Cases

The last thing for us to do is to add unit tests to cover the potential edge
cases. For example, what should happen if the number 0 is passed to the
`fizzBuzzStringList()` method?

Let's add a test case for this and assume the returned `String` should be empty,
"", if that is the case!

```java
@Test
void testZeroFizzBuzzStringList() {     
    assertEquals("", Lab.fizzBuzzStringList(0));
}
```

If you run the test above, you will see that it passes! This is because in
the `fizzBuzzStringList()` method, we initialized the returned `String` to an
empty string:

```java
// Initialize result to an empty string
String result = "";
```

If this test failed though, we would go through the TDD cycle again with
refactoring the method in question until the test passed. Since it has passed,
we will move onto the next edge case.

Consider a negative number is passed as an argument to the `fizzBuzzStringList()`
method. We do not want to find the FizzBuzz-based replacements for negative
values in this case. Add another test case to handle this edge case and assume
the method will return an empty string here as well.

```java
@Test
void testNegativeFizzBuzzStringList() {
    assertEquals("", Lab.fizzBuzzStringList(-5));
}
```

When you execute this test, it will also pass.

It just so happened that these two edge cases have already been covered! Hooray!
Even though they didn't require any refactoring to the code, it is still best
practice to test all our edge cases when unit testing.

Now let's run our entire test suite! Double check that all 9 unit tests
pass as expected and turn in your `Lab.java` and `LabTest.java`
assignments.
