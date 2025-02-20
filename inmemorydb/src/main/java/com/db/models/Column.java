package com.db.models;

import lombok.Getter;

@Getter
public class Column {
    private String name;
    private Type type;
    private Boolean isPrimary;

    public enum Type {
        INTEGER, STRING, BOOLEAN
    }

    public Column(String name, Type type) {
        this(name, type, false);
    }

    public Column(String name, Type type, Boolean isPrimary) {
        this.name = name;
        this.type = type;
        this.isPrimary = isPrimary;
    }

}
