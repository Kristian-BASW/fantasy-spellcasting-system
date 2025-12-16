package com.example.spellcastingsystem.be.constants;

import java.util.Collections;
import java.util.List;

public final class DatabaseConstants {
    public final static String DATABASE_NAME = "jdbc:sqlite:wizard_game.db";
    public final static String WIZARDS_TABLE_NAME = "Wizards";
    public final static String WIZARD_TYPES_TABLE_NAME = "WizardTypes";
    public final static String POOLS_TABLE_NAME = "Pools";
    public final static String SPELLS_TABLE_NAME = "Spells";

    public static String selectAll(String tableName) {
        return String.format("SELECT * FROM %s", tableName);
    }

    public static String selectById(String tableName, int id) {
        return String.format("SELECT * FROM %s WHERE id = %s LIMIT 1", tableName, id);
    }

    public static String updateById(String tableName, int id, List<String> propertyNames) {
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

        return sql.toString();
    }

    public static String create(String tableName, List<String> propertyNames) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        if (propertyNames == null) {
            return null;
        }
        sql.append(String.join(", ", propertyNames))
                .append(") VALUES (")
                .append(String.join(", ", Collections.nCopies(propertyNames.size(), "?")))
                .append(")");
        return sql.toString();
    }
}