import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    // Method to check if placing a queen at board[row][col] is safe
    public boolean isSafe(int row, int col, char[][] board) {
        // Horizontal check
        for (int j = 0; j < board.length; j++) {
            if (board[row][j] == 'Q') {
                return false;
            }
        }
        // Vertical check
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        // Upper-left diagonal check
        int r = row;
        for (int c = col; c >= 0 && r >= 0; c--, r--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }
        // Upper-right diagonal check
        r = row;
        for (int c = col; c < board.length && r >= 0; c++, r--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }
        // Lower-left diagonal check
        r = row;
        for (int c = col; c >= 0 && r < board.length; c--, r++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }
        // Lower-right diagonal check
        r = row;
        for (int c = col; c < board.length && r < board.length; c++, r++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        return true; // If no conflicts, it's safe to place the queen
    }

    // Method to save the board configuration
    public void saveBoard(char[][] board, List<List<String>> allBoards) {
        List<String> newBoard = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            String row = "";
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'Q') {
                    row += 'Q';
                } else {
                    row += '.';
                }
            }
            newBoard.add(row); // Add the complete row after the inner loop
        }
        allBoards.add(newBoard); // Add the full board to allBoards
    }

    // Helper method to recursively place queens
    public void helper(char[][] board, List<List<String>> allBoards, int col) {
        if (col == board.length) {
            saveBoard(board, allBoards);
            return;
        }
        for (int row = 0; row < board.length; row++) {
            if (isSafe(row, col, board)) {
                board[row][col] = 'Q'; // Place the queen
                helper(board, allBoards, col + 1); // Recur for the next column
                board[row][col] = '.'; // Backtrack
            }
        }
    }

    // Main method to solve the N-Queens problem
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> allBoards = new ArrayList<>();
        char[][] board = new char[n][n]; // Initialize the board
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.'; // Set all cells to '.'
            }
        }
        helper(board, allBoards, 0); // Start the recursive process
        return allBoards; // Return all the possible solutions
    }

    // Main method to run the program and take dynamic input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Dynamic input for board size (n)
        System.out.print("Enter the value of n (size of the board): ");
        int n = scanner.nextInt();

        // Create Solution object and solve the problem
        Solution solution = new Solution();
        List<List<String>> results = solution.solveNQueens(n);

        // Print the solutions
        System.out.println("Total number of solutions: " + results.size());
        for (List<String> board : results) {
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println(); // Add an empty line between solutions
        }

        scanner.close();
    }
}
