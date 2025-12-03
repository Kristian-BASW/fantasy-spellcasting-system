package com.example.spellcastingsystem.dal.implementation;

import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.dal.ICrudDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WizardDao implements ICrudDao<Wizard> {
    List<Wizard> wizards = new ArrayList<>();

    @Override
    public void add(Wizard item) {
        this.wizards.add(item);
    }

    @Override
    public void update(Wizard item) {
        Optional<Wizard> wizard = this.wizards.stream()
                .filter((x) -> x.id == item.id)
                .findFirst();
        if(wizard.isPresent()){
            wizard.get().name = item.name;
            wizard.get().level = item.level;
            return;
        }
        throw new RuntimeException("Wizard not found");

    }

    @Override
    public void delete(int id) {
        this.wizards.removeIf((x) -> x.id == id);
    }

    @Override
    public List<Wizard> getAll() {
        return wizards;
    }

    @Override
    public Wizard get(int id) {
        Optional<Wizard> wizard = this.wizards.stream()
                .filter((x) -> x.id == id)
                .findFirst();
        if(wizard.isPresent()){
            return wizard.get();
        }
        throw new RuntimeException("Wizard not found");
    }
}
