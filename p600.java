public class p600 {

    /**
     * 给定一个正整数 n ，请你统计在 [0, n] 范围的非负整数中，有多少个整数的二进制表示中不存在 连续的 1 。
     */

    /**
     * 出现不连续的1的情况
     * 1.从1开始迭代
     * 101  10101  1010101
     *
     * 分情况进行分类：（以下数字都是二进制表示形式
     * 10001001 可以拆分成 10000000 和 1001以此进行迭代
     * 10000000中所有数字有哪些不包含连续的1 可以使用动态规划解决
     * 1001  =  1000  + 1
     */

    public static void main(String[] args) {

        int n = 6;
        Solution solution = new Solution();

        int a = solution.findIntegers(n);
        System.out.println(a);
    }

    static
    class Solution {
        public int findIntegers(int n) {
            int[] dp = new int[31];
            dp[0] = dp[1] = 1;
            for (int i = 2; i < 31; ++i) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }

            int pre = 0, res = 0;
            for (int i = Integer.toBinaryString(n).length(); i >= 0; i--) {
                int val = 1 << i;
                if ((n & val) != 0) {
                    res += dp[i + 1];
                    if (pre == 1) {
                        break;
                    }
                    pre = 1;
                } else {
                    pre = 0;
                }

                if (i == 0) {
                    res++;
                }
            }

            return res;
        }
    }



//    class Solution {
//
//        HashMap<String , Integer> map;
//        int target;
//
//        public int findIntegers(int n) {
//
//            map = new HashMap<>();
//            target = n;
//
//            //求n的二进制有几位有效：
//            int a = Integer.toBinaryString(n).length();
//
//            return dfs(a , 1, 0);
//        }
//
//        //深度优先遍历(pre 可以通过 sum > 2^depth - 1 来判断)
//        public int dfs(int n , int depth ,int sum) {
//
//            //depth代表当前的深度
//            if(depth == n+1){
//
//                if(sum > target){//这不是一种合格的情况
//                    return 0;
//                }
//
//                map.put(""+sum , 1);
//                return 1;
//            }
//
//            if(map.containsKey(depth+" "+sum)){
//
//                return map.get(depth+" "+sum);
//            }
//
//            // _0000 现在正在决定_位的字符  ，目前depth是多少，则当前正在决定的字符就是哪一位，比如此时就是 depth = 5
//            int preDepth = depth-1;
//
////            if(sum >= Math.pow(2, preDepth -1)){
////
////                //上一次填的是1
////                return dfs(n , depth+1 , sum);
////            }
////
////            return dfs(n , depth+1 , sum) + dfs(n , depth+1 , sum+ (int)Math.pow(2, preDepth));
//
//            if(depth == 1){
//
//                //1的深度哪个都可以填
//                return dfs(n , depth+1 , sum) + dfs(n , depth+1 , sum+1);
//            }
//
//            //简化：
//            int pre = (int)Math.pow(2, preDepth -1);
//
//            int thisPositionIsZero = dfs(n , depth+1 , sum);
//
//            if(sum < pre){
//
//                int thisPositionIsOne = dfs(n , depth+1 , sum + pre * 2);
//
//                thisPositionIsZero+= thisPositionIsOne;
//            }
//
//            map.put(depth+" "+sum , thisPositionIsZero);
//
//            return thisPositionIsZero;
//        }
//    }
}
