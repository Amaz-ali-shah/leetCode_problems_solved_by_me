class Solution {
    public int findDuplicate(int[] nums) {
        // Floyd's Tortoise and Hare (cycle detection)
        int slow = nums[0];
        int fast = nums[0];

        // Find the meeting point inside the cycle
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Find the entrance to the cycle, which is the duplicate number
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
