package com.db.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class Table {
    private String tableName;
    private final List<Column> columns = new ArrayList<>();
    private final Map<Integer, Row> rows = new HashMap<>();

    public Table(String tableName) {
        this.tableName = tableName;
    }
}
