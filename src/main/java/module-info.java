module com.example.spellcastingsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.spellcastingsystem to javafx.fxml;
    exports com.example.spellcastingsystem;
    exports com.example.spellcastingsystem.presentation;
    opens com.example.spellcastingsystem.presentation to javafx.fxml;
}