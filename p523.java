package from_500_to_600;

import java.util.HashMap;

public class p523 {

    /**
     * 给你一个整数数组 nums 和一个整数 k ，如果 nums 有一个 好的子数组 返回 true ，否则返回 false：
     *
     * 一个 好的子数组 是：
     *
     * 长度 至少为 2 ，且
     * 子数组元素总和为 k 的倍数。
     * 注意：
     *
     * 子数组 是数组中 连续 的部分。
     * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终 视为 k 的一个倍数。
     */


    public static void main(String[] args) {

        System.out.println(new Solution().checkSubarraySum(new int[]{1, 0}, 2));
    }



    //如果整除，则同余
    static
    class Solution {

        public static int MAXL = 100001;
        public static int[] preSum = new int[MAXL];

        public boolean checkSubarraySum(int[] nums, int k) {
            if(nums == null || nums.length < 2)
                return false;

            HashMap<Integer , Integer> p = new HashMap<>();
            p.put(0 , -1);
            int sum = 0;
            preSum[0] = sum;
            for(int i = 0 ; i < nums.length ; i ++){

                preSum[i + 1] = sum = sum + nums[i];
                int last = sum % k;
                if(p.containsKey(last)){

                    int pre = p.get(last);
                    if(i - pre >= 2)
                        return true;
                }else{

                    p.put(last , i);
                }
            }


            return false;
        }
    }
}
