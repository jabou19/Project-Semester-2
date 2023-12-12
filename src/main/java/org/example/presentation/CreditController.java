package org.example.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CreditController {
    @FXML
    private TextField personID;
    @FXML
    private TextField productionID;
    @FXML
    private TextField role;

    @FXML// fra .fxml
    protected void addCredit() {
        if (role.getText().isEmpty() || missingInfo()) {
            System.out.println("Missing field!");
        } else {
            // kender eller tilføje  til GUI
            GUI.addCredit(personID.getText(), productionID.getText(), role.getText());
            goBack();
        }
    }

    @FXML
    protected void editCredit() {
        if (role.getText().isEmpty() || missingInfo()) {
            System.out.println("Missing field!");
        } else {
            // kender eller tilføje  til GUI
            GUI.editCredit(personID.getText(), productionID.getText(), role.getText());
            goBack();
        }
    }

    @FXML
    protected void deleteCredit() {
        if (missingInfo()) {
            System.out.println("Missing field!");
        } else {
            // kender eller tilføje  til GUI
            GUI.deleteCredit(personID.getText(), productionID.getText());
            goBack();
        }
    }

    @FXML
    protected void goBack() {
        try {
            GUI.setRoot("primary");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private boolean missingInfo() {// betyder emty fields
        return productionID.getText().isEmpty() || personID.getText().isEmpty();
    }


}
