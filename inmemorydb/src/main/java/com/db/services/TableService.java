package com.db.services;

import java.util.List;
import java.util.Map;

import com.db.models.Column;
import com.db.models.Row;
import com.db.models.Table;

public class TableService implements ITableRepo {
    private Table table;

    public TableService(Table table) {
        this.table = table;
    }

    @Override
    public boolean insertOne(Integer id, Row row) {
        if (!validate(row)) {
            return false;
        }

        table.getRows().put(id, row);
        return true;
    }

    @Override
    public void findAll() {
        Map<Integer, Row> rows = table.getRows();
        print(rows);
    }

    @Override
    public void findById(Integer id) {
        Map<Integer, Row> rows = table.getRows();

        if (rows.containsKey(id)) {
            print(Map.of(id, rows.get(id)));
        }
    }

    @Override
    public void updateById(Integer id, Map<String, Object> update) {
        Map<Integer, Row> rows = table.getRows();

        if (!rows.containsKey(id)) {
            throw new IllegalArgumentException("Id not found");
        }

        Row row = rows.get(id);
        Map<String, Object> rowData = row.getRowData();

        for (Map.Entry<String, Object> entry : update.entrySet()) {
            String key = entry.getKey();
            Object newValue = entry.getValue();

            if (!rowData.containsKey(key)) {
                throw new IllegalArgumentException("Column '" + key + "' not found.");
            }

            // Update the row's data
            rowData.put(key, newValue);
        }
    }

    private boolean validate(Row row) {
        if (table.getColumns().size() != row.getRowData().size()) {
            throw new IllegalArgumentException("Data not formatted correctly");
        }

        Map<String, Object> data = row.getRowData();
        for (Column column : table.getColumns()) {
            Object value = data.get(column.getName());

            if (value == null) {
                throw new IllegalArgumentException("Column '" + column.getName() + "' not found.");
            }

            switch (column.getType()) {
                case INTEGER:
                    if (!(value instanceof Integer)) {
                        throw new IllegalArgumentException("Invalid type used.");
                    }
                    break;
                case STRING:
                    if (!(value instanceof String)) {
                        throw new IllegalArgumentException("Invalid type used.");
                    }
                    break;
                case BOOLEAN:
                    if (!(value instanceof Boolean)) {
                        throw new IllegalArgumentException("Invalid type used.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported column type: " + column.getType());
            }
        }

        return true;
    }

    private void print(Map<Integer, Row> rows) {
        // Print all rows
        for (Map.Entry<Integer, Row> row : rows.entrySet()) {
            Row current = row.getValue();
            Map<String, Object> data = current.getRowData();

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                System.out.print(entry.getValue() + "\t");
            }
            System.out.println();
        }

        System.out.println();
    }

}
