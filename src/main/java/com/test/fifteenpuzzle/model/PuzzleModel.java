package com.test.fifteenpuzzle.model;

import com.test.fifteenpuzzle.GameCommand;

public interface PuzzleModel {
	void newPuzzle();
	boolean isSolved();
	void moveBlankTile(GameCommand command);
	int[] getTiles();
	int getSideSize();
	int getBlankPosition();
}
