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
    private int postIdx;                          // pointer moving backwards through postorder
    private Map<Integer, Integer> inorderMap;     // value -> index in inorder

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIdx = postorder.length - 1;
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return build(postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int inLeft, int inRight) {
        if (inLeft > inRight) return null;

        // Root is the current last unprocessed element of postorder
        int rootVal = postorder[postIdx--];
        TreeNode root = new TreeNode(rootVal);

        int mid = inorderMap.get(rootVal);

        // IMPORTANT: build RIGHT first — postorder is being read right-to-left
        root.right = build(postorder, mid + 1, inRight);
        root.left  = build(postorder, inLeft, mid - 1);

        return root;
    }
}
