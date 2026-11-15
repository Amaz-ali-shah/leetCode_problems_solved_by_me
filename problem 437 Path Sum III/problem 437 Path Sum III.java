class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        return pathsFrom(root, (long) targetSum)
             + pathSum(root.left, targetSum)
             + pathSum(root.right, targetSum);
    }

    // counts paths that START at `node`
    private int pathsFrom(TreeNode node, long remaining) {
        if (node == null) return 0;
        int count = (node.val == remaining) ? 1 : 0;
        count += pathsFrom(node.left,  remaining - node.val);
        count += pathsFrom(node.right, remaining - node.val);
        return count;
    }
}
