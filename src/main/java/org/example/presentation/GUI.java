package org.example.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.business.users.User;

import java.io.IOException;

public class GUI extends Application {

    private static Scene scene;
    private static String sceneName;

    // Java FXML functions

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        sceneName = fxml;
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // primary Functionality

    public static void addPerson(String name, String email, int phoneNumber){
        User.addPerson(name, email, phoneNumber);// kender eller tilføje  til User klasse
    }

    public static void addProduction(String title){
        User.addProduction(title);// kender eller tilføje  til User klasse
    }

    public static void editPerson(String id, String name, String email, int phoneNumber){
        User.editPerson(id,name,email,phoneNumber);// kender eller tilføje  til User klasse
    }

    public static void editProduction(String id, String title){
        User.editProduction(id, title);// kender eller tilføje  til User klasse
    }

    public static void deletePerson(String id){
        User.deletePerson(id);// kender eller tilføje  til User klasse
    }

    public static void deleteProduction(String id){
        User.deleteProduction(id); // kender eller tilføje  til User klasse
    }

    public static void addCredit(String personID, String productionID, String role){
        User.addCredit(personID, productionID, role); // kender eller tilføje  til User klasse
    }

    public static void editCredit(String personID, String productionID, String role){
        User.editCredit(personID, productionID, role); // kender eller tilføje  til User klasse
    }

    public static void deleteCredit(String personId, String productionID){
        User.deleteCredit(personId,productionID);// kender eller tilføje  til User klasse
    }

    public static String getSceneName() {
        return sceneName;
    }

    public static String[] loadPerson(int id){
        return User.loadPerson(id); // kender eller tilføje  til user
    }

    public static String[] loadProduction(int id){
        return User.loadProduction(id);} // kender eller tilføje  til User
}