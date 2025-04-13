public class p371 {

    /**
     * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
     */

    class Solution {
        public int getSum(int a, int b) {

            //所有没有进位的位所作不同加法的结果相当于异或
            //所有有进位的位的结果相当于 &   << 1
            //所以我们在执行加法的时候，只需要将进位和非进位加起来就可以了
            while(b != 0){

                int carry = (a & b) << 1;
                a = (a ^ b);
                b = carry;
            }

            return a;
        }
    }
}
