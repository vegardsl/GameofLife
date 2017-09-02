package com.stjerna.gameoflife;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
	* Created by vegar on 9/2/2017.
	*/

public class GameController {

		private int delay = 0; // delay for 0 sec.
		private int period = 1000; // repeat every 10 sec.
		private Timer timer = new Timer();

		boolean isStarted = false;

		GameBoardView gameBoardView;
		GameBoard gameBoard;

		Activity context;

		GameController(Activity context, GameBoardView gameBoardView, GameBoard gameBoard) {
				this.gameBoardView = gameBoardView;
				this.gameBoard = gameBoard;
				this.context = context;
				gameBoardView.setGameBoard(gameBoard);
		}

		public void startGame() {
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
				}, delay, period);
		}
}
