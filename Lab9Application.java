package org.example;

import java.util.*;

public class Lab9Application {
    public static void main(String[] args) {
        Maze maze = new Maze(10, 10);
        Game game = new Game(maze, 60_000);

        Random random = new Random();
        Set<Position> usedPositions = new HashSet<>();

        Position bunnyPosition = randomFreePosition(maze, random, usedPositions);
        Bunny bunny = new Bunny(game);
        game.addActor(bunny, bunnyPosition);

        int robotCount = 3;

        for (int i = 1; i <= robotCount; i++) {
            Position robotPosition = randomFreePosition(maze, random, usedPositions);
            Robot robot = new Robot("Robot-" + i, game);
            game.addActor(robot, robotPosition);
        }

        ManagerThread managerThread = new ManagerThread(game);
        CommandThread commandThread = new CommandThread(game);

        managerThread.start();
        commandThread.start();

        for (Actor actor : game.getActors()) {
            new Thread(actor, actor.getActorName()).start();
        }
    }

    private static Position randomFreePosition(Maze maze, Random random, Set<Position> usedPositions) {
        Position position;

        do {
            int row = random.nextInt(maze.getRows());
            int col = random.nextInt(maze.getCols());
            position = new Position(row, col);
        } while (usedPositions.contains(position));

        usedPositions.add(position);
        return position;
    }
}