package com.example.spellcastingsystem.be;

import com.example.spellcastingsystem.be.constants.DatabaseConstants;

public abstract class Pool {
    public int id;
    public String poolType;
    public int fullPoints;
    public int wizardId;
    public int currentPoints;
    public void add(int amount){
        this.currentPoints += amount;
        if(this.currentPoints >= this.fullPoints){
            this.currentPoints = this.fullPoints;
        }
    }
    public void consume(int amount){
        this.currentPoints -= amount;
    }

    /**
     * Query for SQL to create table, and it also checks whether it's already created
     */
    public static String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "+ DatabaseConstants.POOLS_TABLE_NAME +" ("
            + Pool.FieldNames.ID + " INTEGER PRIMARY KEY,"
            + Pool.FieldNames.NAME + " nvarchar(200) NOT NULL,"
            + Pool.FieldNames.FULL_POINTS + " INTEGER NOT NULL,"
            + Pool.FieldNames.CURRENT_POINTS + " INTEGER NOT NULL,"
            + Pool.FieldNames.WIZARD_ID +" INTEGER NOT NULL,"
            + Pool.FieldNames.POOL_TYPE + " NVARCHAR(30) NOT NULL"
            + ");";

    public static class FieldNames {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String POOL_TYPE = "pool_type";
        public static final String FULL_POINTS = "full_points";
        public static final String WIZARD_ID = "wizard_id";
        public static final String CURRENT_POINTS = "current_points";
    }

}
