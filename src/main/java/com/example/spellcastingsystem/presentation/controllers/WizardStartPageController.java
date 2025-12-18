package com.example.spellcastingsystem.presentation.controllers;

import com.example.spellcastingsystem.WizardApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WizardStartPageController implements Initializable {

    @FXML
    private Label introductionLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        introductionLabel.setText(
                "In an age before kings wore crowns of gold, and before history was written in ink, " +
                "the world was shaped by magic. Invisible currents of power flowed through the land, " +
                "the sky, and all living things—and only a few could sense them. Fewer still could master them.\n\n" +
                "You are one of the chosen.\n\n" +
                "As an apprentice of the ancient arts, you step into a world where every word can be a spell, " +
                "and every decision can alter the course of fate. Forgotten ruins hide forbidden knowledge, " +
                "powerful wizards guard ancient secrets, and dark forces stir in the shadows.\n\n" +
                "Your journey begins with a choice: Will you seek wisdom, power—or the truth behind magic itself?\n\n" +
                "Take up your staff. Open your grimoire.\n" +
                "Magic awaits.");
    }

    public void onNewWizardClicked(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WizardApplication.class.getResource("create-wizard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Big Wizard game");
        stage.setScene(scene);
        Stage currentStage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.show();
        currentStage.close();
    }

    public void onLoginClicked(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WizardApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("Big Wizard game");
        stage.setScene(scene);
        stage.show();
    }
}
