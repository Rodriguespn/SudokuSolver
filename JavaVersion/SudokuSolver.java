package JavaVersion;
/**
 * SudokuSolver
 */
public class SudokuSolver {

    private Board _board;

    public SudokuSolver(Board board) {
        _board = board;
    }

    public boolean solve() {
        int pos[] = _board.getNextWhiteSpace();
        if (pos[0] == -1) {
            return true;
        }
        int i = pos[0], j = pos[1];
        for (int value = 1; value <= _board.getSize(); value++) {
            _board.setValue(i, j, value);
            if (_board.validMove(i, j)) {
                if (solve()) {
                    return true;
                }
            }
            _board.setValue(i, j, 0);
        }
        return false;
    }
    
    public void printBoard() {
        _board.print();
    }

    public void setBoard(Board board) {
        _board = board;
    }

    public static void main(String[] args) {

        /*
            Board1
            Returns true
        */
        int board1[][] =    {{7, 8, 0, 4, 0, 0, 1, 2, 0},
                            {6, 0, 0, 0, 7, 5, 0, 0, 9},
                            {0, 0, 0, 6, 0, 1, 0, 7, 8},
                            {0, 0, 7, 0, 4, 0, 2, 6, 0},
                            {0, 0, 1, 0, 5, 0, 9, 3, 0},
                            {9, 0, 4, 0, 6, 0, 0, 0, 5},
                            {0, 7, 0, 3, 0, 0, 0, 1, 2},
                            {1, 2, 0, 0, 0, 7, 4, 0, 0},
                            {0, 4, 9, 2, 0, 6, 0, 0, 7}};

        /*
            Board2
            Returns false
        */
        int board2[][] =    {{5, 3, 4, 6, 7, 8, 9, 1, 2},
                            {6, 7, 2, 1, 9, 0, 3, 4, 8},
                            {1, 0, 0, 3, 4, 2, 5, 6, 0},
                            {8, 5, 9, 7, 6, 1, 0, 2, 0},
                            {4, 2, 6, 8, 5, 3, 7, 9, 1},
                            {7, 1, 3, 9, 2, 4, 8, 5, 6},
                            {9, 0, 1, 5, 3, 7, 2, 1, 4},
                            {2, 8, 7, 4, 1, 9, 6, 3, 5},
                            {3, 0, 0, 4, 8, 1, 1, 7, 9}};

        SudokuSolver s = new SudokuSolver(new Board(board1, 9));
        System.out.println("====Board1====");
        s.printBoard();
        System.out.println();
        System.out.println(s.solve());
        System.out.println();
        s.printBoard();

        s.setBoard(new Board(board2, 9));
        System.out.println("\n====Board2====\n");
        s.printBoard();
        System.out.println();
        System.out.println(s.solve());
        System.out.println();
        s.printBoard();
    }
}