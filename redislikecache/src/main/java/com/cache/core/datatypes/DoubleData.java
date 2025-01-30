package com.cache.core.datatypes;

public class DoubleData implements Data {
    private final double value;

    public DoubleData(double value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    };

    @Override
    public DataType getType() {
        return DataType.DOUBLE;
    }

}