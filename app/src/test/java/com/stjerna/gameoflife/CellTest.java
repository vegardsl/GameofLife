package com.stjerna.gameoflife;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vegar on 9/2/2017.
 */
public class CellTest {

  @Test
  public void canCreate() {
    Cell cell = new Cell(1, 1);
    assertNotNull(cell);
  }

  @Test
  public void noOffset() {
    Cell cell = new Cell(1, 1);
    cell.offset(0, 0);
    assertEquals(1, cell.getX());
    assertEquals(1, cell.getY());
  }

  @Test
  public void offset() {
    Cell cell = new Cell(1, 1);
    cell.offset(1, 1);
    assertEquals(2, cell.getX());
    assertEquals(2, cell.getY());
  }

  @Test
  public void equalsIsTrue() {
    Cell cellA = new Cell(1, 1);
    Cell cellB = new Cell(1, 1);
    assertTrue(cellA.equals(cellB));
  }

  @Test
  public void equalsIsFalse() {
    Cell cellA = new Cell(1, 1);
    Cell cellB = new Cell(1, 2);
    assertFalse(cellA.equals(cellB));
  }
}