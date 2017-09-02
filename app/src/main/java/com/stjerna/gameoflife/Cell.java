package com.stjerna.gameoflife;

/**
	* Created by vegar on 9/1/2017.
	*/

class Cell {

		private int x;
		private int y;

		public Cell(int x, int y) {
				this.x = x;
				this.y = y;
		}

		public Cell offset(int x, int y) {
				this.x += x;
				this.y += y;
				return this;
		}

		@Override
		public boolean equals(Object cell)
		{
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

		public int getX() {
				return x;
		}

		public int getY() {
				return y;
		}
}
