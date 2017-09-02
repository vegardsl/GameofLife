package com.stjerna.gameoflife;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

				gameBoardView = (GameBoardView) findViewById(R.id.game_board);
				gameBoardView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
						@Override
						public void onGlobalLayout() {
								// make sure it is not called anymore
								gameBoardView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

								gameController = new GameController(activity, gameBoardView, new GameBoard(20, 20, 0.95f));
								gameController.startGame();
						}
				});
		}
}
