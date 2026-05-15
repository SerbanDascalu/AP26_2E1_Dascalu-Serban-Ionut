package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.*;

public class GameServer {
    private final int port;
    private volatile boolean running = true;

    private ServerSocket serverSocket;
    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private final List<ClientThread> clients = new CopyOnWriteArrayList<>();
    private final QuizGame quizGame = new QuizGame("src/main/resources/questions.txt");

    public GameServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            quizGame.startQuestion();

            while (running) {
                Socket socket = serverSocket.accept();

                ClientThread clientThread = new ClientThread(socket, this, quizGame);
                clients.add(clientThread);

                threadPool.execute(clientThread);
            }
        } catch (IOException e) {
            if (running) {
                System.out.println("Server error: " + e.getMessage());
            }
        } finally {
            shutdown();
        }
    }

    public void broadcast(String message) {
        for (ClientThread client : clients) {
            client.sendMessage(message);
        }
    }

    public void removeClient(ClientThread clientThread) {
        clients.remove(clientThread);
    }

    public void sendCurrentQuestionToAll() {
        Question question = quizGame.getCurrentQuestion();

        if (question == null) {
            broadcast("Game finished.\n" + quizGame.getScores() + quizGame.getWinner());
            return;
        }

        broadcast("\nNew question:\n" + question.formatQuestion());
    }

    public void checkQuestionProgress() {
        if (quizGame.allPlayersAnswered()) {
            moveToNextQuestion();
        }
    }

    public synchronized void moveToNextQuestion() {
        if (quizGame.isFinished()) {
            broadcast("Game finished.\n" + quizGame.getScores() + quizGame.getWinner());
            return;
        }

        boolean hasNext = quizGame.nextQuestion();

        if (!hasNext) {
            broadcast("Game finished.\n" + quizGame.getScores() + quizGame.getWinner());
        } else {
            sendCurrentQuestionToAll();
        }
    }

    public void stopServer() {
        running = false;
        broadcast("Server stopped");

        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing server socket.");
        }

        shutdown();
    }

    private void shutdown() {
        threadPool.shutdown();
        scheduler.shutdown();

        try {
            if (!threadPool.awaitTermination(3, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
        }

        System.out.println("Server shutdown complete.");
    }

    public static void main(String[] args) {
        GameServer server = new GameServer(5000);
        server.start();
    }
}