import java.util.ArrayList;
import java.util.List;

public class p228 {

    /**
     * 给定一个  无重复元素 的 有序 整数数组 nums 。
     * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。
     * 也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
     * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
     * "a->b" ，如果 a != b
     * "a" ，如果 a == b
     */

    class Solution {
        public List<String> summaryRanges(int[] nums) {

            List<String> res = new ArrayList<>();
            if(nums == null || nums.length == 0){

                return res;
            }

            if(nums.length == 1){

                res.add(String.valueOf(nums[0]));
                return res;
            }

            int begin = nums[0];

            for(int i = 0 ; i < nums.length - 1 ; i++){

                if(nums[i] + 1 < nums[i+1]){

                    int end = nums[i];

                    if(end == begin){

                        res.add(String.valueOf(begin));
                    }else{

                        StringBuilder sb = new StringBuilder();
                        sb.append(begin).append("->").append(end);
                        res.add(sb.toString());
                    }

                    begin = nums[i+1];
                }
            }

            //之前被结尾了
            if(begin == nums[nums.length -1]){

                res.add(String.valueOf(nums[nums.length -1]));
            }else{


                StringBuilder sb = new StringBuilder();
                sb.append(begin).append("->").append(nums[nums.length-1]);
                res.add(sb.toString());

                //res.add(begin + "->" + nums[nums.length-1]);
            }

            return res;
        }
    }
}
