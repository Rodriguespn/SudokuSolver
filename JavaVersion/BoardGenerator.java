package JavaVersion;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

/**
 * BoardGenerator
 */
public class BoardGenerator {

    private int _size;
    private Board _board;

    public BoardGenerator(int size) {
        _size = size;
        _board = generate();
    }

	// generate a completely solved sudoku board
	public Board generate() {

        
        
        Double squaresNd = Math.sqrt(_size); 
        int squareN = squaresNd.intValue();
        for (int i = 0*squareN; i < _size; i++) {
			generateRandomSquare(i, squareN);
		}

		SudokuSolver s = new SudokuSolver(_board);
        s.solve();

        _board = s.getIntBoard();

        Random rand = new Random();
        for (int k = 0; k < 10; k++) {
            int i = rand.nextInt(_size), j = rand.nextInt(_size);
            _board[i][j] = 0;
        }

		return _board;
    }

    private void generateRandomSquare(int i, int squareN) {
        int squareX = i / squareN, squareY = i / squareN, index = 0;
        ArrayList<Integer> line = generateRandomLine();
        for (int y = squareY * squareN; y < squareY*squareN + squareN; y++) {
            for (int x = squareX * squareN; x < squareX*squareN + squareN; x++) {
                _board[y][x] = line.get(index++);
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

    public static void main(String[] args) {
        BoardGenerator b = new BoardGenerator(9);

        int board[][] = b.generate();

        SudokuSolver s = new SudokuSolver(board, 9);
        s.printBoard();
        s.solve();
        System.out.println();
        s.printBoard();
    }
}