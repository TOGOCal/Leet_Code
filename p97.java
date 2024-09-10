public class p97 {

    /**
     * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
     */



    class Solution {
        //简化二维表的方法
        public boolean isInterleave(String s1, String s2, String s3) {

            if(s1.length() + s2.length() != s3.length()){

                return false;
            }


            char[] str1= s1.toCharArray();
            char[] str2= s2.toCharArray();

            char[] aim = s3.toCharArray();

            boolean[] dpStr1 = new boolean[s1.length() + 1];
            boolean[] dpStr2 = new boolean[s2.length() + 1];

            dpStr2[0] = true;
            dpStr1[0] = true;

            for(int i = 1; i <= s1.length(); i++){

                dpStr1[i] = dpStr1[i-1] && str1[i-1] == aim[i-1];
            }

            for(int i = 1; i <= s2.length(); i++){

                dpStr2[i] = dpStr2[i-1] && str2[i-1] == aim[i-1];
            }


            for(int i = 1; i <= s2.length(); i++){

                dpStr1[0] = dpStr2[i];
                for(int j = 1; j <= s1.length(); j++){

                    dpStr1[j] = (dpStr1[j-1] && str1[j-1] == aim[i+j-1]) || (dpStr1[j] && str2[i-1] == aim[i+j-1]);
                }
            }

            return dpStr1[s1.length()];

        }
    }




//    class Solution {
//        public boolean isInterleave(String s1, String s2, String s3) {
//
//            if(s1.length() + s2.length() != s3.length()){
//
//                return false;
//            }
//
//
//            boolean[][] dp = new boolean[s2.length() + 1][s1.length() + 1];
//
//            char[] str1= s1.toCharArray();
//            char[] str2= s2.toCharArray();
//
//            char[] aim = s3.toCharArray();
//
//
//            dp[0][0] = true;
//            for(int i = 1; i <= s2.length(); i++){
//
//                dp[i][0] = dp[i-1][0] && str2[i-1] == aim[i-1];
//            }
//
//            for(int i = 1; i <= s1.length(); i++){
//
//                dp[0][i] = dp[0][i-1] && str1[i-1] == aim[i-1];
//            }
//
//            /**
//             * 最后一位aim的字符串要么从str1中取，要么从str2中取得
//             */
//
//            for(int i = 1; i <= s2.length(); i++){
//
//                for(int j = 1; j <= s1.length(); j++){
//
//                    dp[i][j] = (dp[i-1][j] && str2[i-1] == aim[i+j-1]) || (dp[i][j-1] && str1[j-1] == aim[i+j-1]);
//                }
//            }
//
//            return dp[s2.length()][s1.length()];
//
//        }
//    }
}
