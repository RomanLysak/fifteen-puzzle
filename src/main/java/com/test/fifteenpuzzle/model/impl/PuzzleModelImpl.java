package com.test.fifteenpuzzle.model.impl;

import com.test.fifteenpuzzle.GameCommand;
import com.test.fifteenpuzzle.model.PuzzleModel;

import java.util.Random;

public class PuzzleModelImpl implements PuzzleModel {

	private final int[] tiles;
	private final int sideSize;
	private final int tilesNumber;
	private final Random random;

	private int blankPosition;

	public PuzzleModelImpl(int sideSize) {
		this.sideSize = sideSize;
		this.tilesNumber = sideSize * sideSize - 1;
		this.tiles = new int[tilesNumber + 1];
		random = new Random();
	}

	@Override
	public boolean isSolved() {
		if (tiles[tilesNumber] != 0) {
			return false;
		}
		for (int i = 0; i < tilesNumber; i++) {
			if (tiles[i] != i + 1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void newPuzzle() {
		do {
			reset();
			shuffle();
		} while (!isSolvable());
	}

	@Override
	public void moveBlankTile(GameCommand command) {
		int newBlankPosition = calcNewBlankPosition(command);
		if (!isValidBlankPosition(newBlankPosition)) {
			return;
		}
		tiles[blankPosition] = tiles[newBlankPosition];
		blankPosition = newBlankPosition;
		tiles[blankPosition] = 0;
	}

	@Override
	public int[] getTiles() {
		return this.tiles;
	}

	@Override
	public int getSideSize() {
		return this.sideSize;
	}

	@Override
	public int getBlankPosition() {
		return this.blankPosition;
	}

	private int calcNewBlankPosition(GameCommand command) throws RuntimeException {
		int offset;
		switch (command) {
			case LEFT:
				offset = -1;
				break;
			case RIGHT:
				offset = 1;
				break;
			case TOP:
				offset = -sideSize;
				break;
			case DOWN:
				offset = sideSize;
				break;
			default:
				throw new RuntimeException("Command is not supported!");
		}

		return blankPosition + offset;
	}

	private boolean isValidBlankPosition(int newBlankPosition) {
		int xNewBlankPosition = newBlankPosition % sideSize;
		int yNewBlankPosition = newBlankPosition / sideSize;
		int xBlankPositionPrevious = blankPosition % sideSize;
		int yBlankPositionPrevious = blankPosition / sideSize;

		if ((newBlankPosition <= tilesNumber && newBlankPosition >= 0) &&
				(xNewBlankPosition == xBlankPositionPrevious
						|| yNewBlankPosition == yBlankPositionPrevious)) {
			return true;
		}
		return false;
	}

	private boolean isSolvable() {
		int inversionsCounter = 0;
		for (int i = 0; i < tilesNumber; i++) {
			for (int j = 0; j < i; j++) {
				if (tiles[j] > tiles[i]) {
					inversionsCounter++;
				}
			}
		}
		return inversionsCounter % 2 == 0;
	}

	private void reset() {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = (i + 1) % tiles.length;
		}
		blankPosition = tilesNumber;
	}

	private void shuffle() {
		int i = tilesNumber;
		while (i > 1) {
			int randInt = random.nextInt(i--);
			int tmp = tiles[randInt];
			tiles[randInt] = tiles[i];
			tiles[i] = tmp;
		}
	}

}
