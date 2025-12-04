package com.example.spellcastingsystem.be;

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

    public static class FieldNames {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String POOL_TYPE = "pool_type";
        public static final String FULL_POINTS = "full_points";
        public static final String WIZARD_ID = "wizard_id";
        public static final String CURRENT_POINTS = "current_points";
    }

}
