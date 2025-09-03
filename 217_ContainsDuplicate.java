// using hashmap

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer,Integer>map=new HashMap<>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        for(int key:map.keySet()){
            if(map.get(key)>=2){
                return true;
            }
        }
        return false;
    }
}

// using sorting
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        
        for (int i = 1; i < n; i++) {

            if (nums[i] == nums[i - 1])

                return true;
        }
        return false;
    }
}