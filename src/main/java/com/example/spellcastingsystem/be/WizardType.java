package com.example.spellcastingsystem.be;
import com.example.spellcastingsystem.be.constants.DatabaseConstants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WizardType {
    public int id;
    public String name;
    public List<Spell> initialSpells;

    public WizardType() {}

    /**
     * Constructor to create a new WizardType
     * @param name Given name for the WizardType
     * @param initialSpells Spells which are initial for this specific WizardType
     */
    public WizardType(String name, List<Spell> initialSpells) {
        this.name = name;
        this.initialSpells = initialSpells;
    }

    /**
     * Gives a WizardType from a given ResultSet from SQL Database
     * @param rs The ResultSet which should be converted to object
     * @return a WizardType object
     * @throws SQLException If the get<type> fails (getInt(), getString())
     */
    public static WizardType fromDb(ResultSet rs) throws SQLException {
        WizardType wizardType = new WizardType();
        wizardType.id = rs.getInt(1);
        wizardType.name = rs.getString(2);
        return wizardType;
    }

    /**
     * Query for SQL to create table, and it also checks whether it's already created
     */
    public static String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.WIZARD_TYPES_TABLE_NAME + " ("
            + WizardType.FieldNames.ID + " INTEGER PRIMARY KEY,"
            + WizardType.FieldNames.NAME + " nvarchar(200) NOT NULL"
            + ");";

    /**
     * Field names for Wizard as String
     */
    public static class FieldNames {
        public static final String ID = "id";
        public static final String NAME = "name";
    }
}
