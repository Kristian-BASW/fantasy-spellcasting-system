package com.example.spellcastingsystem.be;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WizardType {
    public int id;
    public String name;
    public List<Spell> initialSpells;

    public WizardType() {}

    public WizardType(String name, List<Spell> initialSpells) {
        this.name = name;
        this.initialSpells = initialSpells;
    }

    public static WizardType fromDb(ResultSet rs) throws SQLException {
        WizardType wizardType = new WizardType();
        wizardType.id = rs.getInt(0);
        wizardType.name = rs.getString(1);
        return wizardType;
    }

    public static class FieldNames {
        public static final String ID = "id";
        public static final String NAME = "name";
    }
}
