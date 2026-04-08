class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for(char task : tasks){
            //filling the count array with the counts of each task
            count[task - 'A']++;
        }
        //sorts the most amount of tasks last
        Arrays.sort(count);
        // point the most frequent task (last element of the array after sorting)
        int maxf = count[25];

        int idle = (maxf - 1) * n;
        //fill idle slots with other tasks  
        for(int i = 24; i >= 0; i--){
            idle-=Math.min(maxf-1, count[i]);
        }
        //idle slots + the length of the tasks
        return Math.max(0, idle) + tasks.length;


    }
}
