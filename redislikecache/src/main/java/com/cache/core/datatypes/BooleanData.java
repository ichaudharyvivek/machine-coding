package com.cache.core.datatypes;

public class BooleanData implements Data<Boolean> {
    private final boolean value;

    public BooleanData(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    };

    @Override
    public DataType getType() {
        return DataType.BOOLEAN;
    }

}