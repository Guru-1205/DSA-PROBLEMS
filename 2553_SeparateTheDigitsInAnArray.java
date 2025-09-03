class Solution {
    public int[] separateDigits(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
        }
        String str = sb.toString();
        int[] ans = new int[str.length()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = str.charAt(i) - '0';
        }
        return ans;
    }
}
