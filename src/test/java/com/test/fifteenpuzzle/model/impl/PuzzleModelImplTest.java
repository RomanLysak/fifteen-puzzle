package com.test.fifteenpuzzle.model.impl;

import com.test.fifteenpuzzle.model.MoveDirection;
import com.test.fifteenpuzzle.model.PuzzleModel;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class PuzzleModelImplTest {

	PuzzleModel puzzleModel;

	@Before
	public void setUp() {
		puzzleModel = new PuzzleModelImpl(4);
		puzzleModel.newPuzzle();
	}

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
		int result = puzzleModel.getSideSize();

		assertThat(result, equalTo(4));
	}

	@Test
	public void getBlankPosition() throws Exception {
		int result = puzzleModel.getBlankPosition();

		assertThat(puzzleModel.getTiles()[result], equalTo(0));
	}

	@Test(expected = RuntimeException.class)
	public void moveBlankTile_notSupportedCommand() throws Exception {
		puzzleModel.moveBlankTile(null);
		fail();
	}

	@Test
	public void moveBlankTile_moveRight_borderCase() throws Exception {
		int[] expectedTiles = new int[puzzleModel.getTiles().length];
		System.arraycopy(puzzleModel.getTiles(), 0, expectedTiles, 0, puzzleModel.getTiles().length);
		int oldBlankPosition = puzzleModel.getBlankPosition();

		puzzleModel.moveBlankTile(MoveDirection.RIGHT);

		assertThat(puzzleModel.getBlankPosition(), equalTo(oldBlankPosition));
		assertThat(puzzleModel.getTiles(), equalTo(expectedTiles));
	}

	@Test
	public void moveBlankTile_moveDown_borderCase() throws Exception {
		int[] expectedTiles = new int[puzzleModel.getTiles().length];
		System.arraycopy(puzzleModel.getTiles(), 0, expectedTiles, 0, puzzleModel.getTiles().length);
		int oldBlankPosition = puzzleModel.getBlankPosition();

		puzzleModel.moveBlankTile(MoveDirection.DOWN);

		assertThat(puzzleModel.getBlankPosition(), equalTo(oldBlankPosition));
		assertThat(puzzleModel.getTiles(), equalTo(expectedTiles));
	}

	@Test
	public void moveBlankTile_moveLeft_borderCase() throws Exception {
		changeBlankPosition(0);
		int[] expectedTiles = new int[puzzleModel.getTiles().length];
		System.arraycopy(puzzleModel.getTiles(), 0, expectedTiles, 0, puzzleModel.getTiles().length);
		int oldBlankPosition = puzzleModel.getBlankPosition();

		puzzleModel.moveBlankTile(MoveDirection.LEFT);

		assertThat(puzzleModel.getTiles(), equalTo(expectedTiles));
		assertThat(puzzleModel.getBlankPosition(), equalTo(oldBlankPosition));
	}

	@Test
	public void moveBlankTile_moveTop_borderCase() throws Exception {
		changeBlankPosition(0);
		int[] expectedTiles = new int[puzzleModel.getTiles().length];
		System.arraycopy(puzzleModel.getTiles(), 0, expectedTiles, 0, puzzleModel.getTiles().length);
		int oldBlankPosition = puzzleModel.getBlankPosition();

		puzzleModel.moveBlankTile(MoveDirection.TOP);

		assertThat(puzzleModel.getTiles(), equalTo(expectedTiles));
		assertThat(puzzleModel.getBlankPosition(), equalTo(oldBlankPosition));
	}

	@Test
	public void moveBlankTile_moveRight() throws Exception {
		changeBlankPosition(5);
		int oldBlankPosition = puzzleModel.getBlankPosition();
		int newBlankPosition = oldBlankPosition + 1;
		int replacedValue = puzzleModel.getTiles()[newBlankPosition];
		int[] expectedTiles = copyArrayToCompare(oldBlankPosition, newBlankPosition, replacedValue);

		puzzleModel.moveBlankTile(MoveDirection.RIGHT);

		assertThat(puzzleModel.getTiles(), equalTo(expectedTiles));
		assertThat(puzzleModel.getBlankPosition(), equalTo(newBlankPosition));
		assertThat(puzzleModel.getTiles()[oldBlankPosition], equalTo(replacedValue));
	}


	@Test
	public void moveBlankTile_moveDown() throws Exception {
		changeBlankPosition(7);
		int oldBlankPosition = puzzleModel.getBlankPosition();
		int newBlankPosition = oldBlankPosition + 4;
		int replacedValue = puzzleModel.getTiles()[newBlankPosition];
		int[] expectedTiles = copyArrayToCompare(oldBlankPosition, newBlankPosition, replacedValue);

		puzzleModel.moveBlankTile(MoveDirection.DOWN);

		assertThat(puzzleModel.getTiles(), equalTo(expectedTiles));
		assertThat(puzzleModel.getBlankPosition(), equalTo(newBlankPosition));
		assertThat(puzzleModel.getTiles()[oldBlankPosition], equalTo(replacedValue));
	}

	@Test
	public void moveBlankTile_moveLeft() throws Exception {
		int oldBlankPosition = puzzleModel.getBlankPosition();
		int newBlankPosition = oldBlankPosition - 1;
		int replacedValue = puzzleModel.getTiles()[newBlankPosition];
		int[] expectedTiles = copyArrayToCompare(oldBlankPosition, newBlankPosition, replacedValue);

		puzzleModel.moveBlankTile(MoveDirection.LEFT);

		assertThat(puzzleModel.getTiles(), equalTo(expectedTiles));
		assertThat(puzzleModel.getBlankPosition(), equalTo(newBlankPosition));
		assertThat(puzzleModel.getTiles()[oldBlankPosition], equalTo(replacedValue));
	}

	@Test
	public void moveBlankTile_moveTop() throws Exception {
		int oldBlankPosition = puzzleModel.getBlankPosition();
		int newBlankPosition = puzzleModel.getBlankPosition() - 4;
		int replacedValue = puzzleModel.getTiles()[newBlankPosition];
		int[] expectedTiles = copyArrayToCompare(oldBlankPosition, newBlankPosition, replacedValue);

		puzzleModel.moveBlankTile(MoveDirection.TOP);

		assertThat(puzzleModel.getTiles(), equalTo(expectedTiles));
		assertThat(puzzleModel.getBlankPosition(), equalTo(newBlankPosition));
		assertThat(puzzleModel.getTiles()[oldBlankPosition], equalTo(replacedValue));
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

	private void changeBlankPosition(int newPositionIndex) {
		int oldBlankPosition = puzzleModel.getBlankPosition();
		int replacedValue = puzzleModel.getTiles()[newPositionIndex];
		puzzleModel.getTiles()[oldBlankPosition] = replacedValue;
		puzzleModel.getTiles()[newPositionIndex] = 0;
	}

	private int[] copyArrayToCompare(int oldBlankPosition, int newBlankPosition, int replacedValue) {
		int[] expectedTiles = new int[puzzleModel.getTiles().length];
		System.arraycopy(puzzleModel.getTiles(), 0, expectedTiles, 0, puzzleModel.getTiles().length);
		expectedTiles[newBlankPosition] = 0;
		expectedTiles[oldBlankPosition] = replacedValue;
		return expectedTiles;
	}

}