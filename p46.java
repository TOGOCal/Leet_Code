import java.util.ArrayList;
import java.util.List;

public class p46 {


    /**
     * 解题思想：
     * 每个数字都有可能到达当前的位置
     */
    class Solution {
        List<List<Integer>> res;

        public List<List<Integer>> permute(int[] nums) {

            res = new ArrayList<>();
            method(nums,0);
            return res;
        }

        public void method(int[] nums, int index)  {

            if(index ==nums.length) {

                List<Integer> list = new ArrayList<>();

                for(int i = 0; i < nums.length; i++) {

                    list.add(nums[i]);
                }
                res.add(list);
                return ;
            }


            for(int i = index; i < nums.length; i++) {

                swap(nums,index,i);
                method(nums,index+1);
                swap(nums,index,i);
            }


        }

        public void swap (int[] nums, int i, int j) {

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }


    }


}
