class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array for optimization
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2;
        
        int low = 0, high = m;
        
        while (low <= high) {
            int cut1 = low + (high - low) / 2;   // partition index in nums1
            int cut2 = totalLeft - cut1;         // partition index in nums2
            
            // Left half values (use -INF / +INF for boundaries)
            int left1  = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2  = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            
            // Right half values
            int right1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];
            
            // Check valid partition
            if (left1 <= right2 && left2 <= right1) {
                // If total length is odd
                if ((m + n) % 2 == 1) {
                    return Math.max(left1, left2);
                }
                // If total length is even
                return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
            } else if (left1 > right2) {
                // cut1 is too far right, move left
                high = cut1 - 1;
            } else {
                // cut1 is too far left, move right
                low = cut1 + 1;
            }
        }
        
        throw new IllegalArgumentException("Input arrays are not sorted");
    }
}
