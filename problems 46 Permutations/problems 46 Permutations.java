import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(int[] nums, boolean[] used, 
                           List<Integer> path, List<List<Integer>> result) {
        // Base case: permutation is complete
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path)); // deep copy
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            
            used[i] = true;          // choose
            path.add(nums[i]);
            
            backtrack(nums, used, path, result); // explore
            
            path.remove(path.size() - 1);        // un-choose
            used[i] = false;
        }
    }
}
