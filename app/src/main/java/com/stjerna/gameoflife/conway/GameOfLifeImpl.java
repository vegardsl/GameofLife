package com.stjerna.gameoflife.conway;

import com.stjerna.gameoflife.CellStatus;
import com.stjerna.gameoflife.GameOfLife;

public class GameOfLifeImpl extends GameOfLife {

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
      for (ConwayCell cell : column) {
        cell.determineFutureStatus();
      }
    }
    for (ConwayCell[] column : grid) {
      for (ConwayCell cell : column) {
        cell.transitionToFutureStatus();
      }
    }
  }

  @Override
  public ConwayCell[][] getGrid() {
    return grid;
  }

  @Override
  public void activateCell(int x, int y) {
    if (isOutOfBounds(x, y)) return;
    grid[x][y].setStatus(CellStatus.ALIVE);
  }

  @Override
  public void killCell(int x, int y) {
    if (isOutOfBounds(x, y)) return;
    grid[x][y].setStatus(CellStatus.DEAD);
  }
}
