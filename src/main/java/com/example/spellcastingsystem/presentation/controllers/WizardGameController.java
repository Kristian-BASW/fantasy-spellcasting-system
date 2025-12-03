package com.example.spellcastingsystem.presentation.controllers;

import com.example.spellcastingsystem.bll.WizardService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class WizardGameController implements Initializable {

    @FXML
    public ListView wizardsListView;

    public WizardService _wizardService;
    public WizardGameController(){
        _wizardService = new WizardService();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wizardsListView.setItems(FXCollections.observableArrayList(_wizardService.getWizards()));
    }
}
