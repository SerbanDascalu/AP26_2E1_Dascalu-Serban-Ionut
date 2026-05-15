package org.example;

import java.util.List;
import java.util.Random;

public abstract class Actor implements Runnable {
    protected final String actorName;
    protected final Game game;
    protected final Random random = new Random();

    private volatile boolean paused = false;
    private volatile int speedMillis = 500;

    public Actor(String actorName, Game game) {
        this.actorName = actorName;
        this.game = game;
    }

    @Override
    public void run() {
        while (game.getState() == GameState.RUNNING) {
            waitIfPaused();
            act();

            try {
                Thread.sleep(speedMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println(actorName + " stopped.");
    }

    protected abstract void act();

    protected Position getRandomMove() {
        Position current = game.getPosition(actorName);
        List<Position> neighbors = game.getMaze().getAccessibleNeighbors(current);

        if (neighbors.isEmpty()) {
            return current;
        }

        return neighbors.get(random.nextInt(neighbors.size()));
    }

    private void waitIfPaused() {
        while (paused && game.getState() == GameState.RUNNING) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void slowDown() {
        speedMillis += 200;
    }

    public void speedUp() {
        speedMillis = Math.max(100, speedMillis - 200);
    }

    public void pauseActor() {
        paused = true;
    }

    public void resumeActor() {
        paused = false;
    }

    public String getActorName() {
        return actorName;
    }
}