class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;

        // Recursively prune left and right subtrees
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        // If current node is 0 and has no child containing 1, remove it
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }

        return root;
    }
}
