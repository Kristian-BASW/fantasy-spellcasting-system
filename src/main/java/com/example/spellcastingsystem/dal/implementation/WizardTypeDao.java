package com.example.spellcastingsystem.dal.implementation;

import com.example.spellcastingsystem.be.Spell;
import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.be.WizardType;
import com.example.spellcastingsystem.dal.ICrudDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class WizardTypeDao implements ICrudDao<WizardType> {
    private List<WizardType> wizardTypes;

    public WizardTypeDao() {
        wizardTypes = new ArrayList<>();
        mockList();
    }

    private void mockList(){
        wizardTypes = List.of(
                new WizardType("Druid", List.of()),
                new WizardType("Necromancer", List.of())
        );
    }


    @Override
    public void add(WizardType item) {
        wizardTypes.add(item);
    }

    @Override
    public void update(WizardType item) {
        Optional<WizardType> wizardType = this.wizardTypes.stream()
                .filter((x) -> Objects.equals(x.id, item.id))
                .findFirst();
        if(wizardType.isPresent()){
            wizardType.get().name = item.name;
            wizardType.get().initialSpells = item.initialSpells;
            return;
        }
        throw new RuntimeException("Wizard not found");
    }

    @Override
    public void delete(int id) {
        wizardTypes.removeIf((x) -> Objects.equals(x.id, id));
    }

    @Override
    public List<WizardType> getAll() {
        return wizardTypes;
    }

    @Override
    public WizardType get(int id) {
        return wizardTypes.get(id);
    }
}
