package com.example.spellcastingsystem.dal;

import java.sql.SQLException;
import java.util.List;

public interface ICrudDao<T> {
    void add(T item);
    void update(T item);
    void delete(int id);
    List<T> getAll();
    T get(int id);
}
