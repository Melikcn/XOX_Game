package com.bilgeadam.xox.game;


import com.bilgeadam.xox.R;

import java.util.Date;

public enum Players {

    X(1, R.drawable.x),
    O(2, R.drawable.o);

    private final int value, drawable;
    private int currentTurn;
    private Float score;
    private long totalTurnTime;
    private Date turnStartTime;

    Players(int value, int drawable ) {
        this.value = value;
        this.drawable = drawable;
        resetParameters();
    }

    public Float getScore() {
        return score;
    }

    protected void setScore(Float score) {
        this.score = score;
    }

    public int getDrawable() {
        return drawable;
    }

    protected int getValue(){
        return value;
    }

    Players getNextPlayer(){
        return this.ordinal() == X.ordinal() ? O : X;
    }

    protected void incrementTurn(){
        this.currentTurn++;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    protected void resetParameters(){
        currentTurn = 1;
        score = 0F;
        totalTurnTime = 0L;
    }

    protected void startTurnTime(){
        turnStartTime = new Date();
    }

    protected void endTurnTime(){
        totalTurnTime += new Date().getTime() - turnStartTime.getTime();
    }

    protected long getTotalTurnTime() {
        return totalTurnTime;
    }

    /**
     *
     * @return Random Player
     */
    static Players generateRandomPlayer(){
        Players[] players = Players.values();

        return players[(int)Math.round(Math.random() * (players.length - 1))];
    }
}
