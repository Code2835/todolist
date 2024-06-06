package com.bcs.todolist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DemoTest {
    @DisplayName("Assert equals")
    @Test
    void testEquals() {
        assertEquals(4, 2 + 2);
    }

    @Test
    void testBoolean() {
        assertTrue(4 > 1);
        assertFalse(4 > 7);
    }

    @Test
    void testNull() {
        String test = "not empty";
        assertNotNull(test);
    }

    @Test
    void groupedAssertion() {
        String test = "abbdefgh";
        assertAll("String operations",
                () -> assertEquals("a", test.substring(0, 1)),
                () -> assertNotNull(test),
                () -> assertEquals(8, test.length())
        );
    }
}
