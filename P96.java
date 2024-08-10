public class P96 {

    /**
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     */

    //方法：依次决定哪个为头

    class Solution {

        int[][] dp;

        public int numTrees(int n) {

            if(n == 0 || n == 1){

                return n;
            }

            dp = new int[n + 1][n + 1];

            return howMany(1 , n);
        }

        //start ， end都是有效位置
        public int howMany(int start , int end){


            if(start >= end){

                return 1;
            }

            if(dp[start][end] != 0){

                return dp[start][end];
            }


            int sum = 0;
            for(int i = start ; i <= end ; i++){

                int left = howMany(start , i - 1);
                int right = howMany(i + 1 , end);

                sum += left * right;
            }


            dp[start][end] = sum;
            return sum;
        }
    }
}
