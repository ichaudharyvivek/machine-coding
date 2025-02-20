package com.db.services;

import java.util.List;

import com.db.models.Column;
import com.db.models.Database;
import com.db.models.Table;

public class DatabaseService {
    private Database database;

    public DatabaseService(Database database) {
        this.database = database;
    }

    public Table createTable(String tableName, List<Column> columns) {
        if (database.getTableMap().containsKey(tableName)) {
            throw new IllegalArgumentException("The table with name '" + tableName + "' already exists.");
        }

        Table table = new Table(tableName);
        table.getColumns().addAll(columns);
        return table;
    }

    public void deleteTable(String tableName) {
        if (!database.getTableMap().containsKey(tableName)) {
            throw new IllegalArgumentException("The table with name '" + tableName + "' already exists.");
        }

        database.getTableMap().remove(tableName);
    }
}
