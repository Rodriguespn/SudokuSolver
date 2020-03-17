package JavaVersion.Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import JavaVersion.SudokuSolver.SudokuSolver;

/**
 * A Sudoku Game Board
 */
public abstract class Board {

    // The board is represented on an ArrayList of ArrayList of Integers
    private ArrayList<ArrayList<Integer>> _board;
    // The size of the board
    // A Sudoku Game Board will be _size x _size
    private int _size;
    // The difficulty level of the Board
    private DifficultyLevel _ld;
    // The number of big squares on a single line
    int _squareN;

    public Board(int size, DifficultyLevel ld) {
        _size = size;
        _ld = ld;
        Double squaresNd = Math.sqrt(_size); 
        _squareN = squaresNd.intValue();
        generateNewBoard();
    }


    /**
     * Generate a partially solved sudoku board, based on the difficulty level selected
     * 
     * @return partially solved sudoku board, based on the difficulty level selected
     */
	private ArrayList<ArrayList<Integer>> generateNewBoard() {

        clearBoard();
        generateRandomSquares();
        solve();
        prepareBoard();

		return _board;
    }

    /**
     * Clears all the cells of the board
     */
    public void clearBoard() {
        _board = new ArrayList<ArrayList<Integer>>(_size);
        
        for (int i = 0; i < _size; i++) {
            ArrayList<Integer> line = new ArrayList<Integer>(_size);
            for (int j = 0; j < _size; j++) {   
                line.add(0);
            }
            _board.add(line);
        }
    }

    /**
     * Fills the cells of _squareN squares in the left-to-right diagonal of the board 
     */
    public void generateRandomSquares() {
        for (int i = 0; i < _size; i++) {
			generateRandomSquare(i);
		}
    }

    
    /**
     * Fills the cells of square on the board
     * 
     * @param i the position of the square to be filled
     */
    private void generateRandomSquare(int i) {
        int squareX = i / _squareN, squareY = i / _squareN, index = 0;
        ArrayList<Integer> line = generateRandomLine();
        for (int y = squareY * _squareN; y < squareY*_squareN + _squareN; y++) {
            for (int x = squareX * _squareN; x < squareX*_squareN + _squareN; x++) {
                _board.get(y).set(x, line.get(index++));
            }
        }
    }

    /**
     * Generates a new random line with unique integers on a range from 1 to _size
     * 
     * @return a new random line with unique integers on a range from 1 to _size
     */
    private ArrayList<Integer> generateRandomLine() {
        ArrayList<Integer> line = new ArrayList<Integer>(_size);
        for (int value = 1; value <= _size; value++) {
			line.add(value);
        }
        Collections.shuffle(line);
        return line;
    }

    /**
     * Auto completes the board with the right answear, if that exists
     */
    public void solve() {
        SudokuSolver s = new SudokuSolver(this);
        s.solve();
    }

    /**
     * Clears a certain number of cells from the game board, based on the boards difficulty 
     */
    public void prepareBoard() {
        Random rand = new Random();
        for (int k = 0; k < (_ld.getNumberOfBlockToRemove() * _size); k++) {
            int i = rand.nextInt(_size), j = rand.nextInt(_size);
            if (getValue(i, j) == 0) {
                k--;
                continue;
            }
            setValue(i, j, 0);
        }
    }

    /**
     * Prints the all board
     */
    public void print() {
        for (int i = 0; i <_size; i++) {
            if (i % _squareN == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < _size; j++) {
                if (j % _squareN == 0 && j != 0) {
                    System.out.print("| " + getValue(i, j) + " ");
                }
                else {
                    System.out.print(getValue(i, j) + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Checks if a coordinate (j,i) is inside of the board
     * @param i the y component of the board
     * @param j the x component of the board
     * @return true if the position is valid or false otherwise 
     */
    public boolean validCoord(int i, int j) {
        return i >= 0 && i < _size && j >= 0 && j < _size; 
    }

    /**
     * Checks if the cell on the (j,i) position is free
     * @param i the y component of the board
     * @param j the x component of the board
     * @return true if the position is free or false otherwise
     */
    public boolean isWhiteSpace(int i, int j) /*throws InvalidPosition*/{
        if (validCoord(i, j)) {
            return getValue(i, j) == 0;
        }
        return false;
    }

    public int getSize() {
        return _size;
    }

    public ArrayList<Integer> getLine(int i) {
        return _board.get(i);
    }

    public void setValue(int i, int j, int value) throws IndexOutOfBoundsException { /*throws InvalidPosition*/
        if (validCoord(i, j))
            _board.get(i).set(j, value);
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getValue(int i, int j) throws IndexOutOfBoundsException {
        if (validCoord(i, j))
            return _board.get(i).get(j);
        else {
            throw new IndexOutOfBoundsException();
        }    
    }

    public ArrayList<ArrayList<Integer>> getBoard() {
        return _board;
    }

    public int[][] getIntBoard() {
        int intBoard[][] = new int[_size][_size];
        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                intBoard[i][j] = getValue(i, j);
            }
        }
        return intBoard;
    }

    public void setBoard(ArrayList<ArrayList<Integer>> board) {
        _board = board;
    }
    public DifficultyLevel getLd() {
        return _ld;
    }

    public int getNumberOfSquarePerLine() {
        return _squareN;
    }
}