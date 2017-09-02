package com.stjerna.gameoflife;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NaiveGameOfLifeTest {

  @Test
  public void createGameBoard_success() {
    NaiveGameBoard naiveGameBoard = new NaiveGameBoard(100, 100, 0.5f);
    assertNotNull(naiveGameBoard);

    ArrayList<Cell> cells = naiveGameBoard.getCells();
    assertFalse(cells.isEmpty());
  }

  @Test
  public void lonelyCellDies() {
    NaiveGameBoard naiveGameBoard = new NaiveGameBoard(100, 100, 0.5f);

    ArrayList<Cell> lonelyCellList = new ArrayList<>();
    lonelyCellList.add(new Cell(1, 1));
    naiveGameBoard.cells = lonelyCellList;

    naiveGameBoard.nextGeneration();
    ArrayList<Cell> cells = naiveGameBoard.getCells();
    assertTrue(cells.isEmpty());
  }

  @Test
  public void cellWithSingleNeighbourDies() {
    NaiveGameBoard naiveGameBoard = new NaiveGameBoard(100, 100, 0.5f);

    ArrayList<Cell> cellList = new ArrayList<>();
    cellList.add(new Cell(1, 1));
    cellList.add(new Cell(1, 2));
    naiveGameBoard.cells = cellList;

    naiveGameBoard.nextGeneration();
    ArrayList<Cell> cells = naiveGameBoard.getCells();
    assertTrue(cells.isEmpty());
  }

  @Test
  public void cellWithTwoNeighboursLives() {
    NaiveGameBoard naiveGameBoard = new NaiveGameBoard(100, 100, 0.5f);

    ArrayList<Cell> cellList = new ArrayList<>();
    cellList.add(new Cell(1, 1));
    cellList.add(new Cell(1, 2));
    cellList.add(new Cell(1, 3));
    naiveGameBoard.cells = cellList;

    naiveGameBoard.nextGeneration();
    ArrayList<Cell> cells = naiveGameBoard.getCells();
    assertTrue(cells.contains(new Cell(1, 2)));
  }

  @Test
  public void cellWithFourOrMoreNeighboursDies() {
    NaiveGameBoard naiveGameBoard = new NaiveGameBoard(100, 100, 0.5f);

    ArrayList<Cell> cellList = new ArrayList<>();
    cellList.add(new Cell(1, 1));
    cellList.add(new Cell(1, 2));
    cellList.add(new Cell(1, 3));
    cellList.add(new Cell(2, 2));
    cellList.add(new Cell(0, 2));
    naiveGameBoard.cells = cellList;

    naiveGameBoard.nextGeneration();
    ArrayList<Cell> cells = naiveGameBoard.getCells();
    assertFalse(cells.contains(new Cell(1, 2)));
  }

  @Test
  public void deadCellWithTreeNeighboursBecomesAlive() {
    NaiveGameBoard naiveGameBoard = new NaiveGameBoard(100, 100, 0.5f);

    ArrayList<Cell> cellList = new ArrayList<>();
    cellList.add(new Cell(1, 1));
    cellList.add(new Cell(2, 2));
    cellList.add(new Cell(0, 2));
    naiveGameBoard.cells = cellList;

    naiveGameBoard.nextGeneration();
    ArrayList<Cell> cells = naiveGameBoard.getCells();
    assertTrue(cells.contains(new Cell(1, 2)));
  }
}
