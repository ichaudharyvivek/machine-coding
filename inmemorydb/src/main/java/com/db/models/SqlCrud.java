package com.db.models;

public interface SqlCrud {
    public void createDatabase(String dbName);

    public void createTable(Table table);

    public void insertIntoTable(Row row);

    public void displayTable(Table table);

}
