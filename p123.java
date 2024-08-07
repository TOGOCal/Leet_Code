public class p123 {

    /**
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */

    //在最多能进行k笔交易的时候
    /**
     * 如果k > n/2，那么就可以当作无限次交易
     * 就按照上一个题目的解法
     * 动态规划可以按照如下进行构建：
     * dp[i][j] 代表 前 i 天限制交易次数为 j 时的最大利润
     * 动态规划依赖关系：
     * dp[i][j]主要决定方式：
     * 在不在i时间点进行交易
     * 如果不进行交易 dp[i][j] =  dp[i-1][j]
     * 如果在i时间点进行交易，则一定是卖出
     * 因为是买入的话就不会有卖出行为，一定是亏的
     * 所以在此基础上，只需要决定何时买入就可以了
     *
     * //i时刻买入，i时刻卖出 = dp[i][j-1] + [i]
     * 1.i-1时刻买入， - [i-1] + dp[i-1][j-1] + [i] => dp[i-1][j] - [i-1]比较的结果
     * 2.i-2时刻买入， - [i-2] + dp[i-2][j-1] + [i]
     * ...
     * 0时刻买入 -[0] + [i] + dp[0][j-1]
     * 这些所有值中取最大值
     * dp[i][j] = max + [i]
     *
     * 对dp[i-1][j]做分析，我们同样可以得到
     *
     * i-2时刻买入    dp[i-2][j-1] -[i-2] + [i-1]
     * 但是可以发现，其实后面的一坨在前面已经比较过了
     * 所以需要比较的仅仅是
     * dp[i][j-1] -[i] 与 -[i] + dp[i][j]
     *
     */


    public static void main(String[] args) {

        int[] prices = {6, 1, 3, 2, 4, 7};
        Solution solution = new Solution();
        System.out.println(solution.maxProfit(prices));
    }

    static
    class Solution {
        public int maxProfit(int[] prices) {

            int len = prices.length;
            //这道题的背景是两次交易，
            if(2 >= len/2){

                int res = 0;

                for (int i = 1; i < prices.length; i++) {

                    if (prices[i] > prices[i-1]) {

                        res += prices[i] - prices[i - 1];
                    }
                }

                return res;
            }

            int[] dp = new int[len];


            //填写顺序：每一列从上到下，列从左向右
            for( int j = 1; j < 3; j++){

                int max =  -prices[0];
                for( int i = 1; i < len; i++) {

                    max = Math.max(
                            max,
//                            dp[i][j-1] - prices[i]
                            dp[i] - prices[i]
                    );

                    dp[i] = Math.max(
                            max + prices[i],
                            //dp[i-1][j]
                            dp[i-1]
                    );

                }
            }

            return dp[len - 1];

        }
    }


}

//===================================
//            int[][] dp = new int[prices.length][2 + 1];
//
//            //进行0笔交易
//            for(int i =0 ;i < prices.length ; i++){
//
//                dp[i][0] = 0;
//            }
//
//            //只有0时间点（无论怎么交易获利始终是0
//            for(int i = 0 ; i < 3 ; i ++){
//
//                dp[0][i] = 0;
//            }
//
//            for(int i = 1; i < 3 ;i ++){
//
//                for(int time = 1; time < prices.length ; time ++){
//
//                    dp[time][i] = Math.max(
//                            dp[time-1][i],//不进行交易
//                            Math.max(dp[time][i-1] , dp[time-1][i] - prices[time-1])
//                    );
//                }
//            }
//
//            return dp[prices.length-1][2];
