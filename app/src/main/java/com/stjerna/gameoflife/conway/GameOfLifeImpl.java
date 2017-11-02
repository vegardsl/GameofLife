package com.stjerna.gameoflife.conway;

import com.stjerna.gameoflife.CellStatus;
import com.stjerna.gameoflife.GameOfLife;

public class GameOfLifeImpl implements GameOfLife {

  private ConwayCell[][] grid;

  public GameOfLifeImpl(int rows, int columns) {
    grid = new ConwayCell[rows][columns];
    for (int x = 0; x < rows; x++) {
      for (int y = 0; y < columns; y++) {
        grid[x][y] = new ConwayCell(x, y, CellStatus.DEAD, grid);
      }
    }
  }

  @Override
  public void nextGeneration() {
    for (ConwayCell[] column : grid) {
      for (int y = 0; y < column.length; y++) {
        column[y].determineFutureStatus();
      }
    }
    for (ConwayCell[] column : grid) {
      for (int y = 0; y < column.length; y++) {
        column[y].transitionToFutureStatus();
      }
    }
  }

  @Override
  public ConwayCell[][] getGrid() {
    return grid;
  }

  @Override
  public void activateCell(int x, int y) {
    grid[x][y].setStatus(CellStatus.ALIVE);
  }

  @Override
  public void killCell(int x, int y) {
    grid[x][y].setStatus(CellStatus.DEAD);
  }
}
