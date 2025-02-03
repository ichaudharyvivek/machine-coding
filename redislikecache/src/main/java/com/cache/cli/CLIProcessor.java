package com.cache.cli;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cache.Cache;
import com.cache.exceptions.InvalidDataException;
import com.cache.exceptions.NotFoundException;

public class CLIProcessor {
    private final Cache<String, Map<String, Object>> cache;

    public CLIProcessor(Cache<String, Map<String, Object>> cache) {
        this.cache = cache;
    }

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
                break;
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

    private void processPut(String[] commands) throws InvalidDataException {
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