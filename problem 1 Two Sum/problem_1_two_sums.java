class Solution {
    public int[] twoSum(int[] nums, int target) {
        // HashMap stores number -> its index
        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // If we've already seen the complement, return its index and current index
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            
            // Otherwise, store this number with its index for future lookups
            map.put(nums[i], i);
        }
        
        // According to the problem statement, we never reach here
        return new int[] {};
    }
}