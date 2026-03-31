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

public class Codec {

    private static final String NULL_MARKER = "null";
    private static final String DELIMITER = ",";

    // Encodes a tree to a single string using BFS (Level Order Traversal).
    public String serialize(TreeNode root) {
        if (root == null) {
            return NULL_MARKER; // Represent an empty tree as "null"
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // Start with the root

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current == null) {
                sb.append(NULL_MARKER).append(DELIMITER); // Append marker for null nodes
            } else {
                sb.append(current.val).append(DELIMITER); // Append value for non-null nodes
                queue.offer(current.left);  // Enqueue children (even if null)
                queue.offer(current.right);
            }
        }

        // Remove trailing delimiter if exists
        if (sb.length() > 0) {
            sb.setLength(sb.length() - DELIMITER.length());
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals(NULL_MARKER)) {
            return null; // Handle empty tree case
        }

        String[] nodes = data.split(DELIMITER); // Split the string by delimiter
        // The first element is always the root
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1; // Start from the second element (index 1) in the array
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode parent = queue.poll();

            // Process left child
            if (i < nodes.length) { // Ensure we don't go out of bounds
                String leftVal = nodes[i++];
                if (!leftVal.equals(NULL_MARKER)) {
                    parent.left = new TreeNode(Integer.parseInt(leftVal));
                    queue.offer(parent.left);
                }
            }

            // Process right child
            if (i < nodes.length) { // Ensure we don't go out of bounds
                String rightVal = nodes[i++];
                if (!rightVal.equals(NULL_MARKER)) {
                    parent.right = new TreeNode(Integer.parseInt(rightVal));
                    queue.offer(parent.right);
                }
            }
        }

        return root;
    }
}
