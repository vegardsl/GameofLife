package com.stjerna.gameoflife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class GameBoardView extends View {

  private Paint cellPaint;
  GameBoard gameBoard;
  private int rows;
  private int columns;

  private float cellHeight;
  private float cellWidth;

  public GameBoardView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    cellPaint = new Paint();
    cellPaint.setColor(Color.BLACK);
  }

  @Override
  public void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    if (gameBoard == null) return;

    for (int x = 0; x < rows; x++) {
      for (int y = 0; y < this.columns; y++) {
        if (gameBoard.getCells().contains(new Cell(x, y))) {
          canvas.drawRect(
              cellWidth * x, cellHeight * y,
              cellWidth * (x + 1), cellHeight * (y + 1), cellPaint);
        }
      }
    }
  }

  public void update(GameBoard gameBoard) {
    this.gameBoard = gameBoard;
    invalidate();
  }

  public void setGameBoard(GameBoard gameBoard) {
    this.gameBoard = gameBoard;
    rows = gameBoard.getRows();
    columns = gameBoard.getColumns();

    cellHeight = getHeight() / rows;
    cellWidth = getWidth() / columns;
  }
}
