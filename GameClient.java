package org.example;

import java.io.*;
import java.net.Socket;

public class GameClient {
    private static volatile boolean running = true;

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try {
            Socket socket = new Socket(serverAddress, port);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true);

            Thread inputThread = new Thread(() -> {
                try {
                    String command;

                    while (running && (command = keyboard.readLine()) != null) {
                        serverOutput.println(command);

                        if ("exit".equalsIgnoreCase(command)) {
                            running = false;
                            socket.close();
                            break;
                        }
                    }
                } catch (IOException e) {
                    running = false;
                }
            });

            Thread listenerThread = new Thread(() -> {
                try {
                    String response;

                    while (running && (response = serverInput.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException e) {
                    running = false;
                }
            });

            listenerThread.start();
            inputThread.start();

            inputThread.join();
            listenerThread.join();

        } catch (IOException | InterruptedException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
