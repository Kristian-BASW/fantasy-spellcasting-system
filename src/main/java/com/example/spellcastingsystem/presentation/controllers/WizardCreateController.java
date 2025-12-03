package com.example.spellcastingsystem.presentation.controllers;

import com.example.spellcastingsystem.WizardApplication;
import com.example.spellcastingsystem.be.Spell;
import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.be.WizardType;
import com.example.spellcastingsystem.bll.WizardService;
import com.example.spellcastingsystem.bll.WizardTypeService;
import com.example.spellcastingsystem.presentation.models.WizardCreateModel;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.IO.println;

public class WizardCreateController implements Initializable {
    WizardCreateModel model;

    @FXML
    public TextField wizardNameTextField;

    @FXML
    public ComboBox wizardTypeComboBox;

    private WizardService _wizardService;
    private WizardTypeService _wizardTypeService;

    @FXML
    protected void onConfirmClicked() {
        try{
            this._wizardService.create(model.convertToWizard());
            FXMLLoader fxmlLoader = new FXMLLoader(WizardApplication.class.getResource("wizard-game-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
            Stage stage = new Stage();
            stage.setTitle("Big Wizard game");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            println(e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new WizardCreateModel();
        _wizardService = new WizardService();
        _wizardTypeService = new WizardTypeService();
        initUI();
    }

    void initUI(){
        wizardTypeComboBox.setItems(FXCollections.observableArrayList(_wizardTypeService.getNames()));
        wizardTypeComboBox.getSelectionModel().select(0);
        wizardNameTextField.textProperty().bindBidirectional(model.name);
    }
}
