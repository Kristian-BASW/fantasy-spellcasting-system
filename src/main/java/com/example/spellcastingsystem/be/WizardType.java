package com.example.spellcastingsystem.be;

import java.util.List;

public class WizardType {
    String name;
    List<Spell> initialSpells;
    public WizardType(String name, List<Spell> initialSpells) {
        this.name = name;
        this.initialSpells = initialSpells;
    }
}
