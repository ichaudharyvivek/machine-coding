package com.db.models;

import lombok.Data;

@Data
public class Column {
    private String columnName;
    private Type columnType;
    private Boolean isPrimary = false;

    enum Type {
        INT, STRING, BOOLEAN
    }

}
