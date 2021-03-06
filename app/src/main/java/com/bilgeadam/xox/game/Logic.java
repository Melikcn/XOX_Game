package com.bilgeadam.xox.game;

import android.util.Log;

import java.io.Serializable;

public class Logic implements Serializable {

    private final int[][] board; // 0 -> Empty; 1 -> X; 2 -> O
    private Players currentPlayer;
    private int currentTurn;
    private final ScoreCalculator scoreGenerator;
    public Logic(){
        this.board = new int[3][3];
        currentPlayer = Players.generateRandomPlayer();
        currentPlayer.startTurnTime();
        scoreGenerator = new ScoreCalculator(board.length);

        Players.X.resetParameters();
        Players.O.resetParameters();
    }

    public GameState processTurn(int x, int y){
        Log.v(this.getClass().getSimpleName(), String.format("Process turn for X:%d Y:%d", x, y));
        board[x][y] = currentPlayer.getValue();
        currentPlayer.endTurnTime();
        // TODO - Check game condition
        // Check if someone win
        //if(currentPlayer.getCurrentTurn() >= board.length && (checkStraight(x, true)|| checkStraight(y, false))){
        if(currentPlayer.getCurrentTurn() >= board.length && (checkStraight(x, 0, true) || checkStraight(0, y, false) ||
                chechCross(0, 0, true) || chechCross(board.length - 1, 0, false))){

            currentPlayer.setScore(scoreGenerator.generateScore(currentPlayer.getCurrentTurn(), currentPlayer.getTotalTurnTime()));
            return GameState.WIN;
        }
        // Check for draw
        if (Players.X.getCurrentTurn() + Players.O.getCurrentTurn() > board.length * board[0].length)
            return GameState.DRAW;
        // Game continues
        currentPlayer.incrementTurn();
        currentPlayer = currentPlayer.getNextPlayer();
        currentPlayer.startTurnTime();
        return GameState.CONTINUE;
    }
    private boolean checkStraight(int row, int column, boolean isRowCheck){
        if (row >= board.length || column >= board.length)
            return true;
        else if (board[row][column] != currentPlayer.getValue())
            return false;
        else
            return isRowCheck ? checkStraight(row, column + 1, true) : checkStraight(row + 1, column, false);
    }

//    private boolean checkStraight(int location, boolean isRowCheck){
//        int value;
//        for (int i = 0; i < board.length; i++) {
//            value = isRowCheck ? board[location][i] : board[i][location];
//            if(value != currentPlayer.getValue())
//                return false;
//        }
//        return true;
//    }

    private boolean chechCross(int row, int column, boolean isSameElementCheck){
        if (row >= board.length || column >= board.length)
            return true;
        else if (board[row][column] != currentPlayer.getValue())
            return false;
        else
            return isSameElementCheck ? chechCross(row + 1, column + 1, true) : chechCross(row - 1, column + 1, false);
    }


    public Players getCurrentPlayer(){
        return currentPlayer;
    }

}
