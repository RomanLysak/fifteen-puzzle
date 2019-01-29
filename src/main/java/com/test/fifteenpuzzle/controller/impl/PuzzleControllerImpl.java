package com.test.fifteenpuzzle.controller.impl;

import com.test.fifteenpuzzle.GameCommand;
import com.test.fifteenpuzzle.controller.PuzzleController;
import com.test.fifteenpuzzle.model.PuzzleModel;
import com.test.fifteenpuzzle.view.PuzzleView;

public class PuzzleControllerImpl implements PuzzleController {

	private final PuzzleView gameView;
	private final PuzzleModel gameModel;

	public PuzzleControllerImpl(PuzzleView gameView, PuzzleModel gameModel) {
		this.gameView = gameView;
		this.gameModel = gameModel;
	}

	@Override
	public void execute(String command) {
		GameCommand gameCommand = GameCommand.of(command);
		switch (gameCommand) {
			case NEW:
				gameModel.newPuzzle();
				redraw();
				break;
			case END:
				gameView.showMessage("Game over");
				return;
			case TOP:
			case DOWN:
			case LEFT:
			case RIGHT:
				move(gameCommand);
				break;
			default:
				gameView.showMessage("GameCommand is not supported");
		}
	}

	private void move(GameCommand gameCommand) {
		gameModel.moveBlankTile(gameCommand);
		redraw();
		if (gameModel.isSolved()) {
			gameView.showMessage("You won, congratulations!");
		}
	}

	private void redraw(){
		gameView.draw(gameModel.getTiles(), gameModel.getSideSize());
	}
}
