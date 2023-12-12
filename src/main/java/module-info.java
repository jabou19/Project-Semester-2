/**
 * Please for the love of god, do not change this...
 * Took way too long for me to edit to work - Speer.
 */
module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires postgresql;
    requires java.desktop;

    opens org.example.presentation to javafx.fxml;
    exports org.example.presentation;
}