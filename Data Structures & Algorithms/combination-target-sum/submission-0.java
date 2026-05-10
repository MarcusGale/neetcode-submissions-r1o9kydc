class Solution {
     List<List<Integer>> ans;
    public List<List<Integer>> combinationSum(int[] nums, int target) {
    ans = new ArrayList<List<Integer>>();
    //current combination 
    List<Integer> cur = new ArrayList();
    //the recursive helper function backtrack
    backtrack(
        //the number of candidate numbers
        nums, 
        //the remaining target sum
        target, 
        // the current combination
        cur, 
        //starting index in nums
        0);
    return 
    ans;
    }

    public void backtrack(int [] nums, int target, List<Integer> cur, int i){
            // Base Case: if target become 0, it means the combinaton adds to 
            // target
            if(target == 0){
                //add a COPY of the combination to our list of lists
                ans.add(new ArrayList(cur));
                return;
            }
            //if the target becomes negative then the current combination has exceed the original target sum
            if(target < 0 || 
            // if the index has gone outside the length of nums
            i >= nums.length){
                //recursion stops
                return;
            }
            //he # at the current index i from nums is added to the cur combination 
            cur.add(nums[i]);
            backtrack(nums, target - nums[i], cur, i);
            //the essence of backtracking
            cur.remove(cur.size() - 1);
            backtrack(nums, target, cur, i + 1);
    }
}
