package JavaVersion.Player;

import java.util.ArrayList;

import JavaVersion.Board.Board;

/**
 * SudokuPlayer
 */
public class SudokuPlayer {

    ArrayList<ArrayList<Integer>> _originalBoard;
    Board _board;

    public SudokuPlayer(Board board) {
        _originalBoard = board.getBoard();
        _board = board;
    }

    public void makeMove(int i, int j, int value) {
        _board.setValue(i, j, value);
    }

    public void autoSolve() {
        _board.setBoard(_originalBoard);
        _board.solve();
    }

    public void printBoard() {
        _board.print();
    }
}