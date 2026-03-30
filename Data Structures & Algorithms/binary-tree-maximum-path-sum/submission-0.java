/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
            pathSumDFS(root);
            return res;
        }

    public int pathSumDFS(TreeNode node){
        if(node==null) return 0;
        //max path from the left child, making sure it's non negative
        int leftPathSum = Math.max(0, pathSumDFS(node.left));
        //max path from the right child, making sure it's non negative
        int rightPathSum = Math.max(0, pathSumDFS(node.right));
         //current max path compared with new max path
       res = Math.max(res, node.val + leftPathSum + rightPathSum);

       return node.val + Math.max(leftPathSum, rightPathSum);
    }
}
