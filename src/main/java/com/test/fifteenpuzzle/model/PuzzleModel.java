package com.test.fifteenpuzzle.model;

public interface PuzzleModel {
	void newPuzzle();
	boolean isSolved();
	void moveBlankTile(MoveDirection command);
	int[] getTiles();
	int getSideSize();
	int getBlankPosition();
}
