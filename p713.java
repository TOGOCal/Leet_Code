public class p713 {

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().numSubarrayProductLessThanK(new int[]{10 , 5 , 2, 6} , 100));
    }


    static
    class Solution {


        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (k == 0)
                return 0;

            int left = 0;
            int right = 0;
            int sum = nums[0];
            int res = 0;
            while(true){

                while(left < right && sum >= k)
                    sum /= nums[left++];
                res += (sum < k ? right - left + 1 : 0 );

                if(right + 1 >= nums.length)
                    break;
                sum *= nums[++right];
            }

            return res;
        }
    }
}
