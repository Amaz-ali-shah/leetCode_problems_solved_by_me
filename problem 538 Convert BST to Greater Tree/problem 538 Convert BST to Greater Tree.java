class Solution {
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;

        convertBST(root.right);   // visit larger values first
        sum += root.val;
        root.val = sum;
        convertBST(root.left);    // then smaller values

        return root;
    }
}
