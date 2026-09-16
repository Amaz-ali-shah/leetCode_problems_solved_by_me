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
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    // Returns max gain from a downward path starting at this node
    private int maxGain(TreeNode node) {
        if (node == null) return 0;

        // Max gain from left/right children; ignore if negative
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // Path passing through this node (both branches)
        int currentPathSum = node.val + leftGain + rightGain;

        // Update global max
        maxSum = Math.max(maxSum, currentPathSum);

        // Return max gain going down one branch
        return node.val + Math.max(leftGain, rightGain);
    }
}
