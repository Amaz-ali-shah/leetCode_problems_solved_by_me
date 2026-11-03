class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // 1. Find the middle (slow ends at middle)
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse the second half
        ListNode prev = null;
        ListNode curr = slow.next;
        slow.next = null;               // cut the list
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // prev is now the head of the reversed second half

        // 3. Merge two halves alternately
        ListNode first = head, second = prev;
        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;
            first.next = second;
            second.next = tmp1;
            first = tmp1;
            second = tmp2;
        }
    }
}
