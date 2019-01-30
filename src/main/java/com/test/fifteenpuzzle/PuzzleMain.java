package com.test.fifteenpuzzle;

import com.test.fifteenpuzzle.controller.PuzzleController;
import com.test.fifteenpuzzle.controller.impl.PuzzleControllerImpl;
import com.test.fifteenpuzzle.model.PuzzleModel;
import com.test.fifteenpuzzle.model.impl.PuzzleModelImpl;
import com.test.fifteenpuzzle.view.PuzzleView;
import com.test.fifteenpuzzle.view.impl.PuzzleViewImpl;

import java.util.Scanner;

public class PuzzleMain {

	public static void main(String[] args) {
		int fieldSideSize = 4;
		PuzzleView gameView = new PuzzleViewImpl();
		PuzzleModel gameModel = new PuzzleModelImpl(fieldSideSize);
		PuzzleController gameController = new PuzzleControllerImpl(gameView, gameModel);

		gameView.showMessage("n - new game, e - end game, l,r,t,d - moveBlankTile left, right, top, down appropriately");
		gameView.showMessage("Please enter the command: ");
		Scanner scanner = new Scanner(System.in);
		while (!scanner.hasNext(GameCommand.END.getName())) {
			gameController.execute(scanner.next());
			gameView.showMessage("Next command: ");
		}
		gameView.showMessage("Game over!");
	}
}
