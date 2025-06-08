public class p462 {

    /**
     * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最小操作数。
     *
     * 在一次操作中，你可以使数组中的一个元素加 1 或者减 1 。
     *
     * 测试用例经过设计以使答案在 32 位 整数范围内。
     */

    class Solution {

        //找到中位数
        public int minMoves2(int[] nums) {

            int choose = nums.length >> 1;
            choose = quickChoose(choose , nums , 0 , nums.length - 1);
            int res = 0;
            for(int n : nums)
                res += Math.abs(choose - n);
            return res;
        }

        public int quickChoose(int index , int[] nums , int left , int right){

            if(left == right)
                return nums[left];

            int p1 = left - 1;
            int p2 = right + 1;
            int random = nums[(int)(Math.random() * (right - left + 1) + left)];
            for(int i = left ; i < p2 ; i ++){

                if(nums[i] < random){

                    p1++;
                    swap(nums , i , p1);
                }else if(random < nums[i]){

                    p2--;
                    swap(nums , i , p2);
                    i --;
                }
            }

            if(p1 + 1 <= index && index <= p2 - 1)
                return random;
            if(index <= p1)
                return quickChoose(index , nums , left , p1);
            else //(p2 <= index)
                return quickChoose(index , nums , p2 , right);
        }

        private void swap(int[] nums , int a , int b){

            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }
}
