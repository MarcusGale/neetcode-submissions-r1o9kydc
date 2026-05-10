class Solution {
    private static final int[][] directions = {
        // moving one row down
        {1, 0}, 
        // moving one row up
        {-1, 0}, 
        // moving one column right
        {0, 1}, 
        // moving one column left
        {0, -1}};
    public int numIslands(char[][] grid) {

        if(grid == null || grid.length == 0) return 0;

        int ROWS = grid.length;
        int COLS = grid[0].length;
        // island counter
        int ans = 0;
        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                //we've found an island
                if(grid[r][c] == '1'){
                    //find all connected land cells
                    // belonging to this island and "sink" them by marking them as '0'.
                    // This ensures we don't count parts of the same island multiple times.
                    dfs(grid, r, c);
                    ans++;
                }
            }
        }
        return ans;
    }

    public void dfs(char[][] grid, int r, int c){
        // Base Case: This is the stopping condition for the recursion.
        if(r < 0 || c < 0 || r >= grid.length 
        || c >= grid[0].length 
        || grid[r][c] == '0') return;
     // "Sink" the current cell.
    // By changing '1' to '0', we mark it as visited for this DFS traversal.
    grid[r][c] = '0';
    // Explore all four neighboring cells recursively.
    for(int[] dir : directions){
        dfs(grid, r + dir[0], c + dir[1]);
    }
    }
}
