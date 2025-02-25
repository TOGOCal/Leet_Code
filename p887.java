import java.util.Arrays;

public class p887 {

    /**
     * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
     *
     * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
     *
     * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
     *
     * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
     */

    public static void main(String[] args) {

        System.out.println(new Solution().superEggDrop(2 , 6));
    }

    static
    class Solution {
        public static int MAXK = 101;
        public static int MAXN = 10001;
        public int superEggDrop(int k, int n) {

            int[] arr = new int[k + 1];
            Arrays.fill(arr , 1);
            for(int f = 2 ; f <= n ; f ++){

                for(int i = k ; i >= 2 ; i --){

                    arr[i] = arr[i - 1] + arr[i] + 1;
                    if(arr[i] >= n)
                        return f;
                }
                arr[1] = f;
            }
            return n;
        }

        //函数：k个鸡蛋，进行maxn次操作，最多能确定多少层楼
        public static int dp(int[][] dp , int k , int num){

            for(int i = 1 ; i <= k ; i ++){

                dp[i][1] = 1;//不管给定多少个鸡蛋，只能操作一次，最多确定一楼
            }

            for(int i = 0 ; i <= num ; i ++){

                dp[1][i] = i;//i次操作，但是只有一个蛋，多少次操作最多就确定几楼
            }


            //遍历操作次数
            for(int f = 2 ; f <= num ; f ++){

                //遍历蛋的数量
                for(int n = 2 ; n <= k ; n ++){

                    //          这个蛋碎了，说明f在之下，可以确定dp[n-1][f-1]楼
                    //蛋没碎，说明f在之上，能够确定dp[n][f-1]楼
                    dp[n][f] = dp[n - 1][f - 1] + dp[n][f - 1] + 1;
                    if(dp[n][f] >= n)
                        return f;
                }
            }

            return -1;
        }
    }

}
