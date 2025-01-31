package com.cache.core.datatypes;

public class StringData implements Data<String> {
    private final String value;

    public StringData(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    };

    @Override
    public DataType getType() {
        return DataType.STRING;
    }

}