import java.util.Arrays;

public class NQueens {
    public static void main(String[] args) {
        int N = 8;
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], '-');
        }
        solveNQueens(board);
    }

    public static void solveNQueens(char[][] chessBoard) {
        // Initiate the solution process from the first row.
        solveNQueens(chessBoard, 0);
    }

    private static void solveNQueens(char[][] chessBoard, int currentRow) {
        // If all rows have been processed, a solution is found.
        if (currentRow == chessBoard.length) {
            printSolution(chessBoard);
            return;
        }
        // Process each column in the current row.
        for (int currentColumn = 0; currentColumn < chessBoard.length; currentColumn++) {
            // If the current position is safe, place a queen and proceed to the next row.
            if (isPositionSafe(chessBoard, currentRow, currentColumn)) {
                chessBoard[currentRow][currentColumn] = 'Q';
                // Explore potential solutions in the next row.
                solveNQueens(chessBoard, currentRow + 1);
                // If no solution is found, remove the queen and explore other positions.
                chessBoard[currentRow][currentColumn] = '-';
            }
        }
    }

    private static boolean isPositionSafe(char[][] chessBoard, int row, int column) {
        // Check for queens in the same column.
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][column] == 'Q') {
                return false;
            }
        }
        // Check for queens in the left diagonal.
        for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }
        // Check for queens in the right diagonal.
        for (int i = row, j = column; i >= 0 && j < chessBoard.length; i--, j++) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }
        // If no queens are found in the same column or diagonals, the position is safe.
        return true;
    }

    private static void printSolution(char[][] chessBoard) {
        // Print each row of the board.
        for (char[] row : chessBoard) {
            // Remove commas for better readability.
            System.out.println(Arrays.toString(row).replaceAll(",", ""));
        }
        // Separate each solution with a newline.
        System.out.println();
    }

}
