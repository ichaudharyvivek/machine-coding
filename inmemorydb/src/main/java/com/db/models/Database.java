package com.db.models;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class Database {
    private String name;
    private final Map<String, Table> tableMap = new HashMap<>();

    public Database(String name) {
        this.name = name;
    }
}
