package com.example.spellcastingsystem.presentation.controllers;

import com.example.spellcastingsystem.WizardApplication;
import com.example.spellcastingsystem.be.Spell;
import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.be.WizardType;
import com.example.spellcastingsystem.bll.WizardService;
import com.example.spellcastingsystem.bll.WizardTypeService;
import com.example.spellcastingsystem.presentation.models.WizardCreateModel;
import com.example.spellcastingsystem.presentation.models.holders.WizardHolder;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
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
    public Label errorLabel;

    @FXML
    public ComboBox wizardTypeComboBox;

    private WizardService _wizardService;
    private WizardTypeService _wizardTypeService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setStyle("-fx-text-fill: transparent");
        model = new WizardCreateModel();
        _wizardService = new WizardService();
        _wizardTypeService = new WizardTypeService();
        initUI();
    }

    @FXML
    protected void onConfirmClicked(MouseEvent mouseEvent) throws IOException {
        try{
                if(this._wizardService.create(model.convertToWizard())){
                Wizard wizard = this._wizardService.getByName(model.convertToWizard().name);
                WizardHolder.getInstance().setWizard(wizard);
                FXMLLoader fxmlLoader = new FXMLLoader(WizardApplication.class.getResource("wizard-game-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
            else {
                errorLabel.setStyle("-fx-text-fill: #bf0404");
            }
        }catch (Exception e){
            println(e.getMessage());
        }

    }



    void initUI(){
        wizardTypeComboBox.setItems(FXCollections.observableArrayList(_wizardTypeService.getNames()));
        wizardTypeComboBox.getSelectionModel().select(0);
        wizardNameTextField.textProperty().bindBidirectional(model.name);
    }

    public void onBackClicked(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WizardApplication.class.getResource("wizard-start-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
