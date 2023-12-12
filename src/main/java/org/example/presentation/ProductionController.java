package org.example.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ProductionController {
    @FXML
    TextField title;
    @FXML
    TextField id;
    // selv opretet
    static String productionId = null;

    @FXML
    public void initialize() {
        if (productionId != null) { // kender eller tilføje  til GUI
            String[] productionInfo = GUI.loadProduction(Integer.parseInt(productionId));
            id.setText(productionInfo[0]);
            title.setText(productionInfo[1]);
        }
    }

    @FXML
    protected void addProduction() {
        if (title.getText().isEmpty()) {
            System.out.println("Missing field!");
        } else {
            String titleText = title.getText();
            GUI.addProduction(titleText); // kender eller tilføje  til GUI
            goBack();
        }
    }

    @FXML
    protected void editProduction() {
        if (id.getText().isEmpty() || title.getText().isEmpty()) {
            System.out.println("Missing field!");
        } else {
            String idText = id.getText();
            String nameText = title.getText();
            GUI.editProduction(idText, nameText); // kender eller tilføje  til GUI
            goBack();
        }
    }

    @FXML
    protected void deleteProduction() {
        // kun via ID
        GUI.deleteProduction(id.getText()); // kender eller tilføje  til GUI
        goBack();
    }

    @FXML
    // den referer til buttom cancel
    protected void goBack() {
        try {
            productionId = null;
            GUI.setRoot("primary");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
