class Solution {
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer,Integer> count=new HashMap<>();
        HashMap<Integer,Integer> st=new HashMap<>();//starting index
        HashMap<Integer,Integer> end=new HashMap<>();//ending index

        for(int i=0;i<nums.length;i++){
            int num=nums[i];
            count.put(num,count.getOrDefault(num,0)+1);
            if(!st.containsKey(num)){
                st.put(num,i);
            }
            end.put(num,i);
        }

        int degree=Collections.max(count.values());
        int minLen=nums.length;

        for(int num : nums){
            if(count.get(num)==degree){
                int length=end.get(num)-st.get(num)+1;
                minLen=Math.min(length,minLen);
            }
        }

     return minLen;
   
    }
}