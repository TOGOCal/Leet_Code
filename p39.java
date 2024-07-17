import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p39 {

    /**
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
     * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
     * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * candidates 中的 同一个 数字可以 无限制重复被选取 。
     * 如果至少一个数字的被选数量不同，则两种组合是不同的。
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     */


    //方法1：暴力递归
    class Solution {

        List<List<Integer>> res;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {

            res = new ArrayList<>();

            //保证排序
            //Arrays.sort(candidates);

            //从大到小遍历数组
            int index = 0;

            addSituation(candidates, target, new ArrayList<>(), index);

            return res;
        }

        public void addSituation(int[] candidates, int target,  List<Integer> situation, int index) {

            if(target == 0) {

                res.add(new ArrayList<>(situation));
                return;
            }

            if(index == candidates.length){

                return;
            }

            //一个都不选的情况
            addSituation(candidates, target, situation, index + 1);

            if(target >= candidates[index]){

                situation.add(candidates[index]);
                addSituation(candidates, target - candidates[index], situation, index);
                situation.removeLast();
            }

        }
    }

}
