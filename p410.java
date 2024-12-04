import java.util.Arrays;

public class p410 {

    /**
     * 给定一个非负整数数组 nums 和一个整数 k ，你需要将这个数组分成 k 个非空的连续子数组，使得这 k 个子数组各自和的最大值 最小。
     *
     * 返回分割后最小的和的最大值。
     *
     * 子数组 是数组中连续的部份。
     */

    public static void main(String[] args) {

        int[] arr = {7,2,5,10,8};
        System.out.println(new Solution().splitArray(arr , 2));
    }

    static
    class Solution{

        //二分搜索
        public int splitArray(int[] nums, int k) {
           int left = 0;
           int right = Integer.MAX_VALUE;

           int res = 0;
           while(left <= right){

               int mid = left + ((right - left) >> 1);

               if(check(mid , nums , k)){

                   res = mid;
                   right = mid - 1;
               }else{

                   left = mid + 1;
               }
           }

           return res;
        }

        public boolean check(int capacity , int[] nums , int maxGroupNum){

            int sum = 0;
            for(int i = 0; i < nums.length ;i++){

                if(maxGroupNum == 0)
                    return false;

                sum += nums[i];
                if(sum > capacity){

                    sum = 0;
                    maxGroupNum --;


                    i--;
                }
            }

            return true;
        }
    }


    static
    //动态规划版本（记忆化搜索（优点问题，但是不知道问题是什么
    class Solution1 {

        public static final int MAXK = 51;
        public static final int MAX_INDEX = 1001;
        public static final int[][] dp = new int[MAXK][MAX_INDEX];
        public static final int[] copy = new int[MAX_INDEX];//用于消去数组中的0，0会干扰递归的判定
        public int splitArray(int[] nums, int k) {

            int length = 0;
            for(int num : nums)
                if(num != 0)
                    copy[length++] = num;

            int[] arr = new int[length];
            for(int i = 0 ; i< length ; i++)
                arr[i] = copy[i];

            int sum = 0;
            for(int num : nums)
                sum += num;

            int avg = sum/k;//和尽量往这个值靠齐

            for(int[] d:dp)
                Arrays.fill(d , -1);

            return maxMinRecursion(arr , k , 0 , avg);
        }

        public int maxMinRecursion(int[] num , int lastGroupNum , int index , int avg){


            if(index >= num.length)
                if(lastGroupNum == 0)
                    return Integer.MIN_VALUE;//这时一种可行的方案
                else
                    return Integer.MAX_VALUE;//没有分配完全，不是可行的方案


            if(lastGroupNum <= 0)
                return Integer.MAX_VALUE;//这是一种不可取的分类方式

            if(dp[lastGroupNum][index] != -1)
                return dp[lastGroupNum][index];

            int sum = 0;
            int i = index;
            for(; i < num.length ; i++){

                sum += num[i];
                if(sum > avg){
                    //开始决定是许纳泽较大还是较小，往下递归即可
                    sum -= num[i];
                    //i--;;//是加入了这个数才导致的溢出，所以返回到上一个数
                    break;
                }
            }

            int res;
            //需要得到最大值的最小
            if(i == num.length){//i不是因为超出了avg了才越界的，而是因为直接访问结束了

                res = Math.max(sum , maxMinRecursion(num , lastGroupNum - 1 , i , avg));
                dp[lastGroupNum][index] = res;
                return res;
            }

            //决定到底选不选这个位置
            //两种选择中选择较小值
            res =  Math.min(
                    Math.max(sum , maxMinRecursion(num , lastGroupNum -1 , i , avg)),//不选择i位置的值
                    Math.max(sum + num[i] , maxMinRecursion(num , lastGroupNum - 1 , i + 1 , avg))//选择i位置的值
            );
            dp[lastGroupNum][index] = res;
            return res;
        }
    }



//    class Solution {
//
//        /**
//         * 这k个子数组的和的平均值就是sum/k，如果存在比这个平均值大的组，就一定存在比这个平均值小的组合
//         */
//        public int splitArray(int[] nums, int k) {
//
//            int sum = 0;
//            for(int num : nums)
//                sum += num;
//
//            int avg = sum/k;//和尽量往这个值靠齐
//            return maxMinRecursion(nums , k , 0 , avg);
//        }
//
//        public int maxMinRecursion(int[] num , int lastGroupNum , int index , int avg){
//
//            if(index >= num.length)
//                if(lastGroupNum == 0)
//                    return Integer.MIN_VALUE;//这时一种可行的方案
//                else
//                    return Integer.MAX_VALUE;//没有分配完全，不是可行的方案
//
//
//            if(lastGroupNum <= 0)
//                return Integer.MAX_VALUE;//这是一种不可取的分类方式
//
//            int sum = 0;
//            int i = index;
//            for(; i < num.length ; i++){
//
//                sum += num[i];
//                if(sum > avg){
//                    //开始决定是许纳泽较大还是较小，往下递归即可
//                    sum -= num[i];
//                    //i--;;//是加入了这个数才导致的溢出，所以返回到上一个数
//                    break;
//                }
//            }
//
//            //需要得到最大值的最小
//            if(i == num.length){//i不是因为超出了avg了才越界的，而是因为直接访问结束了
//
//                return Math.max(sum , maxMinRecursion(num , lastGroupNum - 1 , i , avg));
//            }
//
//            //决定到底选不选这个位置
//            //两种选择中选择较小值
//            return Math.min(
//                    Math.max(sum , maxMinRecursion(num , lastGroupNum -1 , i , avg)),//不选择i位置的值
//                    Math.max(sum + num[i] , maxMinRecursion(num , lastGroupNum - 1 , i + 1 , avg))//选择i位置的值
//            );
//        }
//    }
}
