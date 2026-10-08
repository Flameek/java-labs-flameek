package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        assertTrue(CourseToolkit.isEven(8));
    }

    @Test
    void returnsFalseForOddNumber() {
        assertFalse(CourseToolkit.isEven(7));
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        assertTrue(CourseToolkit.isEven(-8));
    }

    @Test
    void returnsTrueForPrimeNumber() {
        assertTrue(CourseToolkit.isPrime(13));
    }

    @Test
    void returnsTrueForTwo() {
        assertTrue(CourseToolkit.isPrime(2));
    }

    @Test
    void returnsFalseForCompositeNumber() {
        assertFalse(CourseToolkit.isPrime(12));
    }

    @Test
    void returnsFalseForOne() {
        assertFalse(CourseToolkit.isPrime(1));
    }

    @Test
    void returnsFalseForPrimeSquare() {
        assertFalse(CourseToolkit.isPrime(49));
    }

    @Test
    void returnsTrueForPalindrome() {
        assertTrue(CourseToolkit.isPalindrome("level"));
    }

    @Test
    void returnsFalseForNonPalindrome() {
        assertFalse(CourseToolkit.isPalindrome("hello"));
    }

    @Test
    void palindromeComparisonIsCaseSensitive() {
        assertFalse(CourseToolkit.isPalindrome("Level"));
    }

    @Test
    void nullPalindromeThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> CourseToolkit.isPalindrome(null)
        );
    }

    @Test
    void returnsCorrectAverage() {
        assertEquals(20.0, CourseToolkit.average(new int[]{10, 20, 30}));
    }

    @Test
    void returnsFractionalAverage() {
        assertEquals(1.5, CourseToolkit.average(new int[]{1, 2}));
    }

    @Test
    void returnsCorrectAverageForNegativeNumbers() {
        assertEquals(-20.0, CourseToolkit.average(new int[]{-10, -20, -30}));
    }

    @Test
    void averageDoesNotModifyInputArray() {
        int[] values = {1, 2, 3};

        CourseToolkit.average(values);

        assertArrayEquals(new int[]{1, 2, 3}, values);
    }

    @Test
    void averageWithNullThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> CourseToolkit.average(null)
        );
    }

    @Test
    void averageWithEmptyArrayThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> CourseToolkit.average(new int[]{})
        );
    }
}
