class Solution {
    public Node connect(Node root) {
        if (root == null) return null;

        Node leftmost = root;  // leftmost node of current level

        while (leftmost.left != null) {  // while there's a next level
            Node head = leftmost;
            while (head != null) {
                // 1) connect left child → right child
                head.left.next = head.right;

                // 2) connect right child → next node's left child
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;  // go down to next level
        }
        return root;
    }
                      }
