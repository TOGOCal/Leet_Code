package from_300_to_400;

public class p330 {

    /**
     * 给定一个已排序的正整数数组 nums ，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。
     *
     * 请返回 满足上述要求的最少需要补充的数字个数 。
     */

    class Solution {
        //贪心策略：如果x在数组中，且1 - （x-1）的范围可以被覆盖，则1 - （2x -1）的范围也可以被覆盖

        public int minPatches(int[] nums, int n) {

            long range = 0;//当前能覆盖的范围是从0到1


            int i = 0;
            int addCount = 0;//需要
            while(range < n){

                //还可能通过加入数组中元素的方法来进行范围扩充
                if(i < nums.length && nums[i] <= range + 1){

                    //能够被直接扩容
                    range += nums[i];
                    i++;
                }else{
                    //需要手动一添加数字
                    addCount ++;//添加数字：range + 1
                    range = (range << 1) + 1;
                }
            }

            return addCount;
        }
    }
}
