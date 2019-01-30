package com.test.fifteenpuzzle.controller.impl;

import com.test.fifteenpuzzle.model.MoveDirection;
import com.test.fifteenpuzzle.model.PuzzleModel;
import com.test.fifteenpuzzle.view.PuzzleView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PuzzleControllerImplTest {

	@InjectMocks
	private PuzzleControllerImpl puzzleController;

	@Mock
	private PuzzleModel puzzleModel;
	@Mock
	private PuzzleView puzzleView;
	@Captor
	private ArgumentCaptor<String> messageCaptor;

	@Test
	public void execute_unknownCommand() throws Exception {

		puzzleController.execute("notSupportedCommand");

		verify(puzzleModel, never()).newPuzzle();
		verify(puzzleModel, never()).moveBlankTile(any(MoveDirection.class));
		verify(puzzleView).showMessage(messageCaptor.capture());
		assertThat(messageCaptor.getValue(), equalTo(PuzzleControllerImpl.COMMAND_NOT_SUPPORTED_MESSAGE));
	}

	@Test
	public void execute_newPuzzle() throws Exception {

		puzzleController.execute("n");

		verify(puzzleModel).newPuzzle();
		verify(puzzleModel, never()).moveBlankTile(any(MoveDirection.class));
		verify_draw(false);
	}

	@Test
	public void execute_end() throws Exception {

		puzzleController.execute("e");

		verify(puzzleModel, never()).newPuzzle();
		verify(puzzleModel, never()).moveBlankTile(any(MoveDirection.class));
	}

	@Test
	public void execute_moveTop_isNotSolved() throws Exception {
		puzzleController.execute("t");

		verify_move(MoveDirection.TOP, false);
	}

	@Test
	public void execute_moveTop_isSolved() throws Exception {
		when(puzzleModel.isSolved()).thenReturn(true);

		puzzleController.execute("t");

		verify_move(MoveDirection.TOP, true);
	}

	@Test
	public void execute_moveDown_isNotSolved() throws Exception {
		puzzleController.execute("d");

		verify_move(MoveDirection.DOWN, false);
	}

	@Test
	public void execute_moveDown_isSolved() throws Exception {
		when(puzzleModel.isSolved()).thenReturn(true);

		puzzleController.execute("d");

		verify_move(MoveDirection.DOWN, true);
	}

	@Test
	public void execute_moveLeft_isNotSolved() throws Exception {
		puzzleController.execute("l");

		verify_move(MoveDirection.LEFT, false);
	}

	@Test
	public void execute_moveLeft_isSolved() throws Exception {
		boolean solved = true;
		when(puzzleModel.isSolved()).thenReturn(solved);

		puzzleController.execute("L");

		verify_move(MoveDirection.LEFT, solved);
	}

	@Test
	public void execute_moveRight_isNotSolved() throws Exception {
		puzzleController.execute("r");

		verify_move(MoveDirection.RIGHT, false);
	}

	@Test
	public void execute_moveRight_isSolved() throws Exception {
		boolean solved = true;
		when(puzzleModel.isSolved()).thenReturn(solved);

		puzzleController.execute("r");

		verify_move(MoveDirection.RIGHT, solved);
	}

	private void verify_move(MoveDirection move, boolean solved) {
		verify(puzzleModel).moveBlankTile(move);
		verify_draw(solved);
	}

	private void verify_draw(boolean solved) {
		verify(puzzleModel).getTiles();
		verify(puzzleModel).getSideSize();
		verify(puzzleModel).isSolved();
		verify(puzzleView).draw(any(), anyInt());
		if(solved) {
			verify(puzzleView).showMessage(PuzzleControllerImpl.CONGRATS_MESSAGE);
		} else {
			verify(puzzleView, never()).showMessage(PuzzleControllerImpl.CONGRATS_MESSAGE);
		}
	}

}