package com.stjerna.gameoflife;

public interface GameOfLife {
  void nextGeneration();

  NewCell[][] getGrid();

  void activateCell(int x, int y);

  void killCell(int x, int y);
}
