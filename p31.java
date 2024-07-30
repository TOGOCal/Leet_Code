import java.util.Scanner;

public class p31 {

    /**
     *
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        int[] nums = new int[num];

        for(int i = 0; i < num; i++){

            nums[i] = s.nextInt();
        }

        new Solution().nextPermutation(nums);

        s.close();
    }

    static class Solution {
        public void nextPermutation(int[] nums) {

            if(nums == null || nums.length == 1){
                return;
            }

            /**
             * 核心思想：
             * 每一个位置都可以被任意后面的位置替换
             */
            int i;
            for(i = nums.length-2; i >= 0; i--){

                if(nums[i] < nums[i+1]){
                    break;
                }
            }

            if(i < 0){
                //说明已经是最大字典序，直接逆序数组即可
                for(int j = 0; j < nums.length/2; j++){

                    int temp = nums[j];
                    nums[j] = nums[nums.length-1-j];
                    nums[nums.length-1-j] = temp;
                }
                return ;
            }

            int biggerMinIndex = i+1;
            for(int j = i+1 ; j< nums.length-1; j++){

                if(nums[j] < nums[j+1]){

                    i = j;
                }else if(nums[i] < nums[j] &&  nums[j] <= nums[biggerMinIndex]){

                    biggerMinIndex = j;
                }
            }//找到最后一个比后面的小的位置i//说明后面都是按照字典序排好队的，所以直接替换排序就行了

            if(nums[i] < nums[nums.length-1] && nums[nums.length-1] <= nums[biggerMinIndex]){

                biggerMinIndex = nums.length-1;
            }//之前的循环没有判断最后一个位置

            //找到之后恰好大于i位置的值

            swap(nums, i , biggerMinIndex);

            //逆序i+1到nums.length-1

            int len = nums.length-1-i;
            for(int j = 0; j < len/2; j++){

                swap(nums, i+1+j, nums.length-1-j);
            }
        }


        public void swap(int[] nums, int i, int j){

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }

}
