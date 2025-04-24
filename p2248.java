import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class p2248 {

    /**
     * 给你一个二维整数数组 nums ，其中 nums[i] 是由 不同 正整数组成的一个非空数组，按 升序排列 返回一个数组，数组中的每个元素在 nums 所有数组 中都出现过。
     */

    class Solution {

        public static int MAXN = 1001;
        public static int[] count = new int[MAXN];

        public List<Integer> intersection(int[][] nums) {

            List<Integer> res = new ArrayList<>();
            Arrays.fill(count , 0);
            for(int[] n : nums)
                for(int a : n)
                    count[a]++;

            for(int i = 0 ; i < MAXN ; i ++){

                if(count[i] == nums.length)
                    res.add(i);
            }

            return res;
        }
    }
}
