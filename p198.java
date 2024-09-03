public class p198 {

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     */


    public static void main(String[] args) {

        Solution solution = new Solution();

        int[] nums = {1,2,3,1};

        System.out.println(solution.rob(nums));
    }



    static
    //暴力递归
    class Solution {

        int[][] dp;

        public int rob(int[] nums) {

            //能够访问nums.length这个位置
            dp = new int[nums.length+1][2];

            //每个位置依赖
            //false只能依赖true的index+1
            //true是max(true , index + 1) + (nums[index]+(false , index+1
            //因此，每个基础位置是

            //初始化
            dp[nums.length][0] = 0;
            dp[nums.length][1] = 0;

            for(int j = nums.length-1 ; j >= 0 ; j --){

                dp[j][0] = dp[j+1][1];
                dp[j][1] = Math.max(dp[j+1][1],dp[j+1][0]+nums[j]);
            }


            return dp[0][1];
            //return dfs(nums,0,true);

        }

        public int dfs(int[] nums,int index , boolean canDo){

            int key = canDo?1:0;

            if(dp[index][key] != 0){
                return dp[index][key];
            }


            //终止条件
            if(index == nums.length){

                return 0;
            }

            //能够决定能偷
            int choice = Integer.MIN_VALUE;
            if(canDo){

                choice = nums[index] + dfs(nums,index+1,false);
            }

            int noChoice = dfs(nums,index+1,true);


            dp[index][key] = Math.max(choice,noChoice);

            return Math.max(choice,noChoice);
        }



    }

}
