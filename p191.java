public class p191 {

    /**
     * 编写一个函数，获取一个正整数的二进制形式并返回其二进制表达式中
     * 设置位
     *  的个数（也被称为汉明重量）
     */



    class Solution {
        public int hammingWeight(int n) {


            int res = 0;
            while(n != 0){

                res += n & 1;
                n >>= 1;
            }

            return res;
        }
    }

}
