package com.stjerna.gameoflife;

import com.stjerna.gameoflife.conway.ConwayCell;

public abstract class GameOfLife {

  protected ConwayCell[][] grid;

  public abstract void nextGeneration();

  public abstract ConwayCell[][] getGrid();

  public abstract void activateCell(int x, int y);

  public abstract void killCell(int x, int y);

  protected boolean isOutOfBounds(int x, int y) {
    return x < 0 || y < 0 || grid.length <= x || grid[0].length <= y;
  }
}
