package com.example.spellcastingsystem.be;

public class Spell {
    public String name;
    public int cost;
    public int damage;

    public Spell(String name, int cost, int damage) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
    }

    public static class FieldNames {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String COST = "cost";
        public static final String DAMAGE = "damage";
    }
}
