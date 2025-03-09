import java.util.Arrays;

public class p516 {

    /**
     * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
     *
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     */

    class Solution {

        public static int MAXN = 1001;
        public static int realLength;
        public static char[] str1 = new char[MAXN];
        public static char[] str2 = new char[MAXN];
        public static int[][] dp = new int[MAXN][MAXN];

        public int longestPalindromeSubseq(String s) {

            int index = 0;
            realLength = s.length();
            for(char c: s.toCharArray()){

                str1[index] = c;
                str2[realLength - 1 - index] = c;
                index++;
            }//完成复制

            for(int[] d : dp)
                Arrays.fill(d , -1);

//            return dp(str1 , str2 , realLength - 1 , realLength - 1);
            return dp();
        }

        public int dp(){

            for(int index1 = 0 ; index1 < realLength ; index1++){

                for(int index2 = 0 ; index2 < realLength ; index2 ++){

                    boolean cant1 = index1 - 1 < 0;
                    boolean cant2 = index2 - 1 < 0;
                    int p1 = (cant1 || cant2 ? 0 : dp[index1 - 1][index2 - 1]);
                    int p2 = (cant1 ? 0 : dp[index1 - 1][index2]);
                    int p3 = (cant2 ? 0 : dp[index1][index2 - 1]);
                    int p4 = p1 + (str1[index1] == str2[index2] ? 1 : 0);
                    dp[index1][index2] = Math.max(
                            Math.max(p1 , p2),
                            Math.max(p3 , p4)
                    );
                }
            }

            return dp[realLength - 1][realLength - 1];
        }

        //求两个字符串的最长公共子序列
        public int dp(char[] str1 , char[] str2 , int index1 , int index2){

            if(index1 < 0 || index2 < 0)
                return 0;

            if(dp[index1][index2] != -1)
                return dp[index1][index2];

            int p1 = dp(str1, str2, index1 - 1, index2 - 1);//这两个位置都不参与子序列构成
            int p2 = dp(str1 , str2 , index1 , index2 - 1);
            int p3 = dp(str1, str2, index1 - 1, index2);
            int p4 = p1 + (str1[index1] == str2[index2] ? 1 : 0);

            dp[index1][index2] = Math.max(
                    Math.max(p1 , p2),
                    Math.max(p3 , p4)
            );
            return dp[index1][index2];
        }
    }
}
