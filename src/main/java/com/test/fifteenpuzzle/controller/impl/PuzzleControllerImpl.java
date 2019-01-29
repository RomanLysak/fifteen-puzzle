package com.test.fifteenpuzzle.controller.impl;

import com.test.fifteenpuzzle.GameCommand;
import com.test.fifteenpuzzle.controller.PuzzleController;
import com.test.fifteenpuzzle.model.MoveDirection;
import com.test.fifteenpuzzle.model.PuzzleModel;
import com.test.fifteenpuzzle.view.PuzzleView;

public class PuzzleControllerImpl implements PuzzleController {

	public static final String GAME_OVER_MESSAGE = "Game over";
	public static final String COMMAND_NOT_SUPPORTED_MESSAGE = "Game command is not supported";
	public static final String CONGRATS_MESSAGE = "You won, congratulations!";

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
				gameView.showMessage(GAME_OVER_MESSAGE);
				return;
			case TOP:
				gameModel.moveBlankTile(MoveDirection.TOP);
				redraw();
				break;
			case DOWN:
				gameModel.moveBlankTile(MoveDirection.DOWN);
				redraw();
				break;
			case LEFT:
				gameModel.moveBlankTile(MoveDirection.LEFT);
				redraw();
				break;
			case RIGHT:
				gameModel.moveBlankTile(MoveDirection.RIGHT);
				redraw();
				break;
			default:
				gameView.showMessage(COMMAND_NOT_SUPPORTED_MESSAGE);
		}
	}

	private void redraw() {
		gameView.draw(gameModel.getTiles(), gameModel.getSideSize());
		if (gameModel.isSolved()) {
			gameView.showMessage(CONGRATS_MESSAGE);
		}
	}
}
