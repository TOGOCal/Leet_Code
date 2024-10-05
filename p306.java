import java.math.BigInteger;

public class p306 {

    /**
     * 累加数 是一个字符串，组成它的数字可以形成累加序列。
     *
     * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，序列中的每个后续数字必须是它之前两个数字之和。
     *
     * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
     *
     * 说明：累加序列里的数，除数字 0 之外，不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
     */


    public static void main(String[] args) {

        String s = "9910011992";

        System.out.println(new Solution().isAdditiveNumber(s));
    }


    static
    class Solution {
        public boolean isAdditiveNumber(String num) {
            return dfs(num, 0, 0, 0, 0);
        }

        private boolean dfs(String num, int index, int count, long prevprev, long prev) {
            if (index >= num.length()) {
                return count > 2;
            }//当已经超过边界的时候，判断是不是没有分出3个数


            long current = 0;//现在这个数
            for (int i = index; i < num.length(); i++) {
                char c = num.charAt(i);
                if (num.charAt(index) == '0' && i > index) {
                    return false;
                }//防止第一个数是0

                current = current * 10 + c - '0';

                if (count >= 2) {
                    long sum = prevprev + prev;

                    if (current > sum) {
                        return false;
                    }

                    if (current < sum) {
                        continue;
                    }//逐一判断是不是相等的
                }

                // 当前满足条件了，或者还不到两个数，向下一层探索
                if (dfs(num, i + 1, count + 1, prev, current)) {
                    return true;
                }

            }
            return false;
        }
    }


//    static
//    class Solution {
//        public boolean isAdditiveNumber(String num) {
//
//            if(num.length() < 3)
//                return false;
//
//            if(num.charAt(0) == '0') {
//
//                for (int j = 1; j < num.length(); j++) {
//
//
//                    if (num.charAt(1) == '0' && j != 1) {
//
//                        continue;
//                    }
//
////                    BigInteger num1 = new BigInteger("0");
////                    BigInteger num2 = new BigInteger(num.substring(1, j + 1));
//                    long num1 = 0;
//                    long num2 = Long.parseLong(num.substring(1, j + 1));
//
//                    if (isUseful(num1, num2, num.substring(j + 1))) {
//
//                        return true;
//                    }
//                }
//
//
//                return false;
//            }
//
//
//            for(int i = 0 ; i < num.length() ; i++){
//
//                //执行枚举行为
//                for(int j = i + 1 ; j < num.length() ; j++){
//
//
//                    if(num.charAt(i + 1) == '0' && j != i + 1){
//
//                        continue;
//                    }
//
////                    BigInteger num1 =   new BigInteger(num.substring(0, i + 1));
////                    BigInteger num2 = new BigInteger(num.substring(i + 1 , j + 1));
//                    long num1 = Long.parseLong(num.substring(0, i + 1));
//                    long num2 = Long.parseLong(num.substring(i + 1 , j + 1));
//
//                    if(isUseful(num1 , num2 , num.substring(j + 1))){
//
//                        return true;
//                    }
//                }
//            }
//
//            return false;
//        }
//
//
//        public boolean isUseful(long prePreNum , long preNum , String num){
//
//            long sum = prePreNum + preNum;
//
//            String n = String.valueOf(sum);
//
//            if(n.length() > num.length()){
//
//                return false;
//            }
//
////            BigInteger value = new BigInteger(num.substring(0 , n.length()));
//
//            long value = Long.parseLong(num.substring(0 , n.length()));
//
//            if(value != sum){
//
//                return false;
//            }else{
//
//                if(n.length() == num.length()){
//
//                    return true;
//                }else{
//
//                    return isUseful(preNum , sum , num.substring(n.length()));
//                }
//            }
//        }
//    }
}
