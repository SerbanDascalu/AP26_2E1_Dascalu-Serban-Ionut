package org.example;

public class ManagerThread extends Thread {
    private final Game game;

    public ManagerThread(Game game) {
        this.game = game;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (game.getState() == GameState.RUNNING) {
            game.printState();

            if (game.isTimeLimitExceeded()) {
                game.stopGame(GameState.TIME_LIMIT_EXCEEDED);
                break;
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}