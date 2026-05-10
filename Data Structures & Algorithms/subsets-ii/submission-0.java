class Solution {
    Set<List<Integer>> ans = new HashSet<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>());
        return new ArrayList<>(ans);
    }

    public void backtrack(int[] nums, int i, List<Integer> subset){
        if(i == nums.length){
            ans.add(new ArrayList<> (subset));
            return;
        }

        subset.add(nums[i]);
        backtrack(nums, i +1, subset);
        subset.remove(subset.size() - 1);
        backtrack(nums, i + 1, subset);
        
    }
}
