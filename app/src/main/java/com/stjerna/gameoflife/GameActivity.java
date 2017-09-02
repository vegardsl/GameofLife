package com.stjerna.gameoflife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;

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
        // make sure it is not called anymore
        gameBoardView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

        gameController = new GameController(activity, gameBoardView, new GameOfLifeImpl(50,40));
        gameController.seed(0.3f);
        gameController.startGame();
      }
    });
  }
}
