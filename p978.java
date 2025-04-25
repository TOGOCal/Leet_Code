public class p978 {

    /**
     * 给定一个整数数组 arr ，返回 arr 的 最大湍流子数组的长度 。
     *
     * 如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是 湍流子数组 。
     *
     * 更正式地来说，当 arr 的子数组 A[i], A[i+1], ..., A[j] 满足仅满足下列条件时，我们称其为湍流子数组：
     */

    public static void main(String[] args) {

        System.out.println(new Solution().maxTurbulenceSize(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9}));
    }


    static
    class Solution {

        public static int MAXN = 40_000 + 1;
        //0代表要求下一个位置小于当前数
        //1代表要求下一个位置大于当前数
        public static int[][] dp = new int[MAXN][2];

        public int maxTurbulenceSize(int[] arr) {

            int l = arr.length;
            dp[l - 1][0] = 1;
            dp[l - 1][1] = 1;//最后一个位置

            for(int i = l - 2 ; i >= 0 ; i --){

                dp[i][1] = 1;
                dp[i][0] = 1;
                if(arr[i] > arr[i + 1]){

                    dp[i][0] = dp[i + 1][1] + 1;

                }else if(arr[i] < arr[i + 1]){

                    dp[i][1] = dp[i + 1][0] + 1;
                }
            }

            int res = 0;
            for(int i = 0 ; i < l ; i ++){

                res = Math.max(res ,
                        Math.max(dp[i][0] , dp[i][1]));
            }

            return res;
        }
    }
}
