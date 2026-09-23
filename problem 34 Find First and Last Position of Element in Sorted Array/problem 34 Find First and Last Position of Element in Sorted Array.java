class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return result;
        
        result[0] = findBound(nums, target, true);
        result[1] = findBound(nums, target, false);
        return result;
    }
    
    private int findBound(int[] nums, int target, boolean findFirst) {
        int left = 0, right = nums.length - 1;
        int index = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                index = mid;
                if (findFirst) {
                    right = mid - 1;   // keep searching left
                } else {
                    left = mid + 1;    // keep searching right
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index;
    }
}
