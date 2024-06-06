package com.bcs.todolist.demotesting;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoTestingTest {
    private static DemoTesting testClass;

    @BeforeAll
    public static void setup() {
        testClass = new DemoTesting();
    }


    @Test
    public void numberIsDivisible() {
        // action
        String result1 = testClass.isDivisible(6, 3);
        String result2 = testClass.isDivisible(20, 5);

        // assertion
        assertEquals("Number 6 is divisible by number 3", result1);
        assertEquals("Number 20 is divisible by number 5", result2);
    }

    @Test
    public void numberIsNotDivisible() {
        // action
        String result = testClass.isDivisible(7, 3);

        // assertion
        assertEquals("Number 7 is not divisible by number 3", result);
    }

    @Test
    public void numberIsNotDivisibleByZero() {
        // assertions
        Exception exception = assertThrows(IllegalArgumentException.class, () -> testClass.isDivisible(7, 0));

        assertEquals("Cannot divide by 0", exception.getMessage());
    }
}
