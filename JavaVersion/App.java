package JavaVersion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import JavaVersion.Board.Board;
import JavaVersion.Board.EasyBoard;
import JavaVersion.Board.HardBoard;
import JavaVersion.Board.MediumBoard;
import JavaVersion.Player.SudokuPlayer;

/**
 * App
 */
public class App {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Board board = chooseDifficultyLevel(reader);

        SudokuPlayer p = new SudokuPlayer(board);
        int i = 0, j = 0, value = 0;

        do {
            p.printBoard();
            System.out.println();

            // Reading data using readLine 
            System.out.print("Escolha uma posição para alterar e o valor ou \"-1\" para sair\ni = ");
            i = Integer.parseInt(reader.readLine()) -1;
            System.out.println();
            if (i < 0) { continue; }

            System.out.print("Escolha uma posição para alterar e o valor ou \"-1\" para sair\nj = ");
            j = Integer.parseInt(reader.readLine()) -1;
            System.out.println();
            if (j < 0) { continue; }

            System.out.print("Escolha uma posição para alterar e o valor ou \"-1\" para sair\nvalor = ");
            value = Integer.parseInt(reader.readLine());
            System.out.println();
            if (value < 0) { continue; }

            p.makeMove(i, j, value);

        } while(i >= 0 && j >= 0 && value >= 0);

        p.autoSolve();

        p.printBoard();

        reader.close();

    }
    

    public static Board chooseDifficultyLevel(BufferedReader reader) throws IOException{
        Board board = null; 
        System.out.print("Escolha um nível de dificuldade (1- Fácil; 2- Médio; 3- Difícil)\nNível = ");
        int dl = Integer.parseInt(reader.readLine());
        System.out.println();
        switch (dl) {
            case 1:
                board = new EasyBoard(9);
                break;
                
            case 2:
                board = new MediumBoard(9);
                break;

            case 3:
                board = new HardBoard(9);
                break;
        
            default:
                System.out.println("Erro App: Opção Inválida!");
                reader.close();
                System.exit(1);
                break;
        }
        return board;
    }
}