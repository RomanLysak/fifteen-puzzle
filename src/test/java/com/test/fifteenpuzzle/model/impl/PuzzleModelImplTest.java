package com.test.fifteenpuzzle.model.impl;

import com.test.fifteenpuzzle.model.PuzzleModel;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class PuzzleModelImplTest {

	@Test
	public void newPuzzle_positive() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);
		int[] expectedTiles = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};

		gameModel.newPuzzle();

		int[] result = gameModel.getTiles();
		assertThat(result, not(equalTo(expectedTiles)));
		assertThat(result[result.length - 1], equalTo(0));
	}

	@Test
	public void getSideSize_positive() throws Exception {
		PuzzleModel gameModel = new PuzzleModelImpl(4);

		int result = gameModel.getSideSize();

		assertThat(result, equalTo(4));
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