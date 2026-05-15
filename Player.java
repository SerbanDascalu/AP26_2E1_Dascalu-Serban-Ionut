package org.example;

public class Player {
    private String name;
    private int score;
    private long totalResponseTime;
    private boolean answeredCurrentQuestion;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.totalResponseTime = 0;
        this.answeredCurrentQuestion = false;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public long getTotalResponseTime() {
        return totalResponseTime;
    }

    public boolean hasAnsweredCurrentQuestion() {
        return answeredCurrentQuestion;
    }

    public void setAnsweredCurrentQuestion(boolean answeredCurrentQuestion) {
        this.answeredCurrentQuestion = answeredCurrentQuestion;
    }

    public void addCorrectAnswer(long responseTime) {
        score++;
        totalResponseTime += responseTime;
    }
}
