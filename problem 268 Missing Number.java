class Solution {
    public int missingNumber(int[] nums) {
        
        int numsSum=0;
        for (int i =0;i<nums.length;i++){
            numsSum += nums[i];
        }
        int totalsum = (nums.length * (nums.length + 1) ) /2;
        int actualnumber = totalsum-numsSum;
        return actualnumber;




        
    }
}