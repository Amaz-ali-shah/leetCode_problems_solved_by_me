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
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        
        // If root's value is less than low, the whole left subtree is invalid,
        // and the trimmed tree must be found in the right subtree.
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        
        // If root's value is greater than high, the whole right subtree is invalid,
        // and the trimmed tree must be found in the left subtree.
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        
        // Root is within range, trim its children.
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        
        return root;
    }
}
