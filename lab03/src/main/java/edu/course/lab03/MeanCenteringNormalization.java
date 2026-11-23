package edu.course.lab03;

public class MeanCenteringNormalization implements NormalizationStrategy {
    @Override
    public double[] normalize(double[] values) {
        double[] result = new double[values.length];
        if (values.length == 0) {
            return result;
        }

        double sum = 0;
        double maxMagnitude = 0;
        for (double value : values) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Vector elements must be finite");
            }
            sum += value;
            maxMagnitude = Math.max(maxMagnitude, Math.abs(value));
        }

        double mean = sum / values.length;
        if (!Double.isFinite(sum)) {
            // Summing scaled values keeps the mean finite even if the sum overflowed.
            double scaledSum = 0;
            for (double value : values) {
                scaledSum += value / maxMagnitude;
            }
            mean = scaledSum / values.length * maxMagnitude;
        }
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] - mean;
        }
        return result;
    }
}
