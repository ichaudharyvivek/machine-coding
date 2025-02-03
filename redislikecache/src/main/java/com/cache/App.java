package com.cache;

import java.util.Map;

import com.cache.cli.CLIProcessor;
import com.cache.core.datatypes.Data;
import com.cache.core.factory.DataTypeFactory;
import com.cache.policy.EvictionPolicy;
import com.cache.policy.LRUEvictionPolicy;
import com.cache.storage.HashMapStorage;
import com.cache.storage.Storage;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int capacity = 2;
        DataTypeFactory dataTypeFactory = new DataTypeFactory();
        EvictionPolicy<String> evictionPolicy = new LRUEvictionPolicy<>();
        Storage<String, Map<String, Data<?>>> storage = new HashMapStorage<>();
        Cache<String, Map<String, Data<?>>> cache = new Cache<>(capacity, evictionPolicy, storage);

        CLIProcessor processor = new CLIProcessor(dataTypeFactory, cache);
        try {
            processor.begin();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
}
