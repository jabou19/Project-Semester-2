package org.example.business.notifikation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class NotificationServer {

    private static ArrayList<outputClient> senders = null;
    private static ArrayList<InputClient> receivers = null;

    public static void main(String[] args) {

        senders = new ArrayList<>();

        try (ServerSocket ss = new ServerSocket(3334)) {
            System.out.println("Waiting for clients.");
            Socket socket;


            while (true) {
                socket = ss.accept();
                System.out.println("Client accepted, port:" + socket.getPort());

                // input
                InputClient inputClient = new InputClient(socket);
                Thread inputThread = new Thread(inputClient);
                inputThread.start();

                // output
                socket = ss.accept();
                senders.add(new outputClient(inputClient.getId(), socket));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void notify(int id, String text) {
        System.out.println("Printing message from [" + id + "]: \"" + text + "\"");
        senders.forEach(element -> {
            if (element.getId() != id) {
                element.notify(text);
            }
        });
    }

    private static class outputClient {
        final private int id;

        final Socket socket;
        PrintWriter output = null;

        private outputClient(int id, Socket socket) {
            this.id = id;
            this.socket = socket;
            try {
                this.output = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public int getId() {
            return id;
        }

        public void notify(String text) {
            output.println(text);
        }
    }

    private static class InputClient implements Runnable {
        public int getId() {
            return id;
        }

        private int id;
        final Socket socket;
        BufferedReader input;

        InputClient(Socket socket) {
            this.socket = socket;
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String text;
                while (true) {
                    if((text = input.readLine()) != null){
                        this.id = Integer.parseInt(text);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String text;
                while (true) {
                    if ((text = input.readLine()) != null) {
                        NotificationServer.notify(id, text);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
