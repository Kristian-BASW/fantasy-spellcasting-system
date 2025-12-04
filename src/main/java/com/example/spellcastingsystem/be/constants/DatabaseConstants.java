package com.example.spellcastingsystem.be.constants;

public final class DatabaseConstants {
    public final static String DATABASE_NAME = "jdbc:sqlite:wizard_game.db";
    public final static String WIZARDS_TABLE_NAME = "Wizards";
    public final static String WIZARD_TYPES_TABLE_NAME = "WizardTypes";
    public final static String POOLS_TABLE_NAME = "Pools";
    public final static String SPELLS_TABLE_NAME = "Spells";

    public static String SelectAll(String tableName) {
        return String.format("SELECT * FROM %s", tableName);
    }

    public static String SelectById(String tableName, int id) {
        return String.format("SELECT * FROM %s WHERE id = %s LIMIT 1", tableName, id);
    }
}