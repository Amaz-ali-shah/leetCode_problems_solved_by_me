class Solution {
    private int k;
    private int result;
    
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        this.result = -1;
        inorder(root);
        return result;
    }
    
    private void inorder(TreeNode node) {
        if (node == null || result != -1) {
            return;
        }
        
        inorder(node.left);
        
        k--;
        if (k == 0) {
            result = node.val;
            return;
        }
        
        inorder(node.right);
    }
}
