package JavaVersion.SudokuSolver;

import java.util.ArrayList;

import JavaVersion.Board.Board;

/**
 * SudokuSolver
 */
public class SudokuSolver {

    private Board _board;

    public SudokuSolver(Board board) {
        _board = board;
    }

    public boolean solve() {
        int pos[] = getNextWhiteSpace();
        if (pos[0] == -1) {
            return true;
        }
        int i = pos[0], j = pos[1];
        for (int value = 1; value <= _board.getSize(); value++) {
            _board.setValue(i, j, value);
            if (validMove(i, j)) {
                if (solve()) {
                    return true;
                }
            }
            _board.setValue(i, j, 0);
        }
        return false;
    }

    private boolean validCoord(int i, int j) {
        return i >= 0 && i < _board.getSize() && j >= 0 && j < _board.getSize(); 
    }
    
    private boolean isWhiteSpace(int i, int j) /*throws InvalidCoordinate*/{
        if (validCoord(i, j)) {
            return _board.getValue(i, j) == 0;
        }
        return false;
    }

    private int[] getNextWhiteSpace() {
        int pos[] = {-1, -1};
        for (int i = 0; i < _board.getSize(); i++) {
            for (int j = 0; j < _board.getSize(); j++) {
                if (isWhiteSpace(i, j)) {
                    pos[0] = i; pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }

    private boolean validLine(ArrayList<Integer> line) {
        ArrayList<Integer> unique = new ArrayList<Integer>();
        for (int el: line) {
            if (el != 0 && unique.contains(el)) 
                return false;
            else {
                unique.add(el);
            }
        }
        return true;
    }

    private boolean validColumn(int j) {
        ArrayList<Integer> column = new ArrayList<Integer>();
        for (int i = 0; i < _board.getSize(); i++) {
            column.add(_board.getValue(i, j));
        }
        return validLine(column);
    }

    private boolean validSquare(int i, int j) {
        int squareX = j / 3, squareY = i / 3;
        ArrayList<Integer> square = new ArrayList<Integer>();
        for (int y = squareY * 3; y < squareY*3 + 3; y++) {
            for (int x = squareX * 3; x < squareX*3 + 3; x++) {
                square.add(_board.getValue(y, x));
            }
        }
        return validLine(square);
    }

    private boolean validMove(int i, int j) {
        return validLine(_board.getLine(i)) && validColumn(j) && validSquare(i, j);
    }
    
    public void printBoard() {
        _board.print();
    }

    public Board getBoard() {
        return _board;
    }

    public int[][] getIntBoard() {
        return _board.getIntBoard();
    }

    /*public void setBoard(Boad board) {
        _board = board;
    }*/

    /* public static void main(String[] args) {

        //  Board1
        //  Returns true
        
        int board1[][] =    {{7, 8, 0, 4, 0, 0, 1, 2, 0},
                            {6, 0, 0, 0, 7, 5, 0, 0, 9},
                            {0, 0, 0, 6, 0, 1, 0, 7, 8},
                            {0, 0, 7, 0, 4, 0, 2, 6, 0},
                            {0, 0, 1, 0, 5, 0, 9, 3, 0},
                            {9, 0, 4, 0, 6, 0, 0, 0, 5},
                            {0, 7, 0, 3, 0, 0, 0, 1, 2},
                            {1, 2, 0, 0, 0, 7, 4, 0, 0},
                            {0, 4, 9, 2, 0, 6, 0, 0, 7}};

        //  Board2
        //  Returns false
        
        int board2[][] =    {{5, 3, 4, 6, 7, 8, 9, 1, 2},
                            {6, 7, 2, 1, 9, 0, 3, 4, 8},
                            {1, 0, 0, 3, 4, 2, 5, 6, 0},
                            {8, 5, 9, 7, 6, 1, 0, 2, 0},
                            {4, 2, 6, 8, 5, 3, 7, 9, 1},
                            {7, 1, 3, 9, 2, 4, 8, 5, 6},
                            {9, 0, 1, 5, 3, 7, 2, 1, 4},
                            {2, 8, 7, 4, 1, 9, 6, 3, 5},
                            {3, 0, 0, 4, 8, 1, 1, 7, 9}};

        SudokuSolver s = new SudokuSolver(board1, 9);
        System.out.println("====Board1====");
        s.printBoard();
        System.out.println();
        System.out.println(s.solve());
        System.out.println();
        s.printBoard();

        s.setBoard(board2, 9);
        System.out.println("\n====Board2====\n");
        s.printBoard();
        System.out.println();
        System.out.println(s.solve());
        System.out.println();
        s.printBoard();
    } */
}