package com.cache.core.datatypes;

public class DoubleData implements Data<Double> {
    private final double value;

    public DoubleData(double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    };

    @Override
    public DataType getType() {
        return DataType.DOUBLE;
    }

}