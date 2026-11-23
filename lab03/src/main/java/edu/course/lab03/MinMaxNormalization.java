package edu.course.lab03;

public class MinMaxNormalization implements NormalizationStrategy {
    @Override
    public double[] normalize(double[] values) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (double value : values) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Vector elements must be finite");
            }
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        double[] result = new double[values.length];
        if (values.length == 0 || min == max) {
            return result;
        }

        // Halve both differences if the full range would overflow.
        double range = max - min;
        double scale = Double.isInfinite(range) ? 0.5 : 1.0;
        double scaledMin = min * scale;
        double scaledRange = max * scale - scaledMin;
        for (int i = 0; i < values.length; i++) {
            result[i] = (values[i] * scale - scaledMin) / scaledRange;
        }
        return result;
    }
}
