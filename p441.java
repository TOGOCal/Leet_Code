public class p441 {

    /**
     * 你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
     *
     * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
     */

    class Solution {

        public int arrangeCoins(int n) {

            int left = 0;
            int right = Integer.MAX_VALUE >> 1;
            int res = 0;
            while(left <= right){

                int mid = (left + right) >> 1;
                long sum = getSum(mid);
                if(sum < n){

                    res = mid;
                    left = mid + 1;
                }else if (sum > n){

                    right = mid - 1;
                }else
                    return mid;
            }

            return res;
        }

        private long getSum(int n){

            return ((1L + n) * n) >> 1;
        }
    }
}
