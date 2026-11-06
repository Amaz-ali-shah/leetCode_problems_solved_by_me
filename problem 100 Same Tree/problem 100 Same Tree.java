class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Both null → same
        if (p == null && q == null) return true;
        // One null, or values differ → not same
        if (p == null || q == null || p.val != q.val) return false;
        // Check subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
