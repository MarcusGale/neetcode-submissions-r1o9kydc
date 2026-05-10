class Solution {
    public List<String> generateParenthesis(int n) {
        List<List<String>> ans = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            ans.add(new ArrayList<>());
        }

        ans.get(0).add("");

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                for (String left : ans.get(j)) {
                    for (String right : ans.get(i - 1 - j)) {
                        ans.get(i).add("(" + left + ")" + right);
                    }
                }
            }
        }

        return ans.get(n);
    }
}