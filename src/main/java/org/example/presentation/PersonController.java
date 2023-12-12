package org.example.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PersonController {
    @FXML
    TextField name;
    @FXML
    TextField id;
    @FXML
    TextField email;
    @FXML
    TextField phoneNumber;
// selv opretet
    static String personId = null;

    @FXML
    protected void initialize() {
        if(personId != null) {
            // kender eller tilføje  til GUI
            String[] personInfo = GUI.loadPerson(Integer.parseInt(personId));
            id.setText(personInfo[0]);
            name.setText(personInfo[1]);
            email.setText(personInfo[2]);
            phoneNumber.setText(personInfo[3]);
        }
    }

    @FXML // fra addpersom.fxml
    protected void addPerson() {
        if (missingInput()) {// betyder emty fields
            System.out.println("Missing field!");
        } else {
            int phoneNumberNumber = Integer.parseInt(phoneNumber.getText());
           // kender eller tilføje  til GUI
            GUI.addPerson(name.getText(), email.getText(), phoneNumberNumber);
            goBack();
        }
    }

    @FXML// fra editpersom.fxml
    protected void editPerson() {
        if (missingInput()) {// betyder emty fields
            System.out.println("Missing field!");
        } else {
            int phoneNumberNumber = Integer.parseInt(phoneNumber.getText());
            // kender eller tilføje  til GUI
            // vi redigere via ID
            GUI.editPerson(id.getText(), name.getText(), email.getText(), phoneNumberNumber);
            System.out.println("Edited person successfully");
            goBack();
        }
    }

    @FXML// fra deletepersom.fxml
    protected void deletePerson() {
        // kender eller tilføje  til GUI
        GUI.deletePerson(id.getText());// kun via ID
        goBack();
    }

    @FXML
    // den referer til buttom cancel
    protected void goBack() {
        try {
            personId = null;
            GUI.setRoot("primary");// kender til GUI
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // betyder emty fields
    private boolean missingInput() {
        return name.getText().isEmpty() || email.getText().isEmpty() || !isInt(phoneNumber.getText());
    }

    private boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
