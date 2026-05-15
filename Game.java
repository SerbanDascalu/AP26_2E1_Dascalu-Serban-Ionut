package org.example;

import java.util.*;

public class Game {
    private final Maze maze;
    private final SharedMemory sharedMemory = new SharedMemory();

    private final Map<String, Position> actorPositions = new HashMap<>();
    private final Map<String, Actor> actors = new HashMap<>();

    private GameState state = GameState.RUNNING;
    private final long startTime;
    private final long timeLimitMillis;

    public Game(Maze maze, long timeLimitMillis) {
        this.maze = maze;
        this.timeLimitMillis = timeLimitMillis;
        this.startTime = System.currentTimeMillis();
    }

    public synchronized void addActor(Actor actor, Position position) {
        actors.put(actor.getActorName(), actor);
        actorPositions.put(actor.getActorName(), position);
    }

    public synchronized boolean moveActor(String actorName, Position newPosition) {
        if (state != GameState.RUNNING) {
            return false;
        }

        if (isCellOccupiedByRobot(newPosition, actorName)) {
            return false;
        }

        actorPositions.put(actorName, newPosition);

        Position bunnyPosition = getBunnyPosition();

        if (actorName.startsWith("Robot") && newPosition.equals(bunnyPosition)) {
            state = GameState.ROBOT_CAUGHT_BUNNY;
            notifyAll();
            return true;
        }

        if (actorName.equals("Bunny") && newPosition.equals(maze.getExit())) {
            state = GameState.BUNNY_ESCAPED;
            notifyAll();
            return true;
        }

        return true;
    }

    private boolean isCellOccupiedByRobot(Position position, String currentActorName) {
        for (Map.Entry<String, Position> entry : actorPositions.entrySet()) {
            String name = entry.getKey();

            if (!name.equals(currentActorName)
                    && name.startsWith("Robot")
                    && entry.getValue().equals(position)) {
                return true;
            }
        }

        return false;
    }

    public synchronized Position getPosition(String actorName) {
        return actorPositions.get(actorName);
    }

    public synchronized Position getBunnyPosition() {
        return actorPositions.get("Bunny");
    }

    public synchronized GameState getState() {
        return state;
    }

    public synchronized void stopGame(GameState finalState) {
        if (state == GameState.RUNNING) {
            state = finalState;
            notifyAll();
        }
    }

    public synchronized void printState() {
        char[][] board = new char[maze.getRows()][maze.getCols()];

        for (int row = 0; row < maze.getRows(); row++) {
            Arrays.fill(board[row], '.');
        }

        Position exit = maze.getExit();
        board[exit.row()][exit.col()] = 'E';

        for (Map.Entry<String, Position> entry : actorPositions.entrySet()) {
            Position p = entry.getValue();

            if (entry.getKey().equals("Bunny")) {
                board[p.row()][p.col()] = 'B';
            } else {
                board[p.row()][p.col()] = 'R';
            }
        }

        System.out.println();
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }

        System.out.println("State: " + state);
        System.out.println("Running time: " + getRunningTimeSeconds() + " seconds");
    }

    public synchronized long getRunningTimeSeconds() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public boolean isTimeLimitExceeded() {
        return System.currentTimeMillis() - startTime > timeLimitMillis;
    }

    public Maze getMaze() {
        return maze;
    }

    public SharedMemory getSharedMemory() {
        return sharedMemory;
    }

    public Collection<Actor> getActors() {
        return actors.values();
    }

    public Actor getActor(String name) {
        return actors.get(name);
    }
}