import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class p47 {

    /**
     * 在p46的基础上，只需要进行去重操作即可
     */

    class Solution {
        List<List<Integer>> res;

        public List<List<Integer>> permuteUnique(int[] nums) {

            res = new ArrayList<>();
            method(nums,0);
            return res;
        }

        public void method(int[] nums, int index)  {

            if(index ==nums.length) {

                List<Integer> list = new ArrayList<>();

                for(int i : nums) {

                    list.add(i);
                }
                res.add(list);
                return ;
            }

            //HashSet<Integer> flag = new HashSet<>();

            for(int i = index; i < nums.length; i++) {

                if(isExist(nums,index,i)) {

                    continue;
                }
                swap(nums,index,i);
                method(nums,index+1);
                swap(nums,index,i);

                //flag.add(nums[i]);

            }


        }

        public boolean isExist(int[] nums , int index , int j) {

            for(int i = index; i < j; i++){

                if(nums[i] == nums[j]){

                    return true;
                }
            }

            return false;
        }

        public void swap (int[] nums, int i, int j) {

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }


    }

}
