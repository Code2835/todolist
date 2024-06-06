package com.bcs.todolist.demotesting;

public class DemoTesting {
    // if number 'a' is divisible by divisor 'b', return "Number a is divisible by number b"
    // if number 'a' is not divisible by divisor 'b', return "Number a is not divisible by number b"
    // if divisor = 0 then throw IllegalArgumentException with message "Cannot divide by 0"
    public String isDivisible(int number, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Cannot divide by 0");
        }

        if (number % divisor == 0) {
            return String.format("Number %d is divisible by number %d", number, divisor);
        } else {
            return String.format("Number %d is not divisible by number %d", number, divisor);
        }
    }


}
