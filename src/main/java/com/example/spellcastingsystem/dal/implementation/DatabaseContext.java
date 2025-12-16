package com.example.spellcastingsystem.dal.implementation;

import com.example.spellcastingsystem.be.Pool;
import com.example.spellcastingsystem.be.Spell;
import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.be.WizardType;
import com.example.spellcastingsystem.be.constants.DatabaseConstants;

import java.sql.*;
import java.util.Objects;

public class DatabaseContext {

    private static Connection _connection;

    /**
     * Gives the connection to the database
     * If it is null or closed it creates a new connection
     * @return Connection to the database
     * @throws SQLException
     */
    public static synchronized Connection getConnection() throws SQLException {
        if (_connection == null || _connection.isClosed()) {
            System.out.println("Connection to SQLite has been established.");
            _connection = DriverManager.getConnection(DatabaseConstants.DATABASE_NAME);
        }

        return _connection;
    }

    /**
     * Checks if the table with the given tableName is created, if not
     * then it creates it
     * @param tableName TableName to check
     * @return true if created
     * @throws SQLException
     */
    public boolean checkTableExistence(String tableName) throws SQLException {
        DatabaseMetaData dbm = Objects.requireNonNull(getConnection()).getMetaData();

        ResultSet wizardTable = dbm.getTables(null, null, tableName, null);
        return wizardTable.next();
    }

    /**
     * Checks if all of the tables are created
     * (should be manipulated if more tables should be added)
     * @throws SQLException
     */
    public void checkExistence() throws SQLException {
        // Creating table for Wizard
        createTableIfNotExist(DatabaseConstants.WIZARDS_TABLE_NAME, Wizard.TABLE_CREATE);

        // Creating table for Wizard Types
        createTableIfNotExist(DatabaseConstants.WIZARD_TYPES_TABLE_NAME, WizardType.TABLE_CREATE);

        // Creating table for Spells
        createTableIfNotExist(DatabaseConstants.SPELLS_TABLE_NAME, Spell.TABLE_CREATE);

        // Creating table for Pools
        createTableIfNotExist(DatabaseConstants.POOLS_TABLE_NAME, Pool.TABLE_CREATE);

    }

    private void createTableIfNotExist(String tableName, String createQuery) throws SQLException {
        if(checkTableExistence(tableName) == false){
            try {
                Objects.requireNonNull(getConnection()).createStatement().execute(createQuery);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
