package JavaVersion.Player;

import java.util.ArrayList;

import JavaVersion.Board.Board;

/**
 * Class that represents a Sudoku Player
 */
public class SudokuPlayer {

    // The board before the player modify it
    ArrayList<ArrayList<Integer>> _originalBoard;
    // The board where the player will play
    Board _board;

    public SudokuPlayer(Board board) {
        _board = board;
        _originalBoard = copyBoard();
    }

    /**
     * Copies the board attribute from the Board object and returns a list with that board
     * @return the copy of the board
     */
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

    /**
     * Swaps the value of a cell in the position (j,i) of the board with the value choosen by the player 
     * @param i the y component of the board
     * @param j the x component of the board
     * @param value the new value of the cell
     */
    public void makeMove(int i, int j, int value) {
        if (_board.isWhiteSpace(i, j)) {
            _board.setValue(i, j, value);
        } /*else {
            throw exception
        }*/
    }

    /**
     * Solves the original Sudoku board
     */
    public void autoSolve() {
        _board.setBoard(_originalBoard);
        _board.solve();
    }

    /**
     * Prints the current game board
     */
    public void printBoard() {
        _board.print();
    }
}