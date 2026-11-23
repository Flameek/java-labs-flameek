package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiscountPolicyTest {
    @Test
    void noDiscountReturnsTheOriginalPrice() {
        assertEquals(42.5, new NoDiscount().apply(42.5));
    }

    @Test
    void noDiscountRejectsNegativePrices() {
        assertThrows(IllegalArgumentException.class, () -> new NoDiscount().apply(-1));
    }

    @Test
    void percentDiscountReducesThePrice() {
        assertEquals(80.0, new PercentDiscount(20).apply(100), 1e-9);
    }

    @Test
    void percentDiscountAcceptsBoundaryPercentages() {
        assertEquals(100.0, new PercentDiscount(0).apply(100));
        assertEquals(0.0, new PercentDiscount(100).apply(100));
    }

    @Test
    void percentDiscountRejectsInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(-1));
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(101));
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(20).apply(-1));
    }

    @Test
    void calculatorUsesTheInjectedPolicy() {
        assertEquals(100.0, new PriceCalculator(new NoDiscount()).calculate(100));
        assertEquals(80.0, new PriceCalculator(new PercentDiscount(20)).calculate(100), 1e-9);
    }
}
