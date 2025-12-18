package com.example.spellcastingsystem.presentation.controllers;

import com.example.spellcastingsystem.WizardApplication;
import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.bll.WizardService;
import com.example.spellcastingsystem.presentation.WizardViewModel;
import com.example.spellcastingsystem.presentation.models.holders.WizardHolder;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.IO.println;

public class WizardGameController implements Initializable {

    @FXML
    public TableView<WizardViewModel> wizardsTable;

    @FXML
    public TableColumn<WizardViewModel, String> wizardNameColumn;
    @FXML
    public TableColumn<WizardViewModel, String> wizardLevelColumn;

    public Label labelName;

    private Wizard loggedInWizard;

    public WizardService _wizardService;

    public WizardGameController(){
        _wizardService = new WizardService();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loggedInWizard = WizardHolder.getInstance().getWizard();
        labelName.setText(loggedInWizard.name);
        wizardsTable.setEditable(true);
        wizardNameColumn.setCellValueFactory(
                new PropertyValueFactory<WizardViewModel, String>("name"));

        wizardLevelColumn.setCellValueFactory(
                new PropertyValueFactory<WizardViewModel, String>("level"));


        List<WizardViewModel> wizards = _wizardService.getWizards()
                .stream()
                .filter(w -> w.id != loggedInWizard.id)
                .map(wizard -> WizardViewModel.fromWizard(wizard))
                .toList();

        wizardsTable.setItems(FXCollections.observableArrayList(wizards));
    }

    public void logoutButtonClicked(MouseEvent mouseEvent) throws IOException {
        WizardHolder.getInstance().setWizard(null);

        FXMLLoader fxmlLoader = new FXMLLoader(WizardApplication.class.getResource("wizard-start-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("Big Wizard game");
        stage.setScene(scene);
        stage.show();
    }
}
