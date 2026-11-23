package edu.course.lab03;

public interface NormalizationStrategy {
    /**
     * Returns a new normalized vector. Empty vectors produce empty results.
     * Input elements must be finite.
     */
    double[] normalize(double[] values);
}
