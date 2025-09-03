class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int count=1;
        int max=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i-1]<nums[i]){
                count++;
            }else{
                if(max<count){
                    max=count;
                }
                count=1;
            }
        }
        if(max<count)
            return count;
            return max;
    }
}
