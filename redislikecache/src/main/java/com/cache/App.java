package com.cache;

import java.util.Map;

import com.cache.cli.CLIProcessor;
import com.cache.policy.EvictionPolicy;
import com.cache.policy.LRUEvictionPolicy;
import com.cache.storage.HashMapStorage;
import com.cache.storage.Storage;

/**
 * Main application class that initializes and runs a cache system with a
 * Command Line Interface using.
 */
public class App {
    public static void main(String[] args) {
        int capacity = 2;
        EvictionPolicy<String> evictionPolicy = new LRUEvictionPolicy<>();
        Storage<String, Map<String, Object>> storage = new HashMapStorage<>();
        Cache<String, Map<String, Object>> cache = new Cache<>(capacity, evictionPolicy, storage);

        CLIProcessor processor = new CLIProcessor(cache);
        processor.begin();

    }
}
