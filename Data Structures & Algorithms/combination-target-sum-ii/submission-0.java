class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, cur, 0);
        return ans;
    }

    public void backtrack(int[] candidates, int target, List<Integer> cur, int start){
        if(target == 0){
            ans.add(new ArrayList(cur));
            return;
        }
        if(target < 0 || start >= candidates.length){
            return;
        }

        for(int i = start; i < candidates.length; i++){
            if(i >start && candidates[i] == candidates[i-1]) continue;

            cur.add(candidates[i]);
            backtrack(candidates, target - candidates[i], cur, i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}
