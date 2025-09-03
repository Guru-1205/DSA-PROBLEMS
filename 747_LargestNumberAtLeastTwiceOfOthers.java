class Solution {
    public int dominantIndex(int[] nums) {
        int n = nums.length;

     
        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);
        int maz=copy[n-1];
        int second=n>1?copy[n-2]:-1;

        if(maz>=(second*2)){
            for(int i=0;i<nums.length;i++){
                if(nums[i]==maz){
                    return i;
                }
            }
        }
        return -1;
    }
}

