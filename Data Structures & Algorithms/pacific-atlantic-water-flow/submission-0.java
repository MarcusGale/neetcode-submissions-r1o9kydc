class Solution {
    // Defines the four possible movement directions (up, down, left, right)
    private int[][] dirs = { {1, 0}, {-1, 0},
                             {0, 1}, {0, -1}};    

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        // Two boolean matrices to track which cells can reach the Pacific 
        // and which can reach the Atlantic. This is the core data structure 
        // for storing the results of the two separate DFS traversals.
        boolean[][] pacificReachable = new boolean[rows][cols];
        boolean[][] atlanticReachable = new boolean[rows][cols];
        
        // The overall strategy is **Reverse DFS**: Start at the oceans and work 
        // inward, using the reverse flow rule (water can flow to a *higher* neighbor).

        // Iterate over the first (column 0) and last column (cols - 1)
        for(int r = 0; r < rows; r++){
            // Pacific: start DFS from the left edge (column 0)
            dfs(r, 0, pacificReachable, heights);
            
            // Atlantic: start DFS from the right edge (column N - 1)
            dfs(r, cols - 1, atlanticReachable, heights);
        } 
        
        // Iterate over the first (row 0) and last row (rows - 1)
        for(int c = 0; c < cols; c++){
            // Pacific: start DFS from the top edge (row 0)
            dfs(0, c, pacificReachable, heights);
            
            // Atlantic: start DFS from the bottom edge (rows - 1)
            // Note: Boundary cells are processed twice (once by row iteration, once by col iteration), 
            // but the 'if (!ocean[...])' check in dfs prevents redundant work.
            dfs(rows - 1, c, atlanticReachable, heights);
        }


        // Final step: Compile the results by finding the intersection of the two searches.
        List<List<Integer>> res = new ArrayList<>();
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                // If a cell is reachable by BOTH the Pacific and the Atlantic, it's a solution.
                if(pacificReachable[r][c] && atlanticReachable[r][c])  {
                    res.add(Arrays.asList(r, c));
                }  
            
            }
        }
        return res;
    }

    // Depth-First Search (DFS) implementation for the reverse flow
    // r, c: current cell coordinates
    // ocean: the boolean matrix (pacificReachable or atlanticReachable) to update
    // heights: the grid data
    private void dfs(int r, int c, boolean[][] ocean, int[][] heights){
        int rows = heights.length;
        int cols = heights[0].length;

        // Mark the current cell as reachable from this ocean
        ocean[r][c] = true;
        
        for(int [] d : dirs){
            int nextRow = r + d[0]; 
            int nextColumn = c + d[1];
            
            // 1. Check if the neighbor is within the grid boundaries
            if(nextRow >= 0 && nextRow < rows 
            &&
            nextColumn >= 0 && nextColumn < cols // CORRECTED COLUMN CHECK
            &&
            // 2. Check if the neighbor has not already been visited (prevents infinite recursion)
            !ocean[nextRow][nextColumn] 
            &&
            // 3. REVERSE FLOW RULE: Water can flow *up* from (r, c) to (nextRow, nextColumn) 
            // only if the destination is higher or equal in height.
            heights[nextRow][nextColumn] >= heights[r][c]) {
                
                dfs(nextRow, nextColumn, ocean, heights);
            }
        }
    }
}