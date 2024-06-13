import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class p90 {

    /**
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的
     * 子集
     * （幂集）。
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int[] nums = new int[s.nextInt()];

        for(int i = 0 ; i < nums.length ; i++){

            nums[i] = s.nextInt();
        }

        new Solution().subsetsWithDup(nums);

        s.close();
    }

    static class Solution {
        /**
         * 思想：
         * 1.先进性排序
         * 选择结果的第几个位置选哪个
         */

        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsetsWithDup(int[] nums) {

            Arrays.sort(nums);
            backtrack(nums,  new ArrayList<>() , 0);
            return res;
        }

        public void backtrack(int[] nums,List<Integer> path , int preIndex) {
            //index 代表path的第几个位置 ,每栈的依据是正在对第几个位置进行选择

            res.add(new ArrayList<>(path));

            for(int i = preIndex;i< nums.length;i++){

                //选择
                if(i != preIndex){

                    while(i < nums.length && nums[i] == nums[i - 1]){

                        i++;
                    }

                }

                if(i!= nums.length){

                    path.add(nums[i]);
                    backtrack(nums, path, i + 1);
                    path.removeLast();
                    //不选择
                }

            }

        }

//        public void backtrack(int[] nums,int index,  List<Integer> path) {
//
//            if(index == nums.length){
//
//                res.add(new ArrayList<>(path));
//                return;
//            }
//
//            backtrack(nums, index + 1, path);//不要这个
//
//            path.add(nums[index]);
//            backtrack(nums, index + 1, path);//要这个
//            path.removeLast();
//
//        }
    }
}
