package com.stjerna.gameoflife;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameOfLifeTest {

  private GameOfLife gameOfLife;

  private void initGame(int columns, int rows) {
    gameOfLife = new GameOfLifeImpl(rows, columns);
  }

  @Test
  public void createGame() {
    int columns = 100;
    int rows = 50;
    initGame(columns, rows);
    NewCell[][] grid = gameOfLife.getGrid();
    assertEquals(rows, grid.length);
    assertEquals(columns, grid[0].length);
  }

  @Test
  public void activateCell() {
    initGame(100, 50);
    gameOfLife.activateCell(1,1);
    NewCell[][] grid = gameOfLife.getGrid();
    assertEquals(CellStatus.ALIVE, grid[1][1].getStatus());
  }

  @Test
  public void canCallNextGeneration() {
    initGame(100, 50);
    gameOfLife.nextGeneration();
  }
}