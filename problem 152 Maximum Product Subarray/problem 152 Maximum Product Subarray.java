class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int max = nums[0];
        int min = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            
            // Store max before updating
            int tempMax = max;
            
            // Calculate new max and min
            max = Math.max(current, Math.max(tempMax * current, min * current));
            min = Math.min(current, Math.min(tempMax * current, min * current));
            
            // Update result
            result = Math.max(result, max);
        }
        
        return result;
    }
}
