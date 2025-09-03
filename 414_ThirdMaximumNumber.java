//1
class Solution {
    public int thirdMax(int[] nums) {
      Arrays.sort(nums);
      int n= nums.length;
      int count=1;

      for(int i=n-1;i>=1;i--){
        if(nums[i]!=nums[i-1]){
            count++;
        }
        if(count==3){
            return nums[i-1];
        }
        
      }
      return nums[n-1];

    }
}

//2
class Solution {
    public int thirdMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int num : nums) {
            set.add(num);
            if (set.size() > 3) {
                set.pollFirst(); // remove smallest if more than 3
            }
        }

        // if less than 3 distinct numbers → return largest
        if (set.size() < 3) {
            return set.last();
        }
        return set.first();
    }
}
