package org.example.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.business.users.User;

import java.io.IOException;

public class loginController {

    @FXML
    TextField id;

    @FXML
    protected void login(){
        if(!id.getText().isEmpty() && isInt(id.getText())){
            try {// kender eller tilføje  til User klasse
                User.onLoad(Integer.parseInt(id.getText()));
                GUI.setRoot("primary");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
