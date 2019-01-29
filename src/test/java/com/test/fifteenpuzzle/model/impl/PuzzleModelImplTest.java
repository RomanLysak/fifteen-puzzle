package com.test.fifteenpuzzle.model.impl;

import com.test.fifteenpuzzle.GameCommand;
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

		gameModel.moveBlankTile(GameCommand.UNKNOWN);
		fail();
	}

	@Test
	public void moveBlankTile_moveRight_borderCase() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		gameModel.newPuzzle();
		int blankPosition = 15;

		gameModel.moveBlankTile(GameCommand.RIGHT);

		assertThat(gameModel.getBlankPosition(), equalTo(blankPosition));
	}

	@Test
	public void moveBlankTile_moveDown_borderCase() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		gameModel.newPuzzle();
		int blankPosition = 15;

		gameModel.moveBlankTile(GameCommand.DOWN);

		assertThat(gameModel.getBlankPosition(), equalTo(blankPosition));
	}

	@Test
	public void moveBlankTile_moveLeft() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		gameModel.newPuzzle();
		int oldBlankPosition = 15;
		int newBlankPosition = 14;
		int replacedValue = gameModel.getTiles()[newBlankPosition];

		gameModel.moveBlankTile(GameCommand.LEFT);

		assertThat(gameModel.getBlankPosition(), equalTo(newBlankPosition));
		assertThat(gameModel.getTiles()[oldBlankPosition], equalTo(replacedValue));
	}

	@Test
	public void moveBlankTile_moveTop() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		gameModel.newPuzzle();
		int oldBlankPosition = 15;
		int newBlankPosition = 11;
		int replacedValue = gameModel.getTiles()[newBlankPosition];

		gameModel.moveBlankTile(GameCommand.TOP);

		assertThat(gameModel.getBlankPosition(), equalTo(newBlankPosition));
		assertThat(gameModel.getTiles()[oldBlankPosition], equalTo(replacedValue));
	}

	@Test
	public void isSolved_negative_lastNotZero() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		int[] tiles = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15};
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