package com.stjerna.gameoflife;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GameBoardTest {

		@Test
		public void createGameBoard_success() {
				GameBoard gameBoard = new GameBoard(100, 100, 0.5f);
				assertNotNull(gameBoard);

				ArrayList<Cell> cells = gameBoard.getCells();
				assertFalse(cells.isEmpty());
		}

		@Test
		public void lonelyCellDies() {
				GameBoard gameBoard = new GameBoard(100, 100, 0.5f);

				ArrayList<Cell> lonelyCellList = new ArrayList<>();
				lonelyCellList.add(new Cell(1,1));
				gameBoard.cells = lonelyCellList;

				gameBoard.nextGeneration();
				ArrayList<Cell> cells = gameBoard.getCells();
				assertTrue(cells.isEmpty());
		}

		@Test
		public void cellWithSingleNeighbourDies() {
				GameBoard gameBoard = new GameBoard(100, 100, 0.5f);

				ArrayList<Cell> cellList = new ArrayList<>();
				cellList.add(new Cell(1,1));
				cellList.add(new Cell(1,2));
				gameBoard.cells = cellList;

				gameBoard.nextGeneration();
				ArrayList<Cell> cells = gameBoard.getCells();
				assertTrue(cells.isEmpty());
		}

		@Test
		public void cellWithTwoNeighboursLives() {
				GameBoard gameBoard = new GameBoard(100, 100, 0.5f);

				ArrayList<Cell> cellList = new ArrayList<>();
				cellList.add(new Cell(1,1));
				cellList.add(new Cell(1,2));
				cellList.add(new Cell(1,3));
				gameBoard.cells = cellList;

				gameBoard.nextGeneration();
				ArrayList<Cell> cells = gameBoard.getCells();
				assertTrue(cells.contains(new Cell(1,2)));
		}

		@Test
		public void cellWithFourOrMoreNeighboursDies() {
				GameBoard gameBoard = new GameBoard(100, 100, 0.5f);

				ArrayList<Cell> cellList = new ArrayList<>();
				cellList.add(new Cell(1,1));
				cellList.add(new Cell(1,2));
				cellList.add(new Cell(1,3));
				cellList.add(new Cell(2,2));
				cellList.add(new Cell(0,2));
				gameBoard.cells = cellList;

				gameBoard.nextGeneration();
				ArrayList<Cell> cells = gameBoard.getCells();
				assertFalse(cells.contains(new Cell(1,2)));
		}

		@Test
		public void deadCellWithTreeNeighboursBecomesAlive() {
				GameBoard gameBoard = new GameBoard(100, 100, 0.5f);

				ArrayList<Cell> cellList = new ArrayList<>();
				cellList.add(new Cell(1,1));
				cellList.add(new Cell(2,2));
				cellList.add(new Cell(0,2));
				gameBoard.cells = cellList;

				gameBoard.nextGeneration();
				ArrayList<Cell> cells = gameBoard.getCells();
				assertTrue(cells.contains(new Cell(1,2)));
		}
}
