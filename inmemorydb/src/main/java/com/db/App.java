package com.db;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.db.models.Column;
import com.db.models.Database;
import com.db.models.Row;
import com.db.models.Table;
import com.db.services.DatabaseService;
import com.db.services.TableService;

/**
 * Hello world!
 *
 */
public class App {
        public static void main(String[] args) {
                Database db = new Database("in-memory-db");
                DatabaseService dbService = new DatabaseService(db);

                List<Column> columns = List.of(
                                new Column("id", Column.Type.INTEGER, true),
                                new Column("student_name", Column.Type.STRING),
                                new Column("age", Column.Type.INTEGER));
                Table userTable = dbService.createTable("Users", columns);
                TableService userTableService = new TableService(userTable);

                Map<String, Object> row1 = new LinkedHashMap<>(Map.ofEntries(
                                Map.entry("id", 1), Map.entry("student_name", "Alice"), Map.entry("age", 22)));

                Map<String, Object> row2 = new LinkedHashMap<>(Map.ofEntries(
                                Map.entry("id", 2), Map.entry("student_name", "John"), Map.entry("age", 23)));

                Map<String, Object> row3 = new LinkedHashMap<>(Map.ofEntries(
                                Map.entry("id", 3), Map.entry("student_name", "Bob"), Map.entry("age", 24)));

                userTableService.insertOne(1, new Row(row1));
                userTableService.insertOne(2, new Row(row2));
                userTableService.insertOne(3, new Row(row3));
                userTableService.findAll();

                userTableService.findById(3);

                userTableService.updateById(3, Map.of("student_name", "Bibi"));
                userTableService.findAll();
        }
}
