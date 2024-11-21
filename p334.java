import java.util.TreeSet;

public class p334 {

    /**
     * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
     *
     * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
     */


    public static void main(String[] args) {

        int[] nums = new int[]{5,4,3,2,1};
        System.out.println(new Solution().increasingTriplet(nums));
    }

    //方法3：贪心
    class Solution3 {

        /*
        贪心的思路其实就是使得前两个数在维持对应秩序的情况下还能尽量小
         */

        public boolean increasingTriplet(int[] nums) {

            if(nums == null || nums.length < 3)
                return false;

            int small = nums[0];

            int mid = Integer.MAX_VALUE;

//            for(int i = 1 ; i < nums.length ; i++){
//
//                if(nums[i] > mid)
//                    return true;
//                else if(nums[i] > small)
//                    mid = nums[i];//能够将mid比那的更小
//                else
//                    small = nums[i];//将最小的变得更小
//            }

            for(int num : nums){

                if(num > mid)
                    return true;
                else if(num > small)
                    mid = num;//能够将mid比那的更小
                else
                    small = num;//将最小的变得更小
            }

            return false;
        }
    }

    static//方法2：改进，时间复杂度n
    class Solution {

        public static final int MAXN = 5 * 100000 + 1;
        public static final boolean[] beforeHaveSmall = new boolean[MAXN];
        public static final boolean[] afterHaveBig = new boolean[MAXN];
        public static int length;

        public boolean increasingTriplet(int[] nums) {

            length = nums.length;
            int min = Integer.MAX_VALUE;
            for(int i = 0 ; i < length ; i ++){

                int num = nums[i];
                if(min < num)
                    beforeHaveSmall[i] = true;
                else{
                    beforeHaveSmall[i] = false;
                    min = num;
                }
            }

            int max =Integer.MIN_VALUE;
            for(int i = length - 1; i >= 0; i--){

                int num = nums[i];
                if(max > num)
                    afterHaveBig[i] = true;
                else{

                    afterHaveBig[i] = false;
                    max = num;
                }

                if(afterHaveBig[i] && beforeHaveSmall[i])
                    return true;
            }

//            for(int i = 0 ; i < length ; i++)
//                if(beforeHaveSmall[i] && afterHaveBig[i])
//                    return true;

            return false;
        }
    }

    static//方法1，时间复杂度nlogn
    class Solution2 {
        //思路：到某个位置之后，重看前面有没有比这个数字小的数字，后面有没有比这个数字大的数字
        public static final int MAXN = 5 * 100000 + 1;
        public static final boolean[] beforeHaveSmall = new boolean[MAXN];
        public static final boolean[] afterHaveBig = new boolean[MAXN];
        public static int length;

        public boolean increasingTriplet(int[] nums) {

            length = nums.length;

            TreeSet<Integer> set = new TreeSet<>();
            for(int i = 0 ; i< length ; i++){

                int num = nums[i];
                if(num == Integer.MIN_VALUE)
                    beforeHaveSmall[i] = false;
                else beforeHaveSmall[i] = set.floor(num - 1) != null;

                set.add(num);
            }

            set.clear();
            for(int i = length - 1 ; i >= 0 ;i--){

                int num = nums[i];
                if(num == Integer.MAX_VALUE)
                    afterHaveBig[i] = false;
                else afterHaveBig[i] = set.ceiling(num + 1) != null;

                set.add(num);
            }

            for(int i = 0 ; i < length ; i++)
                if(beforeHaveSmall[i] && afterHaveBig[i])
                    return true;

            return false;
        }
    }
}
