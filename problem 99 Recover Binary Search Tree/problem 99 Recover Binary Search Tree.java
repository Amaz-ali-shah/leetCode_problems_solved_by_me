class Solution {
    public void recoverTree(TreeNode root) {
        if (root == null) return;

        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        // Inorder traversal
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();

            // Find swapped nodes
            if (prev != null && prev.val > cur.val) {
                if (first == null) {
                    first = prev;
                }
                second = cur;
            }

            prev = cur;
            cur = cur.right;
        }

        // Swap values back
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
