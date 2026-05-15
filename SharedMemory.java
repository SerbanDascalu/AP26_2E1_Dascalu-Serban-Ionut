package org.example;

import java.util.HashSet;
import java.util.Set;

public class SharedMemory {
    private Position knownBunnyPosition;
    private final Set<Position> exploredCells = new HashSet<>();

    public synchronized void reportBunnyPosition(Position position) {
        knownBunnyPosition = position;
    }

    public synchronized Position getKnownBunnyPosition() {
        return knownBunnyPosition;
    }

    public synchronized void markExplored(Position position) {
        exploredCells.add(position);
    }

    public synchronized boolean isExplored(Position position) {
        return exploredCells.contains(position);
    }
}