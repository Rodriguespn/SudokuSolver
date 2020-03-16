package JavaVersion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Board
 */
public class Board {

    private ArrayList<ArrayList<Integer>> _board;
    private int _size;

    public Board(int size) {
        _size = size;
        _board = generateNewBoard();
    }

    // generate a completely solved sudoku board
	private ArrayList<ArrayList<Integer>> generateNewBoard() {

        ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>(_size);
        
        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {   
                board.get(i).add(0);
            }
        }

        Double squaresNd = Math.sqrt(_size); 
        int squareN = squaresNd.intValue();
        for (int i = 0; i < _size; i++) {
			generateRandomSquare(i, squareN);
		}

		SudokuSolver s = new SudokuSolver(this);
        s.solve();

        Random rand = new Random();
        for (int k = 0; k < 10; k++) {
            int i = rand.nextInt(_size), j = rand.nextInt(_size);
            _board.get(i).set(j, 0);
        }

		return _board;
    }

    private void generateRandomSquare(int i, int squareN) {
        int squareX = i / squareN, squareY = i / squareN, index = 0;
        ArrayList<Integer> line = generateRandomLine();
        for (int y = squareY * squareN; y < squareY*squareN + squareN; y++) {
            for (int x = squareX * squareN; x < squareX*squareN + squareN; x++) {
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
}