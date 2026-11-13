class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        while (cur != null) {
            if (p.val < cur.val && q.val < cur.val) {
                cur = cur.left;          // both go left
            } else if (p.val > cur.val && q.val > cur.val) {
                cur = cur.right;         // both go right
            } else {
                return cur;              // split point = LCA
            }
        }
        return null; // unreachable for valid input
    }
}
