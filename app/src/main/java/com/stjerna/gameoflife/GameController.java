package com.stjerna.gameoflife;

import android.app.Activity;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

class GameController {

  private static final int DELAY = 0;
  private int periodMillis = 50;
  private Timer timer = new Timer();

  private boolean isStarted = false;

  private GameBoardView gameBoardView;
  private GameOfLife gameOfLife;

  private Activity context;

  private long t1;

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
    t1 = System.currentTimeMillis();
    startGameLoop();
  }

  private void startGameLoop() {
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        logStepDuration();
        gameOfLife.nextGeneration();
        context.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            gameBoardView.update(gameOfLife.getGrid());

          }
        });
      }
    }, DELAY, periodMillis);
  }

  private void logStepDuration() {
    long t2 = System.currentTimeMillis();
    //Log.d(GameController.class.getSimpleName(), "Step duration: " + (t2 - t1));
    t1 = t2;
  }

  public void pauseGame() {
    isStarted = false;
    timer.cancel();
  }

  public void resumeGame() {
    timer = new Timer();
    startGame();
  }

  public boolean isRunning() {
    return isStarted;
  }

  void setSpeedPercent(int speedPercent) {
    boolean wasRunning = isStarted;
    if (wasRunning) pauseGame();
    periodMillis = (1000 * (101 - speedPercent)) / 100; // 101 for non-positive period protection.
    if (wasRunning) resumeGame();
  }
}
