class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;   // node just before the current group

        while (true) {
            // 1. Find the k-th node of the current group
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) break;   // fewer than k nodes left → stop

            ListNode groupNext = kth.next;

            // 2. Reverse the group [groupPrev.next .. kth]
            ListNode prev = groupNext;   // attach tail to the next group's head
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // 3. Reconnect
            ListNode oldGroupStart = groupPrev.next;
            groupPrev.next = kth;        // kth is now the group's new head
            groupPrev = oldGroupStart;   // old start is now the group's tail
        }

        return dummy.next;
    }

    // Returns the k-th node after `node`, or null if fewer than k nodes remain
    private ListNode getKth(ListNode node, int k) {
        while (node != null && k > 0) {
            node = node.next;
            k--;
        }
        return node;
    }
}
