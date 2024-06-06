public class p91 {

    public static void main(String[] args) {

    }

    //方法2：暴力递归改动态规划
    class Solution {
        //可能访问到的位置 s.length  s.length+1
        int[] dp;
        public int numDecodings(String s) {

            char[] str = s.toCharArray();
            dp = new int[s.length()+2];
            dp[s.length()+1] = 0;
            dp[s.length()] = 1;

//            int res = 0;
            for(int index = str.length-1; index >= 0 ; index--) {

                if(str[index] == '0'){

                    dp[index] = 0;
                    continue;
                }

                dp[index] += dp[index + 1];
                //2.从当前位置拿取2个数字
                if (index + 1 < str.length && (str[index] - '0') * 10 + (str[index + 1] - '0') <= 26) {

                    dp[index] += dp[index + 2];
                }

            }

            return dp[0];
        }


    }

//    class Solution {
//        public int numDecodings(String s) {
//
//            char[] chars = s.toCharArray();
//            return method(0, chars);
//        }
//
//        //暴力递归
//        public int method(int index , char[] str) {
//
//            int res = 0;
//            if(index == str.length) {
//
//                return 1;
//            }else if(str[index] == '0') {
//                //无论是一次取一个还是一次取两个，都不会在当前的情况中出现第一个数字是0的情况
//                return 0;
//            }
//
//            //1.从当前位置拿取1个数字
//            //如果本数字不是0，则一定可以找到对应的数字
//            res += method(index + 1, str);
//            //2.从当前位置拿取2个数字
//            if(index + 1 < str.length && (str[index] - '0') * 10 + (str[index + 1] - '0') <= 26){
//
//                res += method(index + 2, str);
//            }
//
//            return res;
//        }
//    }


}
