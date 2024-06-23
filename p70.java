import java.util.Scanner;

public class p70 {

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     */

    public static void main(String[] args) {
        Scanner s=  new Scanner(System.in);

        System.out.println(new Solution().climbStairs(s.nextInt()));

        s.close();
    }

    static class Solution {
        //方法3。动态规划改进
        public int climbStairs(int n) {

            if(n == 1){

                return 1;
            }

            if(n == 2){

                return 2;
            }

            int a = 1;
            int b = 2;
            int c = 0;

            for(int i = 2;i < n ;i++){

                c = a + b;
                a = b;
                b = c;
            }

            return c;
        }

    }

//    static class Solution {
//        //方法2：动态规划
//        public int climbStairs(int n) {
//
//            int[] dp = new int[n];
//
//            if(n == 1){
//                return 1;
//            }
//
//            if(n == 2){
//
//                ret//            }
////urn 2;

//
//            dp[0] = 1;
//            dp[1] = 2;
//
//            for(int i = 2;i<n;i++){
//
//                dp[i] = dp[i-1] + dp[i-2];
//            }
//
//            return dp[n-1];
//
//        }
//    }

//    static class Solution {
//        //方法1：暴力递归
//
//        public int climbStairs(int n) {
//            if(n == 1){
//
//                return 1;
//            }
//
//            if(n == 2){
//
//                return 2;
//            }
//
//            return climbStairs(n-1) + climbStairs(n-2);
//        }
//
//    }

}
