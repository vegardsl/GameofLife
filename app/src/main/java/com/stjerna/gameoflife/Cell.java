package com.stjerna.gameoflife;

class Cell {

  private int x;
  private int y;

  Cell(int x, int y) {
    this.x = x;
    this.y = y;
  }

  Cell offset(int x, int y) {
    this.x += x;
    this.y += y;
    return this;
  }

  @Override
  public boolean equals(Object cell) {
    boolean equals = false;

    if (cell != null && cell instanceof Cell) {
      equals = coordinatesMatch((Cell) cell);
    }

    return equals;
  }

  private boolean coordinatesMatch(Cell object) {
    return this.x == object.getX()
        && this.y == object.getY();
  }

  int getX() {
    return x;
  }

  int getY() {
    return y;
  }
}
