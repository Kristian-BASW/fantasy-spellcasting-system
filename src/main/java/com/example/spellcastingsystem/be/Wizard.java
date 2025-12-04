package com.example.spellcastingsystem.be;

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

    /**
     * Empty constructor
     */
    public Wizard(){}

    /**
     * Constructor with name and wizardTypeId
     * @param name
     * @param wizardTypeId
     */
    public Wizard(String name, int wizardTypeId){
        this.name = name;
        this.level = 1;
        this.experience = 0;
        this.wizardTypeId = wizardTypeId;
    }

    public static Wizard fromDb(ResultSet rs) throws SQLException {
        var wizard = new Wizard();
        wizard.id = rs.getInt(0);
        wizard.name =  rs.getString(1);
        wizard.experience = rs.getInt(2);
        wizard.level = rs.getInt(3);
        wizard.experience = rs.getInt(4);
        return wizard;
    }

    public static class FieldNames {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String WIZARD_TYPE_ID = "wizard_type_id";
        public static final String LEVEL = "level";
        public static final String EXPERIENCE = "experience";
    }

}


