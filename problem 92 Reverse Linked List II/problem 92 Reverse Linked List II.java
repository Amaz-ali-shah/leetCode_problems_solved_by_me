class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        // 1. Move prev to the node right before position `left`
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // 2. Head-insertion: pull each next node to the front of the sub-list
        ListNode curr = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;      // node we want to move
            curr.next = next.next;          // detach it
            next.next = prev.next;          // point it to current front
            prev.next = next;               // make it the new front
        }

        return dummy.next;
    }
}
