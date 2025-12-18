package com.example.spellcastingsystem.be.extenders;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SQLQueryString {
    private String _sqlString = "";

    public SQLQueryString(String sqlString) {
        this._sqlString = _sqlString + sqlString;
    }

    public String toString(){
        return this._sqlString;
    }
    public static SQLQueryString selectAll(String tableName) {
        return new SQLQueryString(String.format("SELECT * FROM %s", tableName));
    }

    public static SQLQueryString selectById(String tableName, int id) {
        return new SQLQueryString(String.format("SELECT * FROM %s WHERE id = %s LIMIT 1", tableName, id));
    }

    public static SQLQueryString selectWhereEqual(String tableName, List<String> columnNames) {
        String query = String.format("SELECT * FROM %s ", tableName);
        if(columnNames != null && !columnNames.isEmpty()){
            query += " WHERE";
        }
        assert columnNames != null;
        for(String columnName : columnNames) {
            query += String.format(" %s = ? AND", columnName);
        }
        if(query.substring(query.length() - 3).equals("AND")){
            query = query.substring(0, query.length() - 3);
        }

        return new SQLQueryString(query);
    }

    public SQLQueryString first(){
         return new SQLQueryString(_sqlString + " LIMIT 1");
    }

    public static SQLQueryString updateById(String tableName, int id, List<String> propertyNames) {
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
        if (propertyNames == null) {
            return null;
        }
        for (String pName : propertyNames) {
            sql.append(sql)
                    .append(pName)
                    .append(" = ")
                    .append(" ? ");
            // Checking if it is the last element in the list
            if(pName.equals(propertyNames.getLast()) == false) {
                sql.append(", ");
            }

        }
        sql.append(" WHERE id = ")
                .append(id);

        return new SQLQueryString(sql.toString());
    }

    public static SQLQueryString create(String tableName, List<String> propertyNames) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        if (propertyNames == null) {
            return null;
        }
        sql.append(String.join(", ", propertyNames))
                .append(") VALUES (")
                .append(String.join(", ", Collections.nCopies(propertyNames.size(), "?")))
                .append(")");
        return new SQLQueryString(sql.toString());
    }
}
