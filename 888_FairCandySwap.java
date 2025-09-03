class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sum = 0, sum1 = 0;
        for (int i = 0; i < aliceSizes.length; i++) {
            sum = sum + aliceSizes[i];
        }
        for (int i = 0; i < bobSizes.length; i++) {
            sum1 = sum1 + bobSizes[i];
        }
        int halfsum = (sum1 + sum) / 2;

        for (int i = 0; i < aliceSizes.length; i++) {
            for (int j = 0; j < bobSizes.length; j++) {
                if (aliceSizes[i] + sum1 - bobSizes[j] == halfsum &&
                        bobSizes[j] + sum - aliceSizes[i] == halfsum) {
                    int temp = aliceSizes[i];
                    aliceSizes[i] = bobSizes[j];
                    bobSizes[j] = temp;
                    return new int[] { bobSizes[j], aliceSizes[i] };
                }

            }
        }
        return aliceSizes;
    }

}