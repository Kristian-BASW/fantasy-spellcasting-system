package com.example.spellcastingsystem.be;

import java.util.List;

public class Wizard {
    int id;
    String name;
    int level;
    int experience;
    List<Spell> spells;


    public Wizard(String name){
        this.name = name;
        this.level = 1;
        this.experience = 0;
    }

}


