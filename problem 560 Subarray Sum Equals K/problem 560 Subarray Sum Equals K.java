import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        // Map: prefix sum -> frequency of that sum seen so far
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // Empty subarray sum = 0 occurs once
        prefixSumCount.put(0, 1);
        
        int currentSum = 0;
        int count = 0;
        
        for (int num : nums) {
            currentSum += num;                 // running prefix sum
            
            // If (currentSum - k) was seen earlier, those subarrays end here
            count += prefixSumCount.getOrDefault(currentSum - k, 0);
            
            // Record current prefix sum for future subarrays
            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
}
