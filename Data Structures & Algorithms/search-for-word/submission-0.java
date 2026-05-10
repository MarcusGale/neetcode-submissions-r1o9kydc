class Solution {
    private int ROWS, COLS;

    public boolean exist(char[][] board, String word) {
        ROWS = board.length;
        COLS = board[0].length;

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (dfs(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int i) {
        // base case: found full word
        if (i == word.length()) {
            return true;
        }

        // boundary + mismatch check
        if (r < 0 || c < 0 || r >= ROWS || c >= COLS ||
            board[r][c] != word.charAt(i)) {
            return false;
        }

        // temporarily mark visited
        char temp = board[r][c];
        board[r][c] = '#';

        // explore all 4 directions
        boolean found =
            dfs(board, word, r + 1, c, i + 1) ||
            dfs(board, word, r - 1, c, i + 1) ||
            dfs(board, word, r, c + 1, i + 1) ||
            dfs(board, word, r, c - 1, i + 1);

        // restore cell (backtrack)
        board[r][c] = temp;

        return found;
    }
}