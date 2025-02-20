package com.db.services;

import java.util.Map;

import com.db.models.Row;

public interface ITableRepo {
    public boolean insertOne(Integer id, Row row);

    public void findAll();

    public void findById(Integer id);

    public void updateById(Integer id, Map<String, Object> update);
}
