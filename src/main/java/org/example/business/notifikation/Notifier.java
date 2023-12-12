package org.example.business.notifikation;

import org.example.business.credits.Credit;
import org.example.business.credits.Person;
import org.example.business.credits.Production;
import org.example.business.users.User;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Notifier {

    private static Notifier instance;
    private PrintWriter clientOut;

    private Notifier() {
        // output
        try{
            Socket outputSocket = new Socket("localhost", 3334);
            System.out.println("Output client connection with server!");
            clientOut = new PrintWriter(outputSocket.getOutputStream(), true);
            clientOut.println(User.id);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        // input Thread
        Thread inputThread = new Thread(()->{
            try (Socket inputSocket = new Socket("localhost", 3334)) {
                System.out.println("Input client connection with server!");
                BufferedReader input = new BufferedReader(new InputStreamReader(inputSocket.getInputStream()));

                Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                TrayIcon icon = new TrayIcon(image, "demo");
                icon.setImageAutoSize(true);

                SystemTray tray = SystemTray.getSystemTray();
                tray.add(icon);

                System.out.println("Notification Handler, waiting for notifications!");
                String text;
                while (true) {
                    if ((text = input.readLine()) != null) {
                        System.out.println("Received notification\n" + text);
                        icon.displayMessage(text, "application", TrayIcon.MessageType.INFO);
                    }
                }
            } catch (IOException | AWTException ioException) {
                ioException.printStackTrace();
            }

        });
        inputThread.start();
    }

    public static Notifier getInstance() {
        if (instance == null) {
            instance = new Notifier();
        }
        return instance;
    }
// fra user klasse
    public void notifyNewPerson(Person person) {
        clientOut.println("User[" + User.id + "] added a new person:" + person);
    }
    // fra user klasse
    public void notifyEditPerson(String before, Person person) {
        clientOut.println("User[" + User.id + "] edited a person. Before:\n" + before + "\n" + person);
    }
    // fra user klasse
    public void notifyDeletePerson(Person person) {
        clientOut.println("User[" + User.id + "] deleted a person:\n" + person);
    }
    // fra user klasse
    public void notifyNewProduction(Production production) {
        clientOut.println("User[" + User.id + "] added a new production:\n" + production);
    }
    // fra user klasse
    public void notifyEditProduction(String before, Production production) {
        clientOut.println("User[" + User.id + "] edited a production. Before:\n" + before + "\n" + production);
    }
    // fra user klasse
    public void notifyDeleteProduction(Production production) {
        clientOut.println("User[" + User.id + "] deleted a production:\n" + production);
    }
    // fra user klasse
    public void notifyNewCredit(Credit credit) {
        clientOut.println("User[" + User.id + "] added a new credit:\n" + credit);
    }
    // fra user klasse
    public void notifyEditCredit(String before, Credit credit) {
        clientOut.println("User[" + User.id + "] edited a credit. Before:\n" + before + "\n" + credit);
    }
    // fra user klasse
    public void notifyDeleteCredit(Credit credit) {
        clientOut.println("User[" + User.id + "] deleted a credit:\n" + credit);
    }

}

