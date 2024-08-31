public class p188 {

    /**
     * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */


    /*
    方法：动态规划：
    dp[i][j]代表 0-i时间点，进行j次交易能够获得的最大利润
    依赖关系：是否在i时间进行交易
    由于如果在i时间进行买入，则一定会比之前的情况亏损，因为没有卖出时间
    所以决策点就是是否在i位置进行卖出操作，所以现在需要决策的就是在什么时间点进行买入操作
    在i-1时间买入 例如 = dp[i-1][j-1] - prices[i-1] + prices[i]
    在i-2时间买入 例如 = dp[i-2][j-1] - prices[i-2] + prices[i]
    ...
    在0时间买入 例如 = dp[0][j-1] - prices[0] + prices[i]

    同理，如果考虑dp[i+1][j]
    在i时间买入 例如 = dp[i][j-1] - prices[i] + prices[i+1]
    在i-1时间买入 例如 = dp[i-1][j-1] - prices[i-1] + prices[i+1]
    在i-2时间买入 例如 = dp[i-2][j-1] - prices[i-2] + prices[i+1]
    ...

    从上面两个式子可以看出，有一部分的比较是重复的，所以只需要比较之前的max与 dp[i-1][j-1]-price[i-1]的大小就可以了

    由这个依赖关系，可以推算出基础情况是什么
    j = 0，进行0笔交易
    i = 0 ，只有0时间才能进行交易
    */
    public static void main(String[] args) {

        Solution s = new Solution();
        int[] prices = {2,4,1};
        System.out.println(s.maxProfit(2, prices));
    }


    static
    class Solution {
        public int maxProfit(int k, int[] prices) {

            int[][] dp = new int[prices.length][k+1];


            //只能在0时间进行操作
//            for(int j = 0; j <= k; j++){
//
//                dp[0][j] = 0;//由于初始化的时候默认就是0，所以可以不写
//            }

            //进行0笔交易的时候，由于活力也是0，所以也可以不写
            int max;


            for(int j = 1; j <= k; j++){

                max = -prices[0];
                //dp[0][j] = 0;
                //买入时间
                for(int i = 1; i < prices.length; i++){

                    max = Math.max(max, dp[i-1][j-1] - prices[i-1]);

                    int choice1 = prices[i] + max;//在i时间卖出
                    int choice2 = dp[i-1][j];//不进行交易（在i时间不进行交易

                    dp[i][j] = Math.max(choice1, choice2);
                }
            }




            return dp[prices.length-1][k];

        }
    }

}
