package from_500_to_600;

public class p540 {

    /**
     * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
     *
     * 请你找出并返回只出现一次的那个数。
     *
     * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
     */


    public static void main(String[] args) {

        System.out.println(new Solution().singleNonDuplicate(new int[]{3,3,7,7,10,11,11}));
    }


    static
    class Solution {
        public int singleNonDuplicate(int[] nums) {


            int left = 0;
            int right = nums.length;

            while(left <= right){

                int mid = (left + right) >> 1;
                if(nums[mid] != (mid - 1 >= 0 ? nums[mid - 1] : Integer.MIN_VALUE) && nums[mid] != (mid + 1 < nums.length ? nums[mid + 1] : Integer.MIN_VALUE)){

                    return nums[mid];
                }
                if(nums[mid] == nums[mid ^ 1]){

                    left = mid + 1;
                }else{

                    right = mid - 1;
                }
            }

            return nums[left];
        }
    }

}
