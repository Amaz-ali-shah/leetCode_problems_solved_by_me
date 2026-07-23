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
import java.util.*;

class Solution {
    private int preIndex = 0;
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderIndexMap = new HashMap<>();
        
        // Store index of each value in inorder traversal
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        
        return construct(preorder, 0, inorder.length - 1);
    }

    private TreeNode construct(int[] preorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }
        
        // First element in preorder is the root
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);
        
        // Find root position in inorder
        int inIndex = inorderIndexMap.get(rootVal);
        
        // Build left and right subtrees
        root.left = construct(preorder, inStart, inIndex - 1);
        root.right = construct(preorder, inIndex + 1, inEnd);
        
        return root;
    }
}
