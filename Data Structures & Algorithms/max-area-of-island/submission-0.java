class Solution {
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0 , 1}, {0, -1}};
    public int maxAreaOfIsland(int[][] grid) {
        

        int ROWS = grid.length;
        int COLS = grid[0].length;

        int maxArea = 0;
        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
            if(grid[r][c] == 1){
                maxArea = Math.max(maxArea, dfs(grid, r, c));
            }
            }
        }
        return maxArea;
    }

    public int dfs(int[][] grid, int r, int c){
        if(r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0)
        return 0;

        // sinking the islands surrounding the 1
        grid[r][c] = 0;
        int curMax = 1;
        for(int[] dir : directions){
            curMax += dfs(grid, r + dir[0], c + dir[1]);
        }

        return curMax;

    }
}
