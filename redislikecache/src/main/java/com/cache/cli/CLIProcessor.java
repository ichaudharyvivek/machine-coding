package com.cache.cli;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cache.Cache;
import com.cache.core.datatypes.Data;
import com.cache.core.factory.DataTypeFactory;
import com.cache.exceptions.InvalidDataException;
import com.cache.exceptions.NotFoundException;

public class CLIProcessor {
    private final DataTypeFactory dataTypeFactory;
    private final Cache<String, Map<String, Data<?>>> cache;

    public CLIProcessor(DataTypeFactory dataTypeFactory, Cache<String, Map<String, Data<?>>> cache) {
        this.dataTypeFactory = dataTypeFactory;
        this.cache = cache;
    }

    public void begin() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine().trim();
            String[] commands = line.split(" ");
            if (commands[0].equalsIgnoreCase("exit")) {
                break;
            }

            try {
                process(commands);
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }

        return;
    }

    private void process(String[] commands) throws NotFoundException, InvalidDataException {
        String cacheCommand = commands[0].toLowerCase();
        switch (cacheCommand) {
            case "put":
                processPut(commands);
                break;
            case "get":
                processGet(commands);
            default:
                throw new IllegalArgumentException("Invalid input type.");
        }
    }

    private void processGet(String[] commands) throws NotFoundException {
        if (commands.length < 2) {
            throw new IllegalArgumentException("Insufficient number of inputs.");
        }

        int[] index = { 0 };
        String namespace = commands[1];
        Map<String, Data<?>> values = cache.get(namespace);
        values.forEach((key, value) -> {
            System.out.print(String.format("%s: %s", key, value.getValue()));
            if (index[0] != values.size() - 1) {
                System.out.print(", ");
                index[0]++;
            }
        });
        System.out.println();
    }

    private void processPut(String[] commands) throws InvalidDataException {
        String namespace = "";
        Map<String, Data<?>> values = new HashMap<>();

        if (commands.length < 4) {
            throw new IllegalArgumentException("Insufficient number of inputs.");
        }

        namespace = commands[1];
        for (int i = 2; i < commands.length; i += 2) {
            String key = commands[i];
            Data<?> value = parseValue(commands[i + 1]);
            values.put(key, value);
        }

        cache.put(namespace, values);
        System.out.println("Success");
    }

    private Data<?> parseValue(String value) throws InvalidDataException {
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

        return dataTypeFactory.ofValue(dataTypObject);
    }

}
