import java.util.Arrays;

public class p1155 {

    /**
     * 这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
     *
     * 给定三个整数 n、k 和 target，请返回投掷骰子的所有可能得到的结果（共有 kn 种方式），使得骰子面朝上的数字总和等于 target。
     *
     * 由于答案可能很大，你需要对 109 + 7 取模。
     */

    public static void main(String[] args) {
        System.out.println(new Solution2().numRollsToTarget(2, 6, 7));
        System.out.println(new Solution2().numRollsToTarget(30, 30, 500));
    }


    static
    class Solution2 {

        public static int mod = 1_000_000_000 + 7;
        public static int maxTarget = 1001;
        public static int MAXN = 31;
        //使用i个色子，投掷到j的方法数
        public static int[] dp = new int[maxTarget];
        public static int[] pre = new int[maxTarget];

        public int numRollsToTarget(int n, int k, int target) {


            for(int i = 0 ; i <= target ; i ++)
                if(1 <= i && i <= k)
                    //dp[1][i] = 1;
                    pre[i] = 1;
                else
                    //dp[1][i] = 0;
                    pre[i] = 0;


            for(int i = 2 ; i <= n ; i++){

                for(int j = 0 ; j < i ; j ++)
                    //dp[i][j] = 0;//由于色子最少都是1，所以值不可能比色子数还少
                    dp[j] = 0;

                long sum = 0;
                //预计算加和
                for(int l = Math.max(0 , i - 1 - k) ; l <= i - 2 ; l++)
                    //sum = (sum + dp[i - 1][l]) % mod;
                    sum = (sum + pre[l]) % mod;

                for(int j = i ; j <= target ; j ++){

                    //加和范围：j - k ~ j - 1
                    //之前的加和范围： j- 1 - k ~ j - 2
                    //新加入的：j - 1 ； 需要移除的： j - 1 - k
                    //sum = (sum + dp[i - 1][j - 1]) % mod;
                    sum = (sum + pre[j - 1]) % mod;
                    if(j - k - 1 >= 0)
                        //sum = (sum - dp[i - 1][j - k - 1] + mod) % mod;
                        sum = (sum - pre[j - k - 1] + mod) % mod;

                    //dp[i][j] = (int)sum;
                    dp[j] = (int)sum;
                }


                int[] temp = pre;
                pre = dp;
                dp = temp;
            }

            return pre[target];//因为是最后一步做的颠倒，所以会导致结果的位置被颠倒
        }
    }




    static
    class Solution {

        public static int mod = 1_000_000_000 + 7;
        public static int maxTarget = 1001;
        public static int MAXN = 31;
        //使用i个色子，投掷到j的方法数
        public static int[][] dp = new int[MAXN][maxTarget];

        public int numRollsToTarget(int n, int k, int target) {

            int[] d;
            d = dp[1];
            for(int i = 0 ; i <= target ; i ++)
                if(1 <= i && i <= k)
                    //dp[1][i] = 1;
                    d[i] = 1;
                else
                    //dp[1][i] = 0;
                    d[i] = 0;


            for(int i = 2 ; i <= n ; i++){

                d = dp[i];
                int[] pre = dp[i - 1];
                for(int j = 0 ; j < i ; j ++)
                    //dp[i][j] = 0;//由于色子最少都是1，所以值不可能比色子数还少
                    d[j] = 0;

                long sum = 0;
                //预计算加和
                for(int l = Math.max(0 , i - 1 - k) ; l <= i - 2 ; l++)
                    //sum = (sum + dp[i - 1][l]) % mod;
                    sum = (sum + pre[l]) % mod;

                for(int j = i ; j <= target ; j ++){

                    //加和范围：j - k ~ j - 1
                    //之前的加和范围： j- 1 - k ~ j - 2
                    //新加入的：j - 1 ； 需要移除的： j - 1 - k
                    //sum = (sum + dp[i - 1][j - 1]) % mod;
                    sum = (sum + pre[j - 1]) % mod;
                    if(j - k - 1 >= 0)
                        //sum = (sum - dp[i - 1][j - k - 1] + mod) % mod;
                        sum = (sum - pre[j - k - 1] + mod) % mod;

                    //dp[i][j] = (int)sum;
                    d[j] = (int)sum;
                }
            }

            return dp[n][target];
        }
    }
}
