package org.example;

public class Bunny extends Actor {

    public Bunny(Game game) {
        super("Bunny", game);
    }

    @Override
    protected void act() {
        Position next = getRandomMove();
        game.moveActor(actorName, next);
    }
}