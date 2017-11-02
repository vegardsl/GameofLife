package com.stjerna.gameoflife.conway;

import com.stjerna.gameoflife.CellStatus;
import com.stjerna.gameoflife.conway.ConwayCell;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConwayCellTest {

  ConwayCell[][] grid;

  void createNoNeighbourGrid() {
    createGrid();
    grid[1][1].setStatus(CellStatus.ALIVE);
  }

  void createOneNeighbourGrid() {
    createGrid();
    grid[1][1].setStatus(CellStatus.ALIVE);
    grid[2][2].setStatus(CellStatus.ALIVE);
  }

  private void createTwoNeighbourGrid() {
    createGrid();
    grid[1][1].setStatus(CellStatus.ALIVE);
    grid[2][2].setStatus(CellStatus.ALIVE);
    grid[0][1].setStatus(CellStatus.ALIVE);
  }

  private void createFourNeighbourGrid() {
    createGrid();
    grid[1][1].setStatus(CellStatus.ALIVE);
    grid[2][2].setStatus(CellStatus.ALIVE);
    grid[0][1].setStatus(CellStatus.ALIVE);
    grid[2][1].setStatus(CellStatus.ALIVE);
    grid[1][2].setStatus(CellStatus.ALIVE);
  }

  private void createThreeNeighbourGrid_deadCenter() {
    createGrid();
    grid[0][1].setStatus(CellStatus.ALIVE);
    grid[1][2].setStatus(CellStatus.ALIVE);
    grid[2][1].setStatus(CellStatus.ALIVE);
  }

  private void createGrid() {
    grid = new ConwayCell[3][3];
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        grid[x][y] = new ConwayCell(x, y, CellStatus.DEAD, grid);
      }
    }
  }

  @Test
  public void livingCell_noNeighbours_willDie() {
    createNoNeighbourGrid();
    CellStatus futureStatus = grid[1][1].determineFutureStatus();
    assertEquals(CellStatus.DEAD, futureStatus);
  }

  @Test
  public void livingCell_oneNeighbours_willDie() {
    createOneNeighbourGrid();
    CellStatus futureStatus = grid[1][1].determineFutureStatus();
    assertEquals(CellStatus.DEAD, futureStatus);
  }

  @Test
  public void livingCell_twoNeighbours_willLive() {
    createTwoNeighbourGrid();
    CellStatus futureStatus = grid[1][1].determineFutureStatus();
    assertEquals(CellStatus.ALIVE, futureStatus);
  }

  @Test
  public void livingCell_moreThanThreeNeighbours_willDie() {
    createFourNeighbourGrid();
    CellStatus futureStatus = grid[1][1].determineFutureStatus();
    assertEquals(CellStatus.DEAD, futureStatus);
  }

  @Test
  public void deadCell_exactlyThreeNeighbours_becomesLiving() {
    createThreeNeighbourGrid_deadCenter();
    CellStatus futureStatus = grid[1][1].determineFutureStatus();
    assertEquals(CellStatus.ALIVE, futureStatus);
  }
}