package JavaVersion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import JavaVersion.Board.Board;
import JavaVersion.Board.EasyBoard;
/*import JavaVersion.Board.HardBoard;
import JavaVersion.Board.MediumBoard;*/
import JavaVersion.Player.SudokuPlayer;

/**
 * App
 */
public class App {

    public static void main(String[] args) throws IOException {
        Board easyBoard = new EasyBoard(9); //, mediumBoard = new MediumBoard(9), hardBoard = new HardBoard(9);

        /*System.out.println("\n======EASY=======\n");
        s.printBoard();
        System.out.println("\nSOLVING...\n");
        s.solve();
        System.out.println();
        s.printBoard();
        System.out.println();

        System.out.println("\n======MEDIUM=======\n");
        s.setBoard(mediumBoard);
        s.printBoard();
        System.out.println("\nSOLVING...\n");
        s.solve();
        System.out.println();
        s.printBoard();

        System.out.println("\n======HARD=======\n");
        s.setBoard(hardBoard);
        s.printBoard();
        System.out.println("\nSOLVING...\n");
        s.solve();
        System.out.println();
        s.printBoard();
        System.out.println();*/

        SudokuPlayer p = new SudokuPlayer(easyBoard);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int i=0, j=0, value = 0;

        do {
            p.printBoard();
            System.out.println();

            System.out.println("Escolha uma posição para alterar e o valor ou \"-1\" para sair - (i,j,valor)");

            // Reading data using readLine 
            i = Integer.parseInt(reader.readLine());
            if (i < 0) { continue; }
            j = Integer.parseInt(reader.readLine());
            if (j < 0) { continue; }
            value = Integer.parseInt(reader.readLine());
            if (value < 0) { continue; }

            p.makeMove(i, j, value);

        } while(i >= 0 && j >= 0 && value >= 0);

        p.autoSolve();

        p.printBoard();

        reader.close();

    }
    
}