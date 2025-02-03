package com.cache.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.cache.exceptions.NotFoundException;

public class HashMapStorage<K, V> implements Storage<K, V> {
    private Map<K, V> cache;

    public HashMapStorage() {
        this.cache = new HashMap<>();
    }

    @Override
    public List<K> keys() {
        List<K> listOfKeys = new ArrayList<>();
        cache.forEach((iKey, iValue) -> {
            listOfKeys.add(iKey);
        });

        return listOfKeys;
    }

    @Override
    public V get(K key) throws NotFoundException {
        V found = cache.get(key);
        if (found == null) {
            throw new NotFoundException(String.format("Cannot 'get' - key '%s' doesn't exist.", key));
        }

        return found;
    }

    @Override
    public void delete(K key) throws NotFoundException {
        if (key == null) {
            throw new IllegalArgumentException("Cannot 'erase' - 'key' provided is null.");
        }

        if (!cache.containsKey(key)) {
            throw new NotFoundException(String.format("Cannot 'delete' - Key '%s' does not exists.", key));
        }

        cache.remove(key);
    }

    @Override
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Cannot 'put' - 'key' or 'value' provided is null.");
        }

        cache.put(key, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<K> search(V value) {
        List<K> keyList = new ArrayList<>();

        for (Map.Entry<K, V> entry : cache.entrySet()) {
            K key = (K) entry.getKey();
            V attributeMap = (V) entry.getValue();

            for (Map.Entry<String, Object> searchEntry : ((Map<String, Object>) value).entrySet()) {
                String searchKey = searchEntry.getKey();
                Object searchObj = searchEntry.getValue();

                if (((Map<String, Object>) attributeMap).containsKey(searchKey)
                        && Objects.equals(searchObj, ((Map<String, Object>) attributeMap).get(searchKey))) {
                    keyList.add(key);
                    break;
                }
            }
        }

        return keyList;
    }

}