package com.example.spellcastingsystem.presentation.models.holders;


import com.example.spellcastingsystem.be.Wizard;

public final class WizardHolder {

    private Wizard wizard;
    private final static WizardHolder INSTANCE = new WizardHolder();

    private WizardHolder() {}

    public static WizardHolder getInstance() {
        return INSTANCE;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }

    public Wizard getWizard() {
        return this.wizard;
    }
}