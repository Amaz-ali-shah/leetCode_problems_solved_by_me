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
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }
    
    private boolean validate(TreeNode node, Integer lower, Integer upper) {
        // Empty tree is a valid BST
        if (node == null) {
            return true;
        }
        
        // Check if current node's value violates the constraints
        if (lower != null && node.val <= lower) {
            return false;
        }
        if (upper != null && node.val >= upper) {
            return false;
        }
        
        // Recursively validate left and right subtrees
        // Left subtree: all values must be < node.val (upper bound = node.val)
        // Right subtree: all values must be > node.val (lower bound = node.val)
        return validate(node.left, lower, node.val) && 
               validate(node.right, node.val, upper);
    }
}
