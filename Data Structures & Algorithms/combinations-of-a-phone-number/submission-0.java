class Solution {
    
    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) return new ArrayList<>();

        List<String> ans = new ArrayList<>();
        ans.add("");
        String[] digitToChar = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        for(char digit : digits.toCharArray()){
        List<String> temp = new ArrayList<>();
        for(String curStr : ans){
        for(char c : digitToChar[digit- '0'].toCharArray()){
            temp.add(curStr + c);
        }
        }
        ans = temp;
        }
        return ans;
    }
}
