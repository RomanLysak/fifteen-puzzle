package com.test.fifteenpuzzle.view.impl;

import com.test.fifteenpuzzle.view.PuzzleView;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PuzzleViewImpl implements PuzzleView {
	@Override
	public void draw(int[] tiles, int sideSize) {
		int i = 0;
		while (i < tiles.length) {
			for (int j = 0; j < sideSize; j++) {
				System.out.print(String.format("%d  ", tiles[i]));
				i++;
			}
			System.out.println();
		}
	}

	@Override
	public void showMessage(String message) {
		System.out.println(message);
	}
}
