public class p213 {

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
     * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     */

    public static void main(String[] args) {

        int[] nums = {0 , 0};
        Solution solution = new Solution();
        System.out.println(solution.rob(nums));
    }


    static
    class Solution {

        int[][] dp;

        public int rob(int[] nums) {

            if(nums == null || nums.length == 0){

                return 0;
            }

            if(nums.length == 1){

                return nums[0];
            }

            if(nums.length == 2){

                return Math.max(nums[0], nums[1]);
            }


            //能够访问nums.length这个位置
            dp = new int[nums.length][2];

            //每个位置依赖
            //false只能依赖true的index+1
            //true是max(true , index + 1) + (nums[index]+(false , index+1
            //因此，每个基础位置是

            int max = Integer.MIN_VALUE;

            //初始化
            dp[nums.length-1][0] = 0;
            dp[nums.length-1][1] = 0;


            //一定不选最后一个
            for (int j = nums.length - 2; j >= 0; j--) {

                dp[j][0] = dp[j + 1][1];
                dp[j][1] = Math.max(dp[j + 1][1], dp[j + 1][0] + nums[j]);
            }

            max = Math.max(max, dp[0][1]);

            //一定选最后一个（也就是一定不选倒数第二个
            dp = new int[nums.length-1][2];
            dp[nums.length-2][0] = 0;
            dp[nums.length-2][1] = 0;

            for(int j = nums.length - 3; j >= 1; j--){

                dp[j][0] = dp[j + 1][1];
                dp[j][1] = Math.max(dp[j + 1][1], dp[j + 1][0] + nums[j]);
            }

            max = Math.max(max, dp[1][1] + nums[nums.length-1]);

            return max;
            //return dfs(nums,0,true);

        }

    }
}
