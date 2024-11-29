public class p413 {

    /**
     * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
     *
     * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
     * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
     *
     * 子数组 是数组中的一个连续序列。
     */


    public static void main(String[] args) {

        int[] arr = new int[]{1,2,3,8,9,10};
        System.out.println(new Solution().numberOfArithmeticSlices(arr));
    }

    static
    class Solution {
        public int numberOfArithmeticSlices(int[] nums) {

            if(nums.length < 3)
                return 0;

            Integer sub = null;

            int length = 0;
            int res = 0;
            for(int i = 0 ; i < nums.length - 1 ; i++){

                length ++;
                if(sub == null){
                    sub = nums[i + 1] - nums[i];//得到差
                    continue;
                }

                if(nums[i] + sub != nums[i+ 1]){
                    res += getLength(length);
                    length = 1;
                    sub = nums[i + 1] - nums[i];
                }
            }

            res += getLength(length + 1);

            return res;
        }


        //一个数组长度大于3的连续子数组长度
        public int getLength(int n){
            if(n < 3)
                return 0;
            /*
            数组本生一个
            数组长度为3的时候有n - 2个
            首相1 ， 末项 n - 2  | (1 + (n - 2) * (n -2) )/2
             */

            return ((n - 1) * (n - 2)) >> 1;
        }
    }
}
