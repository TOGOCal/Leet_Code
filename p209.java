public class p209 {

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     *
     * 找出该数组中满足其总和大于等于 target 的长度最小的
     * 子数组
     *  [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     */

    public static void main(String[] args) {

        int[] nums = {2,3,1,2,4,3};
        Solution solution = new Solution();
        System.out.println(solution.minSubArrayLen(7, nums));
    }


    static
    class Solution {
        public int minSubArrayLen(int target, int[] nums) {

            if(nums == null || nums.length == 0){

                return 0;
            }

            int res = Integer.MAX_VALUE;

            int p1 = 0;
            int p2 = 0;

            int sum = 0;

            while(p2 < nums.length){

                while(sum < target && p2 < nums.length){

                    sum += nums[p2++];
                    //p2++;//p2的位置是系啊一个要到达的位置

                }

                if(p2 == nums.length && sum < target){

                    break;
                }

                while(sum - nums[p1] >= target && p1 <= p2){

                    sum -= nums[p1++];//p1的位置是已经到达的位置
                    //p1++;
                }

                res = Math.min(res, p2-p1);

                sum -= nums[p1++];
                //p1++;
            }

            if(res == Integer.MAX_VALUE){

                return 0;
            }

            return res;
        }
    }

}
