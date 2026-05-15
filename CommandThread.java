package org.example;

import java.util.Scanner;

public class CommandThread extends Thread {
    private final Game game;

    public CommandThread(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        printHelp();

        while (game.getState() == GameState.RUNNING) {
            String command = scanner.nextLine();
            executeCommand(command);
        }
    }

    private void executeCommand(String command) {
        String[] parts = command.split(" ");

        if (parts.length == 0) {
            return;
        }

        switch (parts[0]) {
            case "slow":
                applyToActor(parts, Actor::slowDown);
                break;

            case "fast":
                applyToActor(parts, Actor::speedUp);
                break;

            case "pause":
                applyToActor(parts, Actor::pauseActor);
                break;

            case "resume":
                applyToActor(parts, Actor::resumeActor);
                break;

            case "stop":
                game.stopGame(GameState.TIME_LIMIT_EXCEEDED);
                break;

            case "help":
                printHelp();
                break;

            default:
                System.out.println("Unknown command.");
        }
    }

    private void applyToActor(String[] parts, ActorCommand command) {
        if (parts.length == 1 || parts[1].equals("all")) {
            for (Actor actor : game.getActors()) {
                command.apply(actor);
            }
            return;
        }

        Actor actor = game.getActor(parts[1]);

        if (actor != null) {
            command.apply(actor);
        } else {
            System.out.println("Actor not found.");
        }
    }

    private void printHelp() {
        System.out.println("""
                Commands:
                slow all
                fast all
                pause all
                resume all
                slow Robot-1
                fast Bunny
                pause Robot-2
                resume Robot-2
                stop
                help
                """);
    }

    private interface ActorCommand {
        void apply(Actor actor);
    }
}