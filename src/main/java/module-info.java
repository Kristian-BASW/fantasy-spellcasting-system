module com.example.spellcastingsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.base;
    requires javafx.graphics;

    opens com.example.spellcastingsystem to javafx.fxml;
    exports com.example.spellcastingsystem;
    exports com.example.spellcastingsystem.presentation.controllers;
    opens com.example.spellcastingsystem.presentation.controllers to javafx.fxml;
}