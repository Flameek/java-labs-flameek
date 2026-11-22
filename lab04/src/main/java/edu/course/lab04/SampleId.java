package edu.course.lab04;

import java.util.Objects;

public final class SampleId {
    private final String dataset;
    private final int index;

    public SampleId(String dataset, int index) {
        this.dataset = dataset;
        this.index = index;
    }

    public String getDataset() {
        return dataset;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object value) {
        return value instanceof SampleId other
                && index == other.index
                && Objects.equals(dataset, other.dataset);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(dataset) + index;
    }
}
