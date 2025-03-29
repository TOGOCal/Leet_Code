public class p2712 {

    /**
     * 给你一个下标从 0 开始、长度为 n 的二进制字符串 s ，你可以对其执行两种操作：
     *
     * 选中一个下标 i 并且反转从下标 0 到下标 i（包括下标 0 和下标 i ）的所有字符，成本为 i + 1 。
     * 选中一个下标 i 并且反转从下标 i 到下标 n - 1（包括下标 i 和下标 n - 1 ）的所有字符，成本为 n - i 。
     * 返回使字符串内所有字符 相等 需要的 最小成本 。
     *
     * 反转 字符意味着：如果原来的值是 '0' ，则反转后值变为 '1' ，反之亦然。
     */

    class Solution {

        /**
         * 贪心策略1：
         * 0-i操作对大于一半的index没有意义，因为我也可以用更小的代价使用index-n-1操作去反转剩下的部分
         */
        public long minimumCost(String s) {

            char[] str = s.toCharArray();
            int mid = str.length >> 1;
            long res = 0;

            //选取靠左的中间字符作为最后变动的字符
            for(int i = mid - 1 ; i >= 0 ; i --){

                if(str[i] != str[i + 1])
                    res += (i + 1);
            }

            int n = str.length;
            for(int i = mid + 1 ; i < n ; i ++){

                if(str[i] != str[i - 1])
                    res += (n - i);
            }

            return res;
        }
    }
}
