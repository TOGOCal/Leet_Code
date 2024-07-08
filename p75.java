public class p75 {

    /**
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     */

    //荷兰国旗问题
    class Solution {
        public void sortColors(int[] nums) {

            int p1 = -1;
            int p2 = nums.length;

            for( int i = 0; i < p2; i++) {

                if(nums[i] < 1){

                    p1++;
                    swap(nums, i, p1);
                }else if( nums[i] > 1){

                     p2--;
                    swap(nums, i, p2);
                    i--;
                }
            }
        }

        public void swap(int[] nums, int i, int j) {

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
