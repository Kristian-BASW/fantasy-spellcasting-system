package com.example.spellcastingsystem.presentation.controllers;

import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.bll.WizardService;
import com.example.spellcastingsystem.presentation.WizardViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.IO.println;

public class WizardGameController implements Initializable {

    @FXML
    public TableView<WizardViewModel> wizardsTable;

    @FXML
    public TableColumn<WizardViewModel, String> wizardNameColumn;
    @FXML
    public TableColumn<WizardViewModel, String> wizardLevelColumn;

    public WizardService _wizardService;

    public WizardGameController(){
        _wizardService = new WizardService();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wizardsTable.setEditable(true);
        wizardNameColumn.setCellValueFactory(
                new PropertyValueFactory<WizardViewModel, String>("name"));


        wizardLevelColumn.setCellValueFactory(
                new PropertyValueFactory<WizardViewModel, String>("level"));

        _wizardService.getWizards()
                .stream()
                .forEach(s -> wizardsTable.getItems().add(WizardViewModel.fromWizard(s)));
//        wizardsTable.setItems(FXCollections.observableArrayList(wizards));
        System.out.println(wizardsTable.getColumns().getFirst());
    }
}
