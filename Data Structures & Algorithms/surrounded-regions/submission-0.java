class Solution {
    private int[][] dirs = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    private int rows, cols;

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        rows = board.length;
        cols = board[0].length;

        Queue<int[]> q = new LinkedList<>();

        // Step 1: Add all border 'O's to queue and mark as safe ('T')
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if ((r == 0 || r == rows - 1 || c == 0 || c == cols - 1)
                        && board[r][c] == 'O') {
                    q.offer(new int[]{r, c});
                    board[r][c] = 'T';
                }
            }
        }

        // Step 2: BFS to mark all connected 'O's as safe
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nc >= 0 && nr < rows && nc < cols
                        && board[nr][nc] == 'O') {
                    board[nr][nc] = 'T';
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        // Step 3: Flip surrounded 'O' → 'X', restore 'T' → 'O'
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == 'T') {
                    board[r][c] = 'O';
                }
            }
        }
    }
}