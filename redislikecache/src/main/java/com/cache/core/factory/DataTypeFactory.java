package com.cache.core.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.cache.core.datatypes.BooleanData;
import com.cache.core.datatypes.Data;
import com.cache.core.datatypes.DoubleData;
import com.cache.core.datatypes.IntegerData;
import com.cache.core.datatypes.StringData;
import com.cache.exceptions.InvalidDataError;

public class DataTypeFactory {
    private final Map<Class<?>, Function<Object, Data>> registry = new HashMap<>();

    public DataTypeFactory() {
        // Register primitive wrapper types
        register(String.class, value -> new StringData((String) value));
        register(Boolean.class, value -> new BooleanData((boolean) value));
        register(Integer.class, value -> new IntegerData((int) value));
        register(Double.class, value -> new DoubleData((double) value));
    }

    public void register(Class<?> type, Function<Object, Data> creator) {
        registry.put(type, creator);
    }

    public Data ofValue(Object value) throws InvalidDataError {
        if (value == null) {
            throw new InvalidDataError("Cannot create Data from null value.");
        }

        Function<Object, Data> creator = registry.get(value.getClass());
        if (creator == null) {
            throw new InvalidDataError("Unsupported DataType: " + value.getClass().getSimpleName());
        }

        return creator.apply(value);
    }

}