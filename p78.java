import java.util.ArrayList;
import java.util.List;

public class p78 {

    /**
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的
     * 子集
     * （幂集）。
     *
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     */

    class Solution {
        public List<List<Integer>> subsets(int[] nums) {

            List<List<Integer>> res = new ArrayList<>();

            dfs(res,new ArrayList<>(),0,nums);


            return res;
        }

        public void dfs(List<List<Integer>> res,
                    List<Integer> path,
                        int index , int[] nums){

            if(index == nums.length){

                res.add(new ArrayList<>(path));
                return ;
            }

            //不要这个位置字符的情况
            dfs(res,path,index+1,nums);

            path.add(nums[index]);
            dfs(res,path,index+1,nums);
            path.removeLast();
        }
    }
}
