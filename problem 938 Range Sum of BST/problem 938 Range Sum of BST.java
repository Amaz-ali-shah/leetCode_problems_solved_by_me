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
    public int rangeSumBST(TreeNode root, int low, int high) {
        // Base case
        if (root == null) return 0;
        
        // If current value is out of range, prune the unnecessary subtree
        if (root.val < low) {
            // Entire left subtree is too small, go right
            return rangeSumBST(root.right, low, high);
        }
        if (root.val > high) {
            // Entire right subtree is too large, go left
            return rangeSumBST(root.left, low, high);
        }
        
        // Current node is within range: add it and explore both sides
        return root.val 
                + rangeSumBST(root.left, low, high) 
                + rangeSumBST(root.right, low, high);
    }
}
