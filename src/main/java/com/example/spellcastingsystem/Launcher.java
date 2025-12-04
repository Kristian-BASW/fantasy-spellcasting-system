package com.example.spellcastingsystem;

import com.example.spellcastingsystem.dal.implementation.DatabaseContext;
import javafx.application.Application;

import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.IO.println;

public class Launcher {
    public static void main(String[] args) {
        try {
            DatabaseContext.checkExistence();
        } catch (SQLException e) {
            println("SQL creation failed");
        }
        Application.launch(WizardApplication.class, args);
    }
}
