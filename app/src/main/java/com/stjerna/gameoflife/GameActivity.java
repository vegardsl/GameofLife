package com.stjerna.gameoflife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.stjerna.gameoflife.conway.GameOfLifeImpl;

public class GameActivity extends AppCompatActivity {

  public static final int INITIAL_SPEED_PERCENT = 50;
  GameBoardView gameBoardView;
  GameController gameController;
  SeekBar speedSelector;

  Activity activity;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    activity = this;

    speedSelector = findViewById(R.id.speed_selector);
    speedSelector.setProgress(INITIAL_SPEED_PERCENT);
    speedSelector.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Log.d(GameActivity.class.getSimpleName(), "Seekbar pos: " + i + " boolean: " + b);
        gameController.setSpeedPercent(i);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        Log.d(GameActivity.class.getSimpleName(), "onStartTrackingTouch");
      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        Log.d(GameActivity.class.getSimpleName(), "onStopTrackingTouch");
      }
    });

    gameBoardView = findViewById(R.id.game_board);
    gameBoardView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        gameBoardView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        BoardSize boardSize = determineBoardSize(gameBoardView);
        gameController = new GameController(activity, gameBoardView, new GameOfLifeImpl(boardSize.width, boardSize.height));
        gameController.setSpeedPercent(INITIAL_SPEED_PERCENT);
        gameController.seed(0.4f);
      }
    });
  }

  private BoardSize determineBoardSize(GameBoardView gameBoardView) {
    Log.d(GameActivity.class.getSimpleName(), "H: " + gameBoardView.getHeight());
    Log.d(GameActivity.class.getSimpleName(), "W: " + gameBoardView.getWidth());
    return new BoardSize(90).determine(gameBoardView.getHeight(), gameBoardView.getWidth());
  }

  public void onClickToggleGame(View view) {
    if (gameController.isRunning()) {
      gameController.pauseGame();
      if (view instanceof ImageButton) ((ImageButton) view).setImageResource(R.drawable.ic_play);
    } else {
      gameController.resumeGame();
      if (view instanceof ImageButton) ((ImageButton) view).setImageResource(R.drawable.ic_pause);
    }
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
