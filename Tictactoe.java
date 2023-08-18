package tictactoe;
import java.util.Random;
import java.util.Scanner;

public class Tictactoe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static char aiPlayer = 'O';

    public static void main(String[] args) {
        initializeBoard();
        printNumberedBoard();
        playGame();
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printNumberedBoard() {
        int num = 1;
        System.out.println("Numbered Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(num + " ");
                num++;
            }
            System.out.println();
        }
    }

    private static void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void playGame() {
        boolean gameWon = false;
        int moves = 0;

        while (!gameWon && moves < 9) {
            if (currentPlayer == 'X') {
                System.out.println("Player " + currentPlayer + ", enter box number (1-9):");
                int boxNumber = getUserInput();
                int[] rowCol = getRowColFromBoxNumber(boxNumber);

                if (isValidMove(rowCol[0], rowCol[1])) {
                    board[rowCol[0]][rowCol[1]] = currentPlayer;
                    printBoard();
                    gameWon = checkWin(rowCol[0], rowCol[1]);
                    currentPlayer = aiPlayer;
                    moves++;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("AI's turn:");
                int[] aiMove = getAIMove();
                board[aiMove[0]][aiMove[1]] = aiPlayer;
                printBoard();
                gameWon = checkWin(aiMove[0], aiMove[1]);
                currentPlayer = 'X';
                moves++;
            }
        }

        if (gameWon) {
            if (currentPlayer == aiPlayer) {
                System.out.println("AI wins!");
            } else {
                System.out.println("Player X wins!");
            }
        } else {
            System.out.println("It's a draw!");
        }
    }

    private static int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static int[] getRowColFromBoxNumber(int boxNumber) {
        int row = (boxNumber - 1) / 3;
        int col = (boxNumber - 1) % 3;
        return new int[]{row, col};
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    private static boolean checkWin(int row, int col) {
      
        if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
            return true;
        }
       
        if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
            return true;
        }
       
        if ((row == col && board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (row + col == 2 && board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }

    private static int[] getAIMove() {
        Random random = new Random();

        while (true) {
            int row = random.nextInt(3);
            int col = random.nextInt(3);
            if (isValidMove(row, col)) {
                return new int[]{row, col};
            }
        }
    }
}
