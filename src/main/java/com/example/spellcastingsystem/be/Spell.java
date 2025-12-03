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
}
