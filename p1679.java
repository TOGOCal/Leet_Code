import java.util.Arrays;

public class p1679 {

    /**
     * 给你一个整数数组 nums 和一个整数 k 。
     *
     * 每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
     *
     * 返回你可以对数组执行的最大操作数。
     */

    class Solution {
        public int maxOperations(int[] nums, int k) {

            Arrays.sort(nums);
            int p1 = 0;
            int p2 = nums.length - 1;

            int res = 0;
            while(p1 < p2){

                if(nums[p1] + nums[p2] < k)
                    p1++;
                else if(nums[p1] + nums[p2] > k)
                    p2--;
                else {

                    res ++;
                    p1 ++;
                    p2--;
                }
            }

            return res;
        }
    }
}
