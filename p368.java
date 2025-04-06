package from_300_to_400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p368 {

    /**
     * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
     * answer[i] % answer[j] == 0 ，或
     * answer[j] % answer[i] == 0
     * 如果存在多个有效解子集，返回其中任何一个均可。
     */


    public static void main(String[] args) {

        System.out.println(new Solution().largestDivisibleSubset(new int[]{4, 8, 10, 240}));
    }


    static
    class Solution {

        public static int MAXN = 1001;
        public static int[] dp = new int[MAXN];//必须选择i位置，则可选前面的所有，能够得到的最大子集大小
        public static int length;

        public List<Integer> largestDivisibleSubset(int[] nums) {

            length = nums.length;
            Arrays.sort(nums);

            dp[0] = 1;
            for(int i = 1 ; i < length ; i ++){

                int value = nums[i];
                int max = 0;
                for(int j = i - 1 ; j >= 0 ; j --){

                    //由于排序，所以前面的一定比当前数小
                    if(value % nums[j] == 0)
                        max = Math.max(max , dp[j]);

                }

                dp[i] = max + 1;
            }

            int max = 0;
            int maxIndex = 0;
            for(int i = 0 ; i < length ; i ++){

                if(dp[i] > max){

                    max = dp[i];
                    maxIndex = i;
                }
            }

            List<Integer> res = new ArrayList<>();

            while (maxIndex >= 0){

                int v = nums[maxIndex];
                int index = maxIndex;
                res.add(v);
                do maxIndex--;
                while (maxIndex >= 0 &&
                        !(v % nums[maxIndex] == 0 && dp[maxIndex] == dp[index] - 1));
            }

            return res;
        }

    }
}
