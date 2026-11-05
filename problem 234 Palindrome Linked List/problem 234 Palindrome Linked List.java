class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // 1. Find the middle node (slow ends at the end of the first half)
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse the second half
        ListNode secondHalf = reverse(slow.next);

        // 3. Compare both halves
        ListNode p1 = head, p2 = secondHalf;
        boolean result = true;
        while (p2 != null) {
            if (p1.val != p2.val) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 4. Restore the list (good practice — keeps the input intact)
        slow.next = reverse(secondHalf);

        return result;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
