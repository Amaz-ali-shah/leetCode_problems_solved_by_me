class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first  = prev.next;
            ListNode second = first.next;

            // Perform the swap
            first.next  = second.next;   // 1 -> 3   (skip 2)
            second.next = first;         // 2 -> 1
            prev.next   = second;        // prev -> 2

            // Move prev two nodes forward
            prev = first;
        }

        return dummy.next;
    }
}
