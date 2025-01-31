package com.cache.core.datatypes;

public class IntegerData implements Data<Integer> {
    private final int value;

    public IntegerData(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    };

    @Override
    public DataType getType() {
        return DataType.INTEGER;
    }
}
