public class p3270 {


    /**
     * 给你三个 正 整数 num1 ，num2 和 num3 。
     *
     * 数字 num1 ，num2 和 num3 的数字答案 key 是一个四位数，定义如下：
     *
     * 一开始，如果有数字 少于 四位数，给它补 前导 0 。
     * 答案 key 的第 i 个数位（1 <= i <= 4）为 num1 ，num2 和 num3 第 i 个数位中的 最小 值。
     * 请你返回三个数字 没有 前导 0 的数字答案。
     */

    class Solution {

//        public static int[][] arr = new int[3][4];

        public int generateKey(int num1, int num2, int num3) {

//            for(int i = 0 ; i < 4 ; i ++){
//
//                arr[0][i] = num1 % 10;
//                arr[1][i] = num2 % 10;
//                arr[2][i] = num3 % 10;
//
//                num1 /= 10;
//                num2 /= 10;
//                num3 /= 10;
//            }
//
//            int res = 0;
//            int count = 1;
//            for(int i = 0 ; i < 4 ; i ++){
//
//                res += count * Math.min(arr[0][i] ,
//                        Math.min(arr[1][i] , arr[2][i]));
//                count *= 10;
//            }

            int res = 0;
            int count = 1;
            for(int i = 0 ; i < 4 ; i ++){

                res += Math.min(Math.min(num1 % 10 , num2 % 10) , num3 % 10) * count;

                count *= 10;
                num1 /= 10;
                num2 /= 10;
                num3 /= 10;
            }
            return res;
        }
    }
}
