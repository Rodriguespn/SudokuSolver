package JavaVersion;

import java.util.ArrayList;

/**
 * Board
 */
public class Board {

    private ArrayList<ArrayList<Integer>> _board;
    private int _size;

    public Board(int board[][], int size) {
        _size = size;
        _board = new ArrayList<ArrayList<Integer>>(_size);
        ArrayList<Integer> line;
        for (int i = 0; i < _size; i++) {
            line = new ArrayList<Integer>();
            for (int j = 0; j < _size; j++) {   
                line.add(board[i][j]);
            }
            _board.add(line);
        }
    }

    private boolean validCoord(int i, int j) {
        return i >= 0 && i < _size && j >= 0 && j < _size; 
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
    
    public boolean isWhiteSpace(int i, int j) /*throws InvalidCoordinate*/{
        if (validCoord(i, j)) {
            return getValue(i, j) == 0;
        }
        return false;
    }

    public int[] getNextWhiteSpace() {
        int pos[] = {-1, -1};
        for (int i = 0; i <_size; i++) {
            for (int j = 0; j < _size; j++) {
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
        for (int i = 0; i <_size; i++) {
            column.add(getValue(i, j));
        }
        return validLine(column);
    }

    private boolean validSquare(int i, int j) {
        int squareX = j / 3, squareY = i / 3;
        ArrayList<Integer> square = new ArrayList<Integer>();
        for (int y = squareY * 3; y < squareY*3 + 3; y++) {
            for (int x = squareX * 3; y < squareX*3 + 3; y++) {
                square.add(getValue(y, x));
            }
        }
        return validLine(square);
    }

    public boolean validMove(int i, int j) {
        return validLine(_board.get(i)) && validColumn(j) && validSquare(i, j);
    }

    public int getSize() {
        return _size;
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
}