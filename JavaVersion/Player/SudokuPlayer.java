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
        _board = board;
        _originalBoard = copyBoard();
    }

    private ArrayList<ArrayList<Integer>> copyBoard() {
        ArrayList<ArrayList<Integer>> newBoard = new ArrayList<ArrayList<Integer>> (_board.getSize());
        for (int i = 0; i < _board.getSize(); i++) {
            ArrayList<Integer> newLine = new ArrayList<Integer> (_board.getSize());
            for (int value: _board.getLine(i)) {
                newLine.add(value);
            }
            newBoard.add(newLine);
        }
        return newBoard;
    }

    public void makeMove(int i, int j, int value) {
        if (_board.isWhiteSpace(i, j)) { _board.setValue(i, j, value); }
    }

    public void autoSolve() {
        _board.setBoard(_originalBoard);
        _board.solve();
    }

    public void printBoard() {
        _board.print();
    }
}