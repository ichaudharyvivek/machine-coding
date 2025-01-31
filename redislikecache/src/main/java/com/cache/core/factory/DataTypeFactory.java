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
    private final Map<Class<?>, Function<Object, Data<?>>> registry = new HashMap<>();

    public DataTypeFactory() {
        // Wrappers to create objects dynamically
        register(String.class, value -> new StringData((String) value));
        register(Boolean.class, value -> new BooleanData((boolean) value));
        register(Integer.class, value -> new IntegerData((int) value));
        register(Double.class, value -> new DoubleData((double) value));
    }

    public <T> void register(Class<?> type, Function<Object, Data<?>> creator) {
        registry.put(type, creator);
    }

    @SuppressWarnings("unchecked")
    public <T> Data<T> ofValue(T value) throws InvalidDataError {
        if (value == null) {
            throw new InvalidDataError("Cannot create Data from null value.");
        }

        Function<Object, Data<?>> creator = registry.get(value.getClass());
        if (creator == null) {
            throw new InvalidDataError("Unsupported DataType: " + value.getClass().getSimpleName());
        }

        return (Data<T>) creator.apply(value);
    }

}