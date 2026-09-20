class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int jumps = 0;
        int curEnd = 0;      // right boundary of the current jump's reach
        int farthest = 0;    // farthest index reachable with one more jump

        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == curEnd) {           // exhausted current level
                jumps++;
                curEnd = farthest;
                if (curEnd >= n - 1) break;  // can reach the end
            }
        }
        return jumps;
    }
}
