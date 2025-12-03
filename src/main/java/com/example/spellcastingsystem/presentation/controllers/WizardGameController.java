package com.example.spellcastingsystem.presentation.controllers;

import com.example.spellcastingsystem.bll.WizardService;

public class WizardGameController {
    public WizardService _wizardService;
    public WizardGameController(){
        _wizardService = new WizardService();
    }
}
