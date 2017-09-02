package com.stjerna.gameoflife;

import java.util.ArrayList;

class GameBoard {

  ArrayList<Cell> cells;

  private int rows;
  private int columns;
  private GameBoard gameBoard;

  GameBoard(int rows, int columns, float probabilityOfLife) {

    cells = new ArrayList<>();
    this.rows = rows;
    this.columns = columns;

    for (int x = 0; x < rows; x++) {
      for (int y = 0; y < this.columns; y++) {
        if (probabilityOfLife < Math.random()) {
          cells.add(new Cell(x, y));
        }
      }
    }
  }

  ArrayList<Cell> getCells() {
    return cells;
  }

  void nextGeneration() {
    ArrayList<Cell> nextGenCells = checkLivingCells(cells);
    nextGenCells = checkDeadCells(nextGenCells, cells);
    cells = nextGenCells;
  }

  private ArrayList<Cell> checkLivingCells(ArrayList<Cell> cells) {
    ArrayList<Cell> nextGenLivingCells = new ArrayList<>();
    for (Cell cell : cells) {
      int neighborCount = getNeighbourCount(cell, cells);
      if (neighborCount < 4) {
        if (1 < neighborCount) nextGenLivingCells.add(cell);
      }
    }
    return nextGenLivingCells;
  }

  private ArrayList<Cell> checkDeadCells(ArrayList<Cell> nextGenCells, ArrayList<Cell> cells) {
    for (int x = 0; x < rows; x++) {
      for (int y = 0; y < this.columns; y++) {
        Cell checkedCell = new Cell(x, y);
        if (cells.contains(checkedCell)) continue;
        int neighbourCount = getNeighbourCount(new Cell(x, y), cells);
        if (2 < neighbourCount) nextGenCells.add(checkedCell);
      }
    }
    return nextGenCells;
  }


  private int getNeighbourCount(Cell cell, ArrayList<Cell> cells) {
    int neighborCount = 0;
    Cell testCell = new Cell(cell.getX(), cell.getY());
    testCell.offset(-1, 0);
    if (cells.contains(testCell)) neighborCount++;
    testCell.offset(0, 1);
    if (cells.contains(testCell)) neighborCount++;
    testCell.offset(1, 0);
    if (cells.contains(testCell)) neighborCount++;
    testCell.offset(1, 0);
    if (cells.contains(testCell)) neighborCount++;
    testCell.offset(0, -1);
    if (cells.contains(testCell)) neighborCount++;
    testCell.offset(0, -1);
    if (cells.contains(testCell)) neighborCount++;
    testCell.offset(-1, 0);
    if (cells.contains(testCell)) neighborCount++;
    testCell.offset(-1, 0);
    if (cells.contains(testCell)) neighborCount++;
    return neighborCount;
  }

  int getRows() {
    return rows;
  }

  int getColumns() {
    return columns;
  }
}
