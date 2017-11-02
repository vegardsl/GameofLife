package com.stjerna.gameoflife;

import com.stjerna.gameoflife.conway.ConwayCell;

public interface GameOfLife {
  void nextGeneration();

  ConwayCell[][] getGrid();

  void activateCell(int x, int y);

  void killCell(int x, int y);
}
