package com.stjerna.gameoflife;

public class GameOfLifeImpl implements GameOfLife {

  private NewCell[][] grid;

  public GameOfLifeImpl(int rows, int columns) {
    grid = new NewCell[rows][columns];
    for (int x = 0; x < rows; x++) {
      for (int y = 0; y < columns; y++) {
        grid[x][y] = new NewCell(x, y, CellStatus.DEAD, grid);
      }
    }
  }

  @Override
  public void nextGeneration() {
    for (NewCell[] column : grid) {
      for (int y = 0; y < column.length; y++) {
        column[y].determineFutureStatus();
      }
    }
    for (NewCell[] column : grid) {
      for (int y = 0; y < column.length; y++) {
        column[y].transitionToFutureStatus();
      }
    }
  }

  @Override
  public NewCell[][] getGrid() {
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
