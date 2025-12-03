package com.example.spellcastingsystem.presentation.models;

import com.example.spellcastingsystem.be.Wizard;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class WizardCreateModel {
    public Property<String> name;
    public Property<Integer> wizardTypeId;
    public WizardCreateModel() {
        name = new SimpleStringProperty();
        wizardTypeId = new SimpleIntegerProperty().asObject();
    }

    public Wizard convertToWizard(){
        return new Wizard(name.getValue(), wizardTypeId.getValue());
    }
}
