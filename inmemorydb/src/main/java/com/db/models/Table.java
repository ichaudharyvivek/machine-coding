package com.db.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Table {
    private String tableName;
    private List<Row> rows = new ArrayList<>();
    private Map<String, Column> columns = new HashMap<>();

    public Table(String tableName, Map<String, Column> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public void insert(Row row) {
        System.out.println("Tried to insert into table: " + tableName);
    }

    public void read() {
        System.out.println("Tried to read the table: " + tableName);
    }

    public void update(Row row) {
        System.out.println("Tried to update the table: " + tableName);
    }

    public void delete(Row row) {
        System.out.println("Tried to delete the table: " + tableName);
    }

    public void truncate() {
        System.out.println("Tried to truncate into table: " + tableName);
    }
}
