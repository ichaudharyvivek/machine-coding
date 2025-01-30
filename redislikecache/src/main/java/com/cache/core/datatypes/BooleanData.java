package com.cache.core.datatypes;

public class BooleanData implements Data {
    private final boolean value;

    public BooleanData(boolean value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    };

    @Override
    public DataType getType() {
        return DataType.BOOLEAN;
    }

}