package com.example.spellcastingsystem.presentation;

import com.example.spellcastingsystem.be.Wizard;
import javafx.beans.property.SimpleStringProperty;

public class WizardViewModel {
    private SimpleStringProperty name;
    private SimpleStringProperty level;
    private SimpleStringProperty experience;

    public static WizardViewModel fromWizard(Wizard wizard) {
        WizardViewModel viewModel = new WizardViewModel();
        viewModel.name = new SimpleStringProperty(wizard.name);
        viewModel.level = new SimpleStringProperty(String.valueOf(wizard.level));
        viewModel.experience = new SimpleStringProperty(String.valueOf(wizard.experience));
        return viewModel;
    }

    public String getName(){
        return this.name.get();
    }
    public String getLevel(){
        return this.level.get();
    }
    public String getExperience(){
        return this.experience.get();
    }
}
