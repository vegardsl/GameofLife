package com.stjerna.gameoflife;

public class NewCell {
  int x;
  int y;
  NewCell[][] grid;
  CellStatus status;
  CellStatus futureStatus;

  NewCell(int x, int y, CellStatus status, NewCell[][] grid) {
    this.x = x;
    this.y = y;
    this.grid = grid;
    this.status = status;
  }

  void setStatus(CellStatus status) {
    this.status = status;
  }

  CellStatus getStatus() {
    return status;
  }

  public CellStatus determineFutureStatus() {
    int livingNeighbours = countLivingNeighbours();
    if (status == CellStatus.ALIVE) {
      if (livingNeighbours < 2) {
        futureStatus = CellStatus.DEAD;
      } else if (livingNeighbours < 4) {
        futureStatus = CellStatus.ALIVE;
      } else {
        futureStatus = CellStatus.DEAD;
      }
    } else {
      if (livingNeighbours == 3) {
        futureStatus = CellStatus.ALIVE;
      }
    }
    return futureStatus;
  }

  private int countLivingNeighbours() {
    int neighborCount = 0;

    for (int j = -1; j < 2; j++) {
      for (int k = -1; k < 2; k++) {
        if (j == 0 && k == 0) continue;
        if (y + j != -1 && y + j != (grid[0].length)) {
          if (x + k != -1 && x + k != (grid.length)) {
            if (grid[x + k][y + j].getStatus() == CellStatus.ALIVE) neighborCount++;
          }
        }
      }
    }

    return neighborCount;
  }

  public void transitionToFutureStatus() {
    status = futureStatus;
  }
}
