package com.example.spellcastingsystem.be;

import java.util.List;

public class Wizard {
    public int id;
    public String name;
    public int level;
    public int experience;
    public List<Spell> spells;
    public int wizardTypeId;


    public Wizard(String name, int wizardTypeId){
        this.name = name;
        this.level = 1;
        this.experience = 0;
        this.wizardTypeId = wizardTypeId;
    }

}


