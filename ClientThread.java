package org.example;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {
    private final Socket socket;
    private final GameServer server;
    private final QuizGame quizGame;

    private BufferedReader in;
    private PrintWriter out;
    private String playerName;

    public ClientThread(Socket socket, GameServer server, QuizGame quizGame) {
        this.socket = socket;
        this.server = server;
        this.quizGame = quizGame;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            sendMessage("Connected to quiz server.");
            sendMessage("Commands: join <name>, answer <A/B/C/D>, score, stop, exit");

            String command;

            while ((command = in.readLine()) != null) {
                handleCommand(command);

                if ("exit".equalsIgnoreCase(command)) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        } finally {
            server.removeClient(this);
            closeSocket();
        }
    }

    private void handleCommand(String command) {
        if (command.startsWith("join ")) {
            playerName = command.substring(5).trim();
            quizGame.addPlayer(playerName);

            sendMessage("You joined as " + playerName);
            server.broadcast(playerName + " joined the game.");

            server.sendCurrentQuestionToAll();
        } else if (command.startsWith("answer ")) {
            if (playerName == null) {
                sendMessage("You must join first using: join <name>");
                return;
            }

            String answer = command.substring(7).trim();
            String result = quizGame.submitAnswer(playerName, answer);

            sendMessage(result);
            server.broadcast(playerName + " answered.");

            server.checkQuestionProgress();
        } else if ("score".equalsIgnoreCase(command)) {
            sendMessage(quizGame.getScores());
        } else if ("stop".equalsIgnoreCase(command)) {
            sendMessage("Server stopped");
            server.stopServer();
        } else if ("exit".equalsIgnoreCase(command)) {
            sendMessage("Goodbye!");
        } else {
            sendMessage("Server received the request: " + command);
        }
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    private void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Could not close client socket.");
        }
    }
}
