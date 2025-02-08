package com.cache.cli;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cache.Cache;
import com.cache.exceptions.InvalidDataException;
import com.cache.exceptions.NotFoundException;

/**
 * Command-line interface processor for cache operations.
 * Supports operations: put, get, delete, search, and keys.
 */
public class CLIProcessor {
    private final Cache<String, Map<String, Object>> cache;

    /**
     * Constructs a CLIProcessor with the specified cache implementation.
     * 
     * @param cache The {@link Cache} instance to process operations on
     */
    public CLIProcessor(Cache<String, Map<String, Object>> cache) {
        this.cache = cache;
    }

    /**
     * Starts the CLI processor and handles user input until exit command.
     * 
     * Supported commands:
     * put <namespace> <key1> <value1> [<key2> <value2> ...] - Store key-value pairs
     * get <namespace> - Retrieve all values for a namespace
     * delete <namespace> - Remove a namespace
     * search <key> <value> - Find namespaces with matching key-value
     * keys - List all namespaces
     * exit - Terminate the program
     */
    public void begin() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            String[] commands = line.split(" ");
            if (commands[0].equalsIgnoreCase("exit")) {
                break;
            }

            try {
                process(commands);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getClass().getSimpleName()
                        + ": Please enter input as <property1> <attribute1> <property2> <attribute2> ...");

            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());

            } finally {
                System.out.println();

            }
        }

        sc.close();
        return;
    }

    /**
     * Processes the put command to store key-value pairs in the specified
     * namespace.
     * 
     * @param commands Array containing namespace and key-value pairs
     * @throws InvalidDataException If value type is invalid
     * @throws NotFoundException    If namespace not found for updates
     */
    private void process(String[] commands) throws NotFoundException, InvalidDataException {
        String cacheCommand = commands[0].toLowerCase();
        switch (cacheCommand) {
            case "put":
                processPut(commands);
                break;
            case "get":
                processGet(commands);
                break;
            case "delete":
                processDelete(commands);
                break;
            case "search":
                processSearch(commands);
                break;
            case "keys":
                processKeys(commands);
                break;
            default:
                throw new IllegalArgumentException("Invalid input type.");
        }
    }

    private void processKeys(String[] commands) {
        List<String> keysList = cache.keys();
        for (int i = 0; i < keysList.size(); i++) {
            System.out.print(keysList.get(i));
            if (i != keysList.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println();
    }

    private void processGet(String[] commands) throws NotFoundException {
        if (commands.length < 2) {
            throw new IllegalArgumentException("Insufficient number of inputs.");
        }

        int[] index = { 0 };
        String namespace = commands[1];
        Map<String, Object> values = cache.get(namespace);

        values.forEach((key, value) -> {
            System.out.print(String.format("%s: %s", key, value));
            if (index[0] != values.size() - 1) {
                System.out.print(", ");
                index[0]++;
            }
        });
        System.out.println();
    }

    private void processPut(String[] commands) throws InvalidDataException, NotFoundException {
        String namespace = "";
        Map<String, Object> attributeMap = new HashMap<>();

        if (commands.length < 4) {
            throw new IllegalArgumentException("Insufficient number of inputs.");
        }

        namespace = commands[1];
        for (int i = 2; i < commands.length; i += 2) {
            String key = commands[i];
            Object value = parseValue(commands[i + 1]);
            attributeMap.put(key, value);
        }

        cache.put(namespace, attributeMap);
        System.out.println("Success");
    }

    private void processDelete(String[] commands) throws NotFoundException {
        if (commands.length < 2) {
            throw new IllegalArgumentException("Insufficient number of inputs.");
        }

        String namespace = commands[1];
        cache.delete(namespace);
        System.out.println("Deleted");
    }

    private void processSearch(String[] commands) throws InvalidDataException {
        if (commands.length < 2) {
            throw new IllegalArgumentException("Insufficient number of inputs.");
        }

        String key = commands[1];
        Object value = parseValue(commands[2]);
        Map<String, Object> valueMap = Map.of(key, value);
        List<String> foundKeys = cache.search(valueMap);
        for (int i = 0; i < foundKeys.size(); i++) {
            System.out.print(foundKeys.get(i));
            if (i != foundKeys.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    private Object parseValue(String value) throws InvalidDataException {
        Object dataTypObject;
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            dataTypObject = Boolean.parseBoolean(value);

        } else if (value.matches("\\d+\\.\\d+")) {
            dataTypObject = Double.parseDouble(value);

        } else if (value.matches("\\d+")) {
            dataTypObject = Integer.parseInt(value);

        } else {
            dataTypObject = value;

        }

        return dataTypObject;
    }

}