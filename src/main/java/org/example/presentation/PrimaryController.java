package org.example.presentation;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PrimaryController {

    @FXML
    TextField idSearch;

    @FXML
    private void switchToAddNewPerson() throws IOException {
        GUI.setRoot("addPerson");
    }

    @FXML
    private void switchToEditPerson() throws IOException {
        PersonController.personId = idSearch.getText();
        GUI.setRoot("editPerson");
    }

    @FXML
    private void switchToAddNewProduction() throws IOException {
        GUI.setRoot("addProduction");
    }

    @FXML
    private void switchToEditProduction() throws IOException {
        ProductionController.productionId = idSearch.getText();
        GUI.setRoot("editProduction");
    }
    @FXML
    private void switchToAddCredit() throws IOException {
        GUI.setRoot("addCredit");
    }

    @FXML
    private void switchToEditCredit() throws IOException {
        GUI.setRoot("editCredit");
    }
}
