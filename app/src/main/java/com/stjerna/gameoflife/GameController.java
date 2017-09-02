package com.stjerna.gameoflife;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

class GameController {

  private static final int DELAY = 0;
  private static final int PERIOD_MILLIS = 333;
  private Timer timer = new Timer();

  private boolean isStarted = false;

  private GameBoardView gameBoardView;
  private GameOfLife gameOfLife;

  private Activity context;

  GameController(Activity context, GameBoardView gameBoardView, GameOfLife gameOfLife) {
    this.gameBoardView = gameBoardView;
    this.gameOfLife = gameOfLife;
    this.context = context;
    gameBoardView.setGrid(gameOfLife.getGrid());
  }

  void seed(float probabilityOfLife) {
    for (int x = 0; x < gameOfLife.getGrid().length; x++) {
      for (int y = 0; y < gameOfLife.getGrid()[0].length; y++) {
        if ((1 - probabilityOfLife) < Math.random()) {
          gameOfLife.activateCell(x,y);
        }
      }
    }
  }

  void startGame() {
    if (isStarted) return;
    isStarted = true;
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        gameOfLife.nextGeneration();
        context.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            gameBoardView.update(gameOfLife.getGrid());
          }
        });
      }
    }, DELAY, PERIOD_MILLIS);
  }
}
