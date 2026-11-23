package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NormalizationStrategyTest {
    private static final double DELTA = 1e-9;

    @Test
    void minMaxPreservesOrderAndLeavesInputUnchanged() {
        double[] input = {0, 2, -2};
        double[] result = new MinMaxNormalization().normalize(input);
        assertArrayEquals(new double[]{0.5, 1, 0}, result, DELTA);
        assertArrayEquals(new double[]{0, 2, -2}, input, DELTA);
        assertNotSame(input, result);
    }

    @Test
    void minMaxReturnsZerosForAConstantVector() {
        assertArrayEquals(new double[]{0, 0}, new MinMaxNormalization().normalize(new double[]{4, 4}), DELTA);
    }

    @Test
    void minMaxHandlesARangeLargerThanDoubleMaxValue() {
        double[] input = {-Double.MAX_VALUE, 0, Double.MAX_VALUE};
        assertArrayEquals(new double[]{0, 0.5, 1}, new MinMaxNormalization().normalize(input), DELTA);
    }

    @Test
    void meanCenteringSubtractsTheMeanWithoutChangingTheInput() {
        double[] input = {1, 2, 6};
        double[] result = new MeanCenteringNormalization().normalize(input);
        assertArrayEquals(new double[]{-2, -1, 3}, result, DELTA);
        assertArrayEquals(new double[]{1, 2, 6}, input, DELTA);
        assertNotSame(input, result);
    }

    @Test
    void meanCenteringReturnsZerosForAConstantVector() {
        double[] input = {Double.MAX_VALUE, Double.MAX_VALUE};
        assertArrayEquals(new double[]{0, 0}, new MeanCenteringNormalization().normalize(input), DELTA);
    }

    @Test
    void bothStrategiesReturnAnEmptyVectorForEmptyInput() {
        double[] input = {};
        double[] minMax = new MinMaxNormalization().normalize(input);
        double[] centered = new MeanCenteringNormalization().normalize(input);
        assertArrayEquals(input, minMax, DELTA);
        assertArrayEquals(input, centered, DELTA);
        assertNotSame(input, minMax);
        assertNotSame(input, centered);
    }

    @Test
    void bothStrategiesRejectNonFiniteElements() {
        double[] input = {Double.NaN, Double.POSITIVE_INFINITY};
        assertThrows(IllegalArgumentException.class, () -> new MinMaxNormalization().normalize(input));
        assertThrows(IllegalArgumentException.class, () -> new MeanCenteringNormalization().normalize(input));
    }

    @Test
    void processorUsesTheInjectedStrategy() {
        double[] input = {2, 4};
        FeatureProcessor minMax = new FeatureProcessor(new MinMaxNormalization());
        FeatureProcessor centered = new FeatureProcessor(new MeanCenteringNormalization());
        assertArrayEquals(new double[]{0, 1}, minMax.process(input), DELTA);
        assertArrayEquals(new double[]{-1, 1}, centered.process(input), DELTA);
    }
}
