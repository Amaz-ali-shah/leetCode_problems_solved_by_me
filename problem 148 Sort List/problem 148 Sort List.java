/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        // Base case: empty list or single node is already sorted
        if (head == null || head.next == null) {
            return head;
        }

        // 1. Calculate the total length of the linked list
        int len = 0;
        for (ListNode p = head; p != null; p = p.next) {
            len++;
        }

        // Dummy node serves as an anchor for the merged list in each pass
        ListNode dummy = new ListNode(0, head);

        // 2. Iteratively merge sublists of size 'step', doubling 'step' each time
        for (int step = 1; step < len; step <<= 1) {
            ListNode cur = dummy.next;   // Current node to process
            ListNode tail = dummy;       // Tail of the merged result

            while (cur != null) {
                // Cut the first sublist of length 'step'
                ListNode left = cur;
                ListNode right = cut(left, step);

                // Cut the second sublist of length 'step'
                cur = cut(right, step);

                // Merge the two sorted sublists and attach to 'tail'
                tail.next = merge(left, right);

                // Move 'tail' to the end of the merged list
                while (tail.next != null) {
                    tail = tail.next;
                }
            }
        }
        return dummy.next;
    }

    /** Cuts the list after 'n' nodes and returns the head of the remaining list. */
    private ListNode cut(ListNode head, int n) {
        if (head == null) return null;
        for (int i = 1; i < n && head.next != null; i++) {
            head = head.next;
        }
        ListNode next = head.next;
        head.next = null; // Sever the link
        return next;
    }

    /** Merges two sorted linked lists and returns the merged head. */
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}
