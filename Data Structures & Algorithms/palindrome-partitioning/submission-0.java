class Solution {
  private List<List<String>> ans = new ArrayList<>();
  private List<String> part = new ArrayList<>();
    public List<List<String>> partition(String s) {
     dfs(0, s);
     return ans;     
    }

    public void dfs(int startIndex,  String s){
        if(startIndex == s.length()){
            ans.add(new ArrayList<>(part));
            return;
        }
        for(int i = startIndex; i < s.length(); i++){
             if(isPali(s, startIndex, i)){
            // add the following palindrome to the current partition
            part.add(s.substring(startIndex, i + 1));
            // "Explore": Recursively call DFS to find partitions for the rest of the string.
            // The new starting index is i + 1.
            dfs(i + 1, s);
        //all possibilities with this
        // specific palindrome choice have been explored), we "undo" that choice.
        // This allows the 'for' loop to continue and try a *different* palindrome
        // starting from the original 'startIndex' (a longer one, as 'i' increments).
            part.remove(part.size() - 1);
        }
        }
    }

    private boolean isPali(String s, int l, int r){
        while(l < r){
            if(s.charAt(l) != s.charAt(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    
}
