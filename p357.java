public class p357 {

    /**
     * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
     */

    class Solution {

        public int countNumbersWithUniqueDigits(int n) {

            if(n == 0)
                return 1;
            if(n == 1)
                return 10;


            int res = 0;
            for(int i = n; i > 1 ; i--){

                res += 9 * A(9 , i - 1);
            }

            res += 10;

            return res;

        }

        public int A(int a ,int b){

            int res = 1;
            for(int i = 0 ; i < b ; i++){

                res *= (a - i);
            }

            return res;
        }
    }
}
