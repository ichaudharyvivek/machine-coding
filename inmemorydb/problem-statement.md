# Simple In-Memory SQL Database Challenge

## Problem Statement
Design a basic in-memory relational database that supports fundamental SQL operations. Focus on core functionality and clean implementation.

## Core Requirements
- In-memory storage (no persistence needed)
- Basic SQL query support
- Single-threaded operations (no concurrency handling required)
- Support for basic data types
- Simple table operations

## Supported Features

### Data Types
- INTEGER
- STRING (max 256 chars)
- DOUBLE
- BOOLEAN

### Database Operations
1. Create Table
2. Insert Row
3. Select Data
4. Delete Row
5. List Tables

## Required Functions

### 1. Table Operations
```sql
CREATE TABLE table_name (
    column1 datatype,
    column2 datatype
);

DROP TABLE table_name;
```

### 2. Data Operations
```sql
INSERT INTO table_name (column1, column2) 
VALUES (value1, value2);

SELECT column1, column2 
FROM table_name 
WHERE column1 = value;

DELETE FROM table_name 
WHERE column1 = value;
```

## Input Format
Commands should be entered one per line:
```
create_table students (id INTEGER, name STRING, gpa DOUBLE, active BOOLEAN)
insert students 1 "John Doe" 3.8 true
select students where gpa > 3.5
delete students where id = 1
list_tables
exit
```

## Output Format

### Select Query Results
```
| id | name     | gpa | active |
|----|----------|-----|--------|
| 1  | John Doe | 3.8 | true   |
```

### Command Responses
- "Table created successfully"
- "Row inserted successfully"
- "N rows deleted"
- List of table names for list_tables command
- Appropriate error messages

## Example Usage

### Input
```
create_table students (id INTEGER, name STRING, gpa DOUBLE, active BOOLEAN)
insert students 1 "John Doe" 3.8 true
insert students 2 "Jane Smith" 3.9 true
select students where gpa > 3.5
list_tables
```

### Expected Output
```
Table created successfully
Row inserted successfully
Row inserted successfully
| id | name       | gpa | active |
|----|------------|-----|--------|
| 1  | John Doe   | 3.8 | true   |
| 2  | Jane Smith | 3.9 | true   |
students
```

## Implementation Requirements

### 1. Core Classes
```java
class Table {
    String name;
    List<Column> columns;
    List<Row> rows;
}

class Column {
    String name;
    DataType type;
}

class Row {
    Map<String, Object> values;
}

class Database {
    Map<String, Table> tables;
}
```

### 2. Required Methods
```java
class Database {
    void createTable(String name, List<Column> columns);
    void insertRow(String tableName, List<Object> values);
    List<Row> select(String tableName, String condition);
    int delete(String tableName, String condition);
    List<String> listTables();
}
```

### 3. Code Organization
- Separate classes for different components
- Clear error handling
- Basic input validation
- Simple command parser
- Unit tests for core functionality

## Testing Requirements
1. Basic unit tests for:
   - Table creation
   - Data insertion
   - Simple queries
   - Delete operations
2. Error handling tests
3. Data type validation tests

## Bonus Features (Optional)
1. Support for ORDER BY in SELECT
2. Basic aggregations (COUNT, SUM)
3. Simple error messages
4. Input command history

## Limitations
1. No transaction support
2. No joins between tables
3. No indexes
4. Simple where clause conditions only
5. No concurrent access

## Evaluation Criteria
1. Code readability and organization
2. Proper error handling
3. Input validation
4. Test coverage
5. Documentation
