package com.example.spellcastingsystem.dal.implementation;

import com.example.spellcastingsystem.be.Pool;
import com.example.spellcastingsystem.be.Spell;
import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.be.WizardType;
import com.example.spellcastingsystem.be.constants.DatabaseConstants;

import java.sql.*;
import java.util.Objects;

public class DatabaseContext {

    public static Connection connect(){
        var url = "jdbc:sqlite:wizard_games.db";
        try {
            System.out.println("Connection to SQLite has been established.");
            return DriverManager.getConnection(DatabaseConstants.DATABASE_NAME);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void checkExistence() throws SQLException {
        DatabaseMetaData dbm = Objects.requireNonNull(connect()).getMetaData();

        ResultSet wizardTable = dbm.getTables(null, null, DatabaseConstants.WIZARDS_TABLE_NAME, null);
        if(wizardTable.next()){
            var sql = "CREATE TABLE IF NOT EXISTS "+ DatabaseConstants.WIZARDS_TABLE_NAME + " ("
                    + Wizard.FieldNames.ID +" INTEGER PRIMARY KEY,"
                    + Wizard.FieldNames.NAME + " nvarchar(200) NOT NULL,"
                    + Wizard.FieldNames.LEVEL +" INTEGER NOT NULL,"
                    + Wizard.FieldNames.EXPERIENCE + " INTEGER NOT NULL,"
                    + Wizard.FieldNames.WIZARD_TYPE_ID + " INTEGER NOT NULL,"
                    + ");";
            try {
                Objects.requireNonNull(DatabaseContext.connect()).createStatement().execute(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        ResultSet wizardTypeTable = dbm.getTables(null, null, DatabaseConstants.WIZARD_TYPES_TABLE_NAME, null);
        if(wizardTypeTable.next()){
            var sql = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.WIZARD_TYPES_TABLE_NAME + " ("
                    + WizardType.FieldNames.ID + " INTEGER PRIMARY KEY,"
                    + WizardType.FieldNames.NAME + " nvarchar(200) NOT NULL,"
                    + ");";
            try {
                Objects.requireNonNull(DatabaseContext.connect()).createStatement().execute(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        ResultSet spellsTable = dbm.getTables(null, null, DatabaseConstants.SPELLS_TABLE_NAME, null);
        if(spellsTable.next()){
            var sql = "CREATE TABLE IF NOT EXISTS "+ DatabaseConstants.SPELLS_TABLE_NAME +" ("
                    + Spell.FieldNames.ID +" INTEGER PRIMARY KEY,"
                    + Spell.FieldNames.NAME +" nvarchar(200) NOT NULL,"
                    + Spell.FieldNames.COST +" INTEGER NOT NULL,"
                    + Spell.FieldNames.DAMAGE +" INTEGER NOT NULL,"
                    + ");";
            try {
                Objects.requireNonNull(DatabaseContext.connect()).createStatement().execute(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        ResultSet poolsTable = dbm.getTables(null, null, DatabaseConstants.POOLS_TABLE_NAME, null);
        if(poolsTable.next()){
            var sql = "CREATE TABLE IF NOT EXISTS "+ DatabaseConstants.POOLS_TABLE_NAME +" ("
                    + Pool.FieldNames.ID + " INTEGER PRIMARY KEY,"
                    + Pool.FieldNames.NAME + " nvarchar(200) NOT NULL,"
                    + Pool.FieldNames.FULL_POINTS + " INTEGER NOT NULL,"
                    + Pool.FieldNames.CURRENT_POINTS + " INTEGER NOT NULL,"
                    + Pool.FieldNames.WIZARD_ID +" INTEGER NOT NULL,"
                    + Pool.FieldNames.POOL_TYPE + "NVARCHAR(30) NOT NULL"
                    + ");";
            try {
                Objects.requireNonNull(DatabaseContext.connect()).createStatement().execute(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
