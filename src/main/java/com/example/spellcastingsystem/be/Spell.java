package com.example.spellcastingsystem.be;

import com.example.spellcastingsystem.be.constants.DatabaseConstants;

/**
 * A spell is something the wizard can learn and use against opponents
 */
public class Spell {
    public String name;
    public int cost;
    public int damage;

    /**
     * Constructor for creating a new Spell
     * @param name given name for the spell
     * @param cost how much mana it costs to use
     * @param damage how much damage the spell delivers to the receiver
     */
    public Spell(String name, int cost, int damage) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
    }

    /**
     * Query for SQL to create table, and it also checks whether it's already created
     */
    public static String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "+ DatabaseConstants.SPELLS_TABLE_NAME +" ("
            + Spell.FieldNames.ID +" INTEGER PRIMARY KEY,"
            + Spell.FieldNames.NAME +" nvarchar(200) NOT NULL,"
            + Spell.FieldNames.COST +" INTEGER NOT NULL,"
            + Spell.FieldNames.DAMAGE +" INTEGER NOT NULL"
            + ");";

    /**
     * Field names for Spell as String
     */
    public static class FieldNames {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String COST = "cost";
        public static final String DAMAGE = "damage";
    }
}
