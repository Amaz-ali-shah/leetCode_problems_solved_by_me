class Solution {
    public boolean canJump(int[] nums) {
        int farthest = 0;

        for (int i = 0; i < nums.length; i++) {
            // If current position is unreachable, we're stuck
            if (i > farthest) return false;

            farthest = Math.max(farthest, i + nums[i]);

            // Early exit: we can already reach the last index
            if (farthest >= nums.length - 1) return true;
        }

        return true;
    }
}
