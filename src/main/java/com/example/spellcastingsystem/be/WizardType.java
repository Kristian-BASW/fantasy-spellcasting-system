package com.example.spellcastingsystem.be;
import java.util.List;

public class WizardType {
    public int id;
    public String name;
    public List<Spell> initialSpells;
    public WizardType(String name, List<Spell> initialSpells) {
        this.name = name;
        this.initialSpells = initialSpells;
    }
}
