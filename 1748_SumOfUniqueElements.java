class Solution {
    public int sumOfUnique(int[] nums) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int num:nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for (int key : map.keySet()) {
            if (map.get(key) == 1) {  // frequency check
                ans += key;  // adding the key, not its frequency
            }
        }
        
        return ans;
    }
}
