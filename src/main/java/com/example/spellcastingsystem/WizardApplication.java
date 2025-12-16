package com.example.spellcastingsystem;

import com.example.spellcastingsystem.dal.implementation.DatabaseContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.IO.println;

public class WizardApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        checkDatabase();
        FXMLLoader fxmlLoader = new FXMLLoader(WizardApplication.class.getResource("create-wizard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Big Wizard game");
        stage.setScene(scene);
        stage.show();
    }

    void checkDatabase(){
        try{
            new DatabaseContext().checkExistence();
        } catch (SQLException e) {
            println(e.getMessage());
        }
    }
}
