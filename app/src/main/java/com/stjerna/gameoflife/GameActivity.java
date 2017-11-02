package com.stjerna.gameoflife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.stjerna.gameoflife.conway.GameOfLifeImpl;

public class GameActivity extends AppCompatActivity {

  GameBoardView gameBoardView;
  GameController gameController;

  Activity activity;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    activity = this;

    gameBoardView = findViewById(R.id.game_board);
    gameBoardView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        gameBoardView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        BoardSize boardSize = determineBoardSize(gameBoardView);
        gameController = new GameController(activity, gameBoardView, new GameOfLifeImpl(boardSize.width, boardSize.height));
        gameController.seed(0.4f);
        gameController.startGame();
      }
    });
  }

  private BoardSize determineBoardSize(GameBoardView gameBoardView) {
    Log.d(GameActivity.class.getSimpleName(), "H: " + gameBoardView.getHeight());
    Log.d(GameActivity.class.getSimpleName(), "W: " + gameBoardView.getWidth());
    return new BoardSize(180).determine(gameBoardView.getHeight(), gameBoardView.getWidth());
  }

  public void onClickToggleGame(View view) {
    if (gameController.isRunning()) gameController.pauseGame();
    else gameController.resumeGame();
  }

  private class BoardSize {
    int height;
    int width;

    BoardSize(int columns) {
      this.width = columns;
    }

    BoardSize determine(double height, double width) {
      double ratio = height / width;
      this.height = (int) (ratio * this.width);
      return this;
    }
  }
}
