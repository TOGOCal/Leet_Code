import java.util.Scanner;

public class p32 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String str = s.nextLine();

        System.out.println(new Solution().longestValidParentheses(str));

        s.close();
    }

    static class Solution {
        //动态规划
        public int longestValidParentheses(String s) {

            if(s == null || s.isEmpty()){

                return 0;
            }

            //以某个下表结尾的最大有效长度
            int[] dp = new int[s.length()];

            char[] str = s.toCharArray();

            int maxLength = Integer.MIN_VALUE;

            for(int i= 1;i<str.length;i++){

                if(str[i] == '('){

                    dp[i] = 0;
                }else{

                    if(str[i-1] == '('){

                        if(i<2){
                            dp[i] = 2;
                        }else{
                            dp[i] = dp[i-2]+2;
                        }
                        maxLength = Math.max(maxLength , dp[i]);
                    }else{

                        //如果是))这种形式，就需要去找前面的位置
                        //length = end - begin + 1
                        //begin = end + 1 - length 的前一个位置
                        if( i - 1 +1 - dp[i-1] -1 >=0 &&  str[ i - 1 +1 - dp[i-1] -1] == '('){

                            dp[i] = dp[i-1]+1;
                            maxLength = Math.max(maxLength , dp[i]);

                        }else{

                            dp[i] = 0;
                        }
                    }
                }
            }
            if(maxLength == Integer.MIN_VALUE){

                return 0;
            }

            return maxLength;
        }
    }

//    static class Solution {
//        public int longestValidParentheses(String s) {
//
//            if(s == null || s.isEmpty()) {
//
//                return 0;
//            }
//
//            char[] str = s.toCharArray();
//
//
//            int result = Integer.MIN_VALUE;
//            for(int i =0 ;i < str.length ; i++){
//                //以某个位置的作为开头
//
//                if(str[i] == ')') {
//
//                    continue;
//                }
//
//                int count = 0;
//                int maxLength = Integer.MIN_VALUE;
//                for(int j = i ; j < str.length ; j++){
//
//                    if(str[j] == '(') {
//                        count++;
//                    }else{
//
//                        count--;
//                    }
//
//                    if(count < 0){
//
//                        break;
//                    }
//
//                    if(count == 0 && str[j] == ')') {
//
//                        maxLength = Math.max(maxLength, j - i + 1);
//                    }
//                }
//
//
//                result = Math.max(result, maxLength);
//
//            }
//
//            if(result == Integer.MIN_VALUE) {
//
//                return 0;
//            }
//
//
//            return result;
//        }
//    }
}