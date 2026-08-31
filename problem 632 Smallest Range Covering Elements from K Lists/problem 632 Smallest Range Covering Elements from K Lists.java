import java.util.*;

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        
        // Min-heap: stores [value, listIndex, positionInList]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        int max = Integer.MIN_VALUE;
        
        // Initialize with the first element from each list
        for (int i = 0; i < k; i++) {
            int val = nums.get(i).get(0);
            pq.offer(new int[]{val, i, 0});
            max = Math.max(max, val);
        }
        
        // Initial best range
        int[] best = new int[]{pq.peek()[0], max};
        int bestRange = max - best[0];
        
        while (true) {
            int[] cur = pq.poll();
            int val = cur[0];
            int listIdx = cur[1];
            int pos = cur[2];
            
            // Check current range [val, max]
            int currentRange = max - val;
            if (currentRange < bestRange || 
                (currentRange == bestRange && val < best[0])) {
                bestRange = currentRange;
                best[0] = val;
                best[1] = max;
            }
            
            // Move forward in the list from which we removed the minimum
            if (pos + 1 == nums.get(listIdx).size()) {
                break; // one list is exhausted, cannot form any more ranges
            }
            
            int nextVal = nums.get(listIdx).get(pos + 1);
            pq.offer(new int[]{nextVal, listIdx, pos + 1});
            max = Math.max(max, nextVal);
        }
        
        return best;
    }
}
