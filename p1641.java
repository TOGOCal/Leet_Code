package from_1600_to_1700;

import java.util.Arrays;

public class p1641 {

    /**
     * 给你一个整数 n，请返回长度为 n 、仅由元音 (a, e, i, o, u) 组成且按 字典序排列 的字符串数量。
     *
     * 字符串 s 按 字典序排列 需要满足：对于所有有效的 i，s[i] 在字母表中的位置总是与 s[i+1] 相同或在 s[i+1] 之前。
     */

    class Solution {

        public static int MAXN = 50 + 1;
        public static int[][] dp = new int[5][MAXN];
        //以哪个字母开头的，剩下的长度为j的，组合数量
        static {

            for(int i = 0 ; i < 5 ; i ++)
                dp[i][1] = 1;

            for(int l = 2 ; l < MAXN ; l ++){

                int temp = 0;
                for(int i = 4 ; i >= 0 ; i --){

                    temp += dp[i][l - 1];
                    dp[i][l] = temp;
                    //0 = 0 + 1 + 2 + 3 + 4
                    //1 = 1 + 2 + 3 + 4
                    //。。。。
                    //4 = 4；
                }
            }
        }

        public int countVowelStrings(int n) {

            int res = 0;
            for(int i = 0 ; i < 5 ; i ++)
                res += dp[i][n];
            return res;
        }
    }
}
