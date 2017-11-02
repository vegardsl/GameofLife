package com.stjerna.gameoflife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.stjerna.gameoflife.conway.ConwayCell;

public class GameBoardView extends View {

  private Paint cellPaint;

  private float cellHeight;
  private float cellWidth;
  private ConwayCell[][] grid;
  private OnTouchListener onTouchListener;

  public GameBoardView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    cellPaint = new Paint();
    cellPaint.setColor(Color.BLACK);
  }

  public void setOnTouchListener(OnTouchListener onTouchListener) {
    this.onTouchListener = onTouchListener;
  }

  interface OnTouchListener {
    void cellTouchedAtPosition(int x, int y);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    //if (event.getAction() != MotionEvent.ACTION_UP) return false;

    float touchX = event.getX();
    float touchY = event.getY();
    onTouchListener.cellTouchedAtPosition((int) (touchX / cellWidth), (int) (touchY / cellHeight));
    return true;
  }

  @Override
  public void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    if (grid == null) return;

    for (int x = 0; x < grid.length; x++) {
      for (int y = 0; y < grid[0].length; y++) {
        if (grid[x][y].getStatus() == CellStatus.ALIVE) {
          canvas.drawRect(
              cellWidth * x, cellHeight * y,
              cellWidth * (x + 1), cellHeight * (y + 1), cellPaint);
        }
      }
    }
  }

  public void update(ConwayCell[][] grid) {
    this.grid = grid;
    invalidate();
  }

  public void setGrid(ConwayCell[][] grid) {
    this.grid = grid;
    cellHeight = getHeight() / grid[0].length;
    cellWidth = getWidth() / grid.length;
  }
}
