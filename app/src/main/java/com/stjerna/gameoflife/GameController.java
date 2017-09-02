package com.stjerna.gameoflife;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

class GameController {

  private static final int DELAY = 0;
  private static final int PERIOD_MILLIS = 1000;
  private Timer timer = new Timer();

  private boolean isStarted = false;

  private GameBoardView gameBoardView;
  private GameBoard gameBoard;

  private Activity context;

  GameController(Activity context, GameBoardView gameBoardView, GameBoard gameBoard) {
    this.gameBoardView = gameBoardView;
    this.gameBoard = gameBoard;
    this.context = context;
    gameBoardView.setGameBoard(gameBoard);
  }

  void startGame() {
    //tryCancelTimer();
    if (isStarted) return;
    isStarted = true;
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        gameBoard.nextGeneration();
        context.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            gameBoardView.update(gameBoard);
          }
        });
      }
    }, DELAY, PERIOD_MILLIS);
  }
}
