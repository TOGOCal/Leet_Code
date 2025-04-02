public class p2873 {

    /**
     * 给你一个下标从 0 开始的整数数组 nums 。
     *
     * 请你从所有满足 i < j < k 的下标三元组 (i, j, k) 中，找出并返回下标三元组的最大值。如果所有满足条件的三元组的值都是负数，则返回 0 。
     *
     * 下标三元组 (i, j, k) 的值等于 (nums[i] - nums[j]) * nums[k] 。
     */

    /**
     * 方法2：
     * 不使用线段树，使用预处理数组
     */
    class Solution {
        public static int MAXN = 1_000_001;
        public static int[] leftMax = new int[MAXN];
        public static int[] rightMax = new int[MAXN];
        public static int length;

        private static void init(int[] num){

            length = num.length;
            leftMax[0] = Integer.MIN_VALUE;
            for(int i = 1 ; i <= length - 2 ; i ++)
                leftMax[i] = Math.max(leftMax[i - 1] , num[i - 1]);
            rightMax[length - 1] = Integer.MIN_VALUE;
            for(int i = length - 2 ; i >= 1; i--)
                rightMax[i] = Math.max(rightMax[i + 1], num[i + 1]);
        }

        public long maximumTripletValue(int[] nums) {

            init(nums);
            long ans = Long.MIN_VALUE;
            for(int i = 1 ; i <= length - 2 ; i ++){

                ans = Math.max(ans ,
                        ((long)leftMax[i] - nums[i]) * rightMax[i]);
            }

            return Math.max(ans , 0);
        }
    }

    /**
     * 思路：（我的），时间复杂度nlogn
     * 枚举j的位置，寻找两边的最大值
     * 由于题目给定所有值都是正数，所以不用管
     */
    class Solution1 {

        public static int MAXN = 1_000_001;
        public static int[] max = new int[MAXN << 2];
        public static int length;

        private static void init(int[] arr){

            initRecursion(arr ,
                    0 , arr.length - 1 , 1);
        }

        private static void initRecursion(int[] arr ,
                                          int nowLeft , int nowRight , int treeIndex){

            if(nowLeft == nowRight){

                max[treeIndex] = arr[nowLeft];
                return;
            }

            int mid = getMid(nowLeft , nowRight);
            initRecursion(arr ,
                    nowLeft , mid , treeIndex << 1);
            initRecursion(arr ,
                    mid + 1  , nowRight , treeIndex << 1 | 1);

            max[treeIndex] = Math.max(max[treeIndex << 1] , max[treeIndex << 1 | 1]);
        }

        private static int max(int left , int right){

            return getMax(left , right ,
                    0 , length - 1 , 1);
        }

        private static int getMax(int left , int right ,
                                  int nowLeft , int nowRight , int treeIndex){

            if(left <= nowLeft && nowRight <= right)
                return max[treeIndex];

            int mid = getMid(nowLeft , nowRight);
            int ans = Integer.MIN_VALUE;
            if(left <= mid)
                ans = Math.max(ans , getMax(left,  right ,
                        nowLeft , mid , treeIndex << 1));
            if(mid < right)
                ans = Math.max(ans , getMax(left , right , mid + 1 , nowRight , treeIndex << 1 | 1));

            return ans;
        }

        private static int getMid(int left , int right){

            return (left + right) >> 1;
        }

        public long maximumTripletValue(int[] nums) {

            init(nums);
            length = nums.length;
            long ans = Long.MIN_VALUE;

            for(int i = 1 ; i <= length - 2 ; i ++){

                int leftMax = max(0 , i - 1);
                int rightMax = max(i + 1 , length - 1);
                ans = Math.max(ans ,
                        ((long)leftMax - nums[i]) * rightMax);
            }

            return Math.max(ans , 0);
        }
    }
}
