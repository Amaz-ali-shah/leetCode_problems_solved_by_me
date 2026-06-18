class Solution {
    public int maxSubArray(int[] nums) {
        // Initialize with the first element
        int maxEndingHere = nums[0];
        int maxSoFar = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // Either extend the previous subarray, or start fresh from nums[i]
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            
            // Update the global maximum
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
}