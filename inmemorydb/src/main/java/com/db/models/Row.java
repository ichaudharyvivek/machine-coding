package com.db.models;

import java.util.Map;

import lombok.Data;

@Data
public class Row {
    private Integer rowId;
    private Map<Column, Object> columnData;
}
