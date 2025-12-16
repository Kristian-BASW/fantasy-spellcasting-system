package com.example.spellcastingsystem.be;

import com.example.spellcastingsystem.be.constants.DatabaseConstants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Wizard {
    public int id;
    public String name;
    public int level;
    public int experience;
    public List<Spell> spells;
    public int wizardTypeId;
    public HealthPool healthPool;
    public ManaPool manaPool;

    /**
     * Empty constructor
     */
    public Wizard(){}

    /**
     * Constructor to create a new Wizard
     * @param name given name for the Wizard
     * @param wizardTypeId foreign key to the wizardType
     */
    public Wizard(String name, int wizardTypeId){
        this.name = name;
        this.level = 1;
        this.experience = 0;
        this.wizardTypeId = wizardTypeId;
        this.manaPool = new ManaPool();
        this.healthPool = new HealthPool();
    }

    /**
     * Gives a wizard from a given ResultSet from SQL Database
     * @param rs The ResultSet which should be converted to object
     * @return a Wizard object
     * @throws SQLException If the get<type> fails (getInt(), getString())
     */
    public static Wizard fromDb(ResultSet rs) throws SQLException {
        var wizard = new Wizard();
        wizard.id = rs.getInt(1);
        wizard.name =  rs.getString(2);
        wizard.experience = rs.getInt(3);
        wizard.level = rs.getInt(4);
        wizard.experience = rs.getInt(5);
        return wizard;
    }

    /**
     * Query for SQL to create table, and it also checks whether it's already created
     */
    public static String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "+ DatabaseConstants.WIZARDS_TABLE_NAME + " ("
            + Wizard.FieldNames.ID +" INTEGER PRIMARY KEY,"
            + Wizard.FieldNames.NAME + " nvarchar(200) NOT NULL,"
            + Wizard.FieldNames.LEVEL +" INTEGER NOT NULL,"
            + Wizard.FieldNames.EXPERIENCE + " INTEGER NOT NULL,"
            + Wizard.FieldNames.WIZARD_TYPE_ID + " INTEGER NOT NULL"
            + ");";

    /**
     * Field names for Wizard as String
     */
    public static class FieldNames {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String WIZARD_TYPE_ID = "wizard_type_id";
        public static final String LEVEL = "level";
        public static final String EXPERIENCE = "experience";
    }

}


