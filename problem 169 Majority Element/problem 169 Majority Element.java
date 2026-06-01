class Solution {
    public int majorityElement(int[] nums) {
        int i =0 ;
        int candidate = nums[i];
        int votes=0;
        for (;i< nums.length ;i++){
             if (votes == 0){
                candidate = nums[i];
            }
           

            if (candidate == nums[i]){
                votes++;

            }
            else {
                votes--;
            }
/*
             if (votes == 0){
                candidate = nums[i+1];
            }
            WHAT I WAS DOING HERE I WAS  doing this after the votes-- it 
            was immediately making the opposite candidate as canidate so in next iteration it was taken as candidate 
            the catch is do it in the start or do it by +1
            means if the votes have been decreased to zero now then just make i+1 element the new candidate
            

            if (votes == 0){
                candidate = nums[i];
            }
            // Reset candidate at the START of the iteration when votes == 0.
            // If we reset at the END (after votes--), we immediately assign
            // the element that just cancelled out the previous candidate —
            // making the "loser" the new candidate. That's the bug.
            // Fix: reset here so the current element gets a fresh evaluation,
            // OR reset at the end using nums[i+1] to skip the current element.





            */

        }
        return candidate;
        
    }
}