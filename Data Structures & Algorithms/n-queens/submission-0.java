class Solution {

    public List<List<String>> solveNQueens(int n) {
    // store all the valid board configurations (solutions) found.
    //Each inner List<String> represents one complete solution, 
    // where each String is a row of the board.
        List<List<String>> ans = new ArrayList<>();
        char[][] board = new char[n][n];
    //intilization loop
        for(int i = 0;  i < n; i++){
        for(int j = 0; j < n; j++){
        // Fills the board with '.' representing empty squares
            board[i][j] = '.';
             }
         }
        backtrack(0, //the starting row
            board, // the chessboard
            ans);

    return ans;
            }

    // recursive helper function that tries to place queens row by row.
    private void backtrack(int r, char[][] board, List<List<String>> ans){            
        // base case
        if(r == board.length){
        // If r (the current row we're trying to place a queen in) equals 
        // board.length (which is n), it means we've successfully 
        // placed a queen in every row from 0 to n-1. 
        List<String> copy = new ArrayList<>();
        for(char[] row : board){
        // Each char[] row is converted into a String (e.g., ".Q.."). 
        // This copy is then added to the global ans list. It's crucial to add a copy because the board will be modified during backtracking.
        copy.add(new String(row));
            }
        ans.add(copy);
        return;
        }

        for(int c = 0; c < board.length; c++){
            //For each column, it tries to place a queen.
            // Before placing a queen, the isSafe helper function is called. 
            // This checks if placing a queen at (r, c) would be safe (i.e., not 
            // attacked by any previously placed queens in rows 0 to r-1).
            if(isSafe(r, c, board)){
                board[r][c] = 'Q';
                backtrack(r + 1, board, ans);
                board[r][c] = '.';
                }
            }
        }

    //The Safety Check
    private boolean isSafe(int r, int c, char[][] board){
        //check column
        for(int i = r - 1; i>= 0; i--){
            if(board[i][c] == 'Q') return false;
        }
        //check left-up diagonal
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        // check right-up diagonal
        for(int i = r - 1, j = c + 1; i >= 0
        && j < board.length; i--, j++){
            if(board[i][j] == 'Q') return false;
        }
        return true;
    }
}


