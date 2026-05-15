package org.example;

import java.util.*;

public class Robot extends Actor {
    private final Set<Position> visited = new HashSet<>();
    private final Deque<Position> stack = new ArrayDeque<>();

    public Robot(String name, Game game) {
        super(name, game);
    }

    @Override
    protected void act() {
        Position current = game.getPosition(actorName);
        visited.add(current);
        game.getSharedMemory().markExplored(current);

        Position bunny = game.getBunnyPosition();

        if (isClose(current, bunny)) {
            game.getSharedMemory().reportBunnyPosition(bunny);
        }

        Position target = game.getSharedMemory().getKnownBunnyPosition();

        if (target != null) {
            moveToward(target);
        } else {
            exploreSystematically(current);
        }
    }

    private void exploreSystematically(Position current) {
        List<Position> neighbors = game.getMaze().getAccessibleNeighbors(current);

        for (Position neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                stack.push(current);
                game.moveActor(actorName, neighbor);
                return;
            }
        }

        if (!stack.isEmpty()) {
            game.moveActor(actorName, stack.pop());
        } else {
            Position randomMove = getRandomMove();
            game.moveActor(actorName, randomMove);
        }
    }

    private void moveToward(Position target) {
        Position current = game.getPosition(actorName);
        List<Position> neighbors = game.getMaze().getAccessibleNeighbors(current);

        Position best = current;
        int bestDistance = distance(current, target);

        for (Position neighbor : neighbors) {
            int d = distance(neighbor, target);
            if (d < bestDistance) {
                bestDistance = d;
                best = neighbor;
            }
        }

        game.moveActor(actorName, best);
    }

    private boolean isClose(Position a, Position b) {
        return distance(a, b) <= 2;
    }

    private int distance(Position a, Position b) {
        return Math.abs(a.row() - b.row()) + Math.abs(a.col() - b.col());
    }
}