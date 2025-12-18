package com.example.spellcastingsystem.presentation.controllers;

import com.example.spellcastingsystem.WizardApplication;
import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.bll.WizardService;
import com.example.spellcastingsystem.presentation.models.holders.WizardHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField wizardNameTextField;
    @FXML
    private Label errorLabel;

    private WizardService wizardService;

    public LoginController(){
        wizardService = new WizardService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setStyle("-fx-text-fill: transparent");
    }


    public void onTryLoginClicked(MouseEvent mouseEvent) throws IOException {
        Stage currentStage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        var wizard = wizardService.existsName(wizardNameTextField.getText());
        if(wizard != null){
            WizardHolder.getInstance().setWizard(wizard);
            nameApproved(currentStage, wizard);
        }
        else{
            errorLabel.setStyle("-fx-text-fill: #bf0404");
        }
    }

    public void nameApproved(Stage currentStage, Wizard wizard) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WizardApplication.class.getResource("wizard-game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        // Setting Wizard for accessing in next page
        WizardHolder wizardHolder = WizardHolder.getInstance();
        wizardHolder.setWizard(wizard);

        currentStage.setScene(scene);
        currentStage.show();
    }


    public void onBackClicked(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WizardApplication.class.getResource("wizard-start-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}
