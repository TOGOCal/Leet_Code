import java.util.Arrays;

public class p3396 {

    /**
     * 给你一个整数数组 nums，你需要确保数组中的元素 互不相同 。为此，你可以执行以下操作任意次：
     *
     * 从数组的开头移除 3 个元素。如果数组中元素少于 3 个，则移除所有剩余元素。
     * 注意：空数组也视作为数组元素互不相同。返回使数组元素互不相同所需的 最少操作次数 。
     */

    class Solution {

        public static int MAXN = 101;
        public static boolean[] have = new boolean[MAXN];

        public int minimumOperations(int[] nums) {

            Arrays.fill(have , false);

            int n = nums.length;
            int i = n -1;
            for( ; i >=0 ; i --){

                int val = nums[i];
                if(have[val])
                    break;
                have[val] = true;
            }

            int count = i + 1;
            return (count + 2) / 3;//向上取整
        }
    }
}
