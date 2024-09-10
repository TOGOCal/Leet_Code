public class p215 {

    /**
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     *
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */



    class Solution {
        public int findKthLargest(int[] nums, int k) {


            return quickSelect(nums, 0, nums.length - 1, nums.length - k);
        }


        public int quickSelect(int[] nums, int left, int right, int k){

            if(left == right)
                return nums[left];


            int random = nums[ (int)(Math.random() * (right - left + 1) + left) ];

            int p1 = left - 1 , p2 = right + 1;

            for(int i = left ; i < p2 ; i++){

                if(nums[i] < random){
                    p1++;
                    swap(nums, i, p1);
                }else if(nums[i] > random) {

                    p2--;
                    swap(nums, i, p2);
                    i--;
                }
            }


            //区间划分：left - p1 , p1 + 1) - (p2 -1, p2 - right
            if(k <= p1 && k >= left)
                return quickSelect(nums , left, p1, k);

            //quickSelect(nums, left, p1, k);

            if((p1 + 1) <= k && k <= p2 - 1)
                return nums[k];


            //quickSelect(nums, p2, right, k);
            return quickSelect(nums, p2, right, k);

        }

        public void swap(int[] nums, int i, int j){

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
