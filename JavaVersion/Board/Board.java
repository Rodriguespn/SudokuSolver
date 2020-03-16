package JavaVersion.Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import JavaVersion.DifficultyLevel.DifficultyLevel;
import JavaVersion.SudokuSolver.SudokuSolver;

/**
 * Board
 */
public abstract class Board {

    private ArrayList<ArrayList<Integer>> _board;
    private int _size;
    private DifficultyLevel _ld;

    public Board(int size, DifficultyLevel ld) {
        _size = size;
        _ld = ld;
        generateNewBoard();
    }

    public static void main(String[] args) {
        SudokuSolver s = new SudokuSolver(new EasyBoard(9));
        s.printBoard();
        s.solve();
        System.out.println();
        s.printBoard();
    }

    // generate a completely solved sudoku board
	private ArrayList<ArrayList<Integer>> generateNewBoard() {

        _board = new ArrayList<ArrayList<Integer>>(_size);
        
        for (int i = 0; i < _size; i++) {
            ArrayList<Integer> line = new ArrayList<Integer>(_size);
            for (int j = 0; j < _size; j++) {   
                line.add(0);
            }
            _board.add(line);
        }

        Double squaresNd = Math.sqrt(_size); 
        int squareN = squaresNd.intValue();
        for (int i = 0; i < _size; i++) {
			generateRandomSquare(i, squareN);
		}

		solve();

		return _board;
    }
        
        
    public void solve() {
        SudokuSolver s = new SudokuSolver(this);
        s.solve();
    }

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

    private void generateRandomSquare(int i, int squareN) {
        int squareX = i / squareN, squareY = i / squareN, index = 0;
        ArrayList<Integer> line = generateRandomLine();
        for (int y = squareY * squareN; y < squareY*squareN + squareN; y++) {
            for (int x = squareX * squareN; x < squareX*squareN + squareN; x++) {
                System.out.println(y + " " + x);
                _board.get(y).set(x, line.get(index++));
            }
        }
    }
    
    private ArrayList<Integer> generateRandomLine() {
        ArrayList<Integer> line = new ArrayList<Integer>(_size);
        for (int value = 1; value <= _size; value++) {
			line.add(value);
        }
        Collections.shuffle(line);
        return line;
    }

    public void print() {
        for (int i = 0; i <_size; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < _size; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| " + getValue(i, j) + " ");
                }
                else {
                    System.out.print(getValue(i, j) + " ");
                }
            }
            System.out.println();
        }
    }

    public int getSize() {
        return _size;
    }

    public ArrayList<Integer> getLine(int i) {
        return _board.get(i);
    }

    public void setValue(int i, int j, int value) {
        _board.get(i).set(j, value);
    }

    public int getValue(int i, int j) {
        return _board.get(i).get(j);
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

    public DifficultyLevel getLd() {
        return _ld;
    }
}