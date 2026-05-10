class Solution {
    public int orangesRotting(int[][] grid) {

        int fresh = 0;
        int time = 0;

        Queue<int[]> q = new ArrayDeque<>();


        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                if(grid[r][c]==1){
                    fresh++;
                }
                if(grid[r][c] == 2){
                    q.offer(new int[] {r, c});
                }
            }
        }

        int[][] dirs = { {0,1}, {0,-1}, {1,0}, {-1,0} };
        while(fresh > 0 && !q.isEmpty()){
            int length = q.size();
            for(int i = 0; i < length; i++){
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];

                for(int[] dir : dirs){
                    int row = r + dir[0];
                    int col = c + dir[1];
                    // the neighbor is in bounds and is a fresh fruit
                    if(row >= 0 && row < grid.length && 
                    col>=0 && col < grid[0].length 
                    && grid[row][col] == 1){
                        //turn it into a rotten orange
                        grid[row][col] = 2;
                        q.offer(new int[] {row,col});
                        fresh--;
                    }
                }
            }
            time++;
        }
        return fresh == 0 ? time : -1; 
    }
}
