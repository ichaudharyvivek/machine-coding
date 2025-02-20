package com.db.models;

import java.util.Map;

import lombok.Getter;

@Getter
public class Row {
    private final Map<String, Object> rowData;

    public Row(Map<String, Object> rowData) {
        this.rowData = rowData;
    }
}
