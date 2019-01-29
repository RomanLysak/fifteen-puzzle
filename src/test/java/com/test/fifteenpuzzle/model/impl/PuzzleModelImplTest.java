package com.test.fifteenpuzzle.model.impl;

import com.test.fifteenpuzzle.model.MoveDirection;
import com.test.fifteenpuzzle.model.PuzzleModel;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class PuzzleModelImplTest {

	@Test
	public void newPuzzle() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		int[] expectedTiles = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};

		gameModel.newPuzzle();

		int[] result = gameModel.getTiles();
		assertThat(result, not(equalTo(expectedTiles)));
		assertThat(result[result.length - 1], equalTo(0));
	}

	@Test
	public void getSideSize() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);

		int result = gameModel.getSideSize();

		assertThat(result, equalTo(4));
	}

	@Test
	public void getBlankPosition() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		gameModel.newPuzzle();

		int result = gameModel.getBlankPosition();

		assertThat(gameModel.getTiles()[result], equalTo(0));
	}

	@Test(expected = RuntimeException.class)
	public void moveBlankTile_notSupportedCommand() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);

		gameModel.moveBlankTile(null);
		fail();
	}

	@Test
	public void moveBlankTile_moveRight_borderCase() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		gameModel.newPuzzle();
		int[] expectedTiles = new int[gameModel.getTiles().length];
		System.arraycopy(gameModel.getTiles(), 0, expectedTiles, 0, gameModel.getTiles().length);
		int oldBlankPosition = gameModel.getBlankPosition();

		gameModel.moveBlankTile(MoveDirection.RIGHT);

		assertThat(gameModel.getBlankPosition(), equalTo(oldBlankPosition));
		assertThat(gameModel.getTiles(), equalTo(expectedTiles));
	}

	@Test
	public void moveBlankTile_moveDown_borderCase() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		gameModel.newPuzzle();
		int[] expectedTiles = new int[gameModel.getTiles().length];
		System.arraycopy(gameModel.getTiles(), 0, expectedTiles, 0, gameModel.getTiles().length);
		int oldBlankPosition = gameModel.getBlankPosition();

		gameModel.moveBlankTile(MoveDirection.DOWN);

		assertThat(gameModel.getBlankPosition(), equalTo(oldBlankPosition));
	}

	@Test
	public void moveBlankTile_moveLeft() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		gameModel.newPuzzle();
		int oldBlankPosition = gameModel.getBlankPosition();
		int newBlankPosition = oldBlankPosition - 1;
		int replacedValue = gameModel.getTiles()[newBlankPosition];
		int[] expectedTiles = new int[gameModel.getTiles().length];
		System.arraycopy(gameModel.getTiles(), 0, expectedTiles, 0, gameModel.getTiles().length);
		expectedTiles[newBlankPosition] = 0;
		expectedTiles[oldBlankPosition] = replacedValue;

		gameModel.moveBlankTile(MoveDirection.LEFT);

		assertThat(gameModel.getTiles(), equalTo(expectedTiles));
		assertThat(gameModel.getBlankPosition(), equalTo(newBlankPosition));
		assertThat(gameModel.getTiles()[oldBlankPosition], equalTo(replacedValue));
	}

	@Test
	public void moveBlankTile_moveTop() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		gameModel.newPuzzle();
		int oldBlankPosition = gameModel.getBlankPosition();
		int newBlankPosition = gameModel.getBlankPosition() - 4;
		int replacedValue = gameModel.getTiles()[newBlankPosition];
		int[] expectedTiles = new int[gameModel.getTiles().length];
		System.arraycopy(gameModel.getTiles(), 0, expectedTiles, 0, gameModel.getTiles().length);
		expectedTiles[newBlankPosition] = 0;
		expectedTiles[oldBlankPosition] = replacedValue;

		gameModel.moveBlankTile(MoveDirection.TOP);

		assertThat(gameModel.getTiles(), equalTo(expectedTiles));
		assertThat(gameModel.getBlankPosition(), equalTo(newBlankPosition));
		assertThat(gameModel.getTiles()[oldBlankPosition], equalTo(replacedValue));
	}

	@Test
	public void isSolved_negative_lastNotZero() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		int[] tiles = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		System.arraycopy(tiles, 0, gameModel.getTiles(), 0, tiles.length);

		boolean result = gameModel.isSolved();

		assertFalse(result);
	}

	@Test
	public void isSolved_negative_orderIsNotValid() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		int[] tiles = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10, 9, 11, 12, 13, 14, 15, 0};
		System.arraycopy(tiles, 0, gameModel.getTiles(), 0, tiles.length);

		boolean result = gameModel.isSolved();

		assertFalse(result);
	}

	@Test
	public void isSolved_positive_orderIsValid() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		int[] tiles = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
		System.arraycopy(tiles, 0, gameModel.getTiles(), 0, tiles.length);

		boolean result = gameModel.isSolved();

		assertTrue(result);
	}

}