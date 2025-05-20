import java.util.Arrays;
import java.util.List;

public class p2811 {

    /**
     * 给你一个长度为 n 的数组 nums 和一个整数 m 。请你判断能否执行一系列操作，将数组拆分成 n 个 非空 数组。
     *
     * 一个数组被称为 好 的，如果：
     *
     * 子数组的长度为 1 ，或者
     * 子数组元素之和 大于或等于  m 。
     * 在每一步操作中，你可以选择一个 长度至少为 2 的现有数组（之前步骤的结果） 并将其拆分成 2 个子数组，而得到的 每个 子数组都需要是好的。
     *
     * 如果你可以将给定数组拆分成 n 个满足要求的数组，返回 true ；否则，返回 false 。
     */


    public static void main(String[] args) {

        System.out.println(new Solution().canSplitArray(Arrays.asList(2, 1, 1, 3) , 4));
    }


    //剥洋葱，每次一定剥掉首尾的某个元素
    //最后剩下一个长度为2的子数组
    //如果没有任何一个子数组的和符合要求，就不符合要求
    //如果有，则在每次剥洋葱的时候，大的那块剩下的一定包含该子数组，这样大的那块一定也是满足要求的
    class Solution1 {
        public boolean canSplitArray(List<Integer> nums, int m) {
            int n = nums.size();
            if (n <= 2) return true;
            for (int i = 1; i < n; i++)
                if (nums.get(i - 1) + nums.get(i) >= m)
                    return true;
            return false;
        }
    }


    static
    //区间通带规划，从i到j的区域最多能拆成几个部分（i，j均为有效位置）
    class Solution {
        public static int MAXN = 101;
        public static boolean[][] dp = new boolean[MAXN][MAXN];
        public static int realLength ;

        public static int[] preSum = new int[MAXN];

        public static int getSum(int i , int j){

            return i == 0 ? preSum[j] : preSum[j] - preSum[i - 1];
        }

        public boolean canSplitArray(List<Integer> nums, int m) {

            for(boolean[] d: dp)
                Arrays.fill(d , false);

            realLength = nums.size();

            for(int i = 0 ; i < realLength ; i ++){

                dp[i][i] = true;//一个长度一定能完全切分
                if(i != realLength - 1)
                    //如果大小为2，则一定可以拆分成两个部分
                    dp[i][i + 1] = true;//两个长度一定能完全切分
            }
            preSum[0] = nums.getFirst();
            for(int i = 1 ; i < realLength ; i++)
                preSum[i] = preSum[i - 1] + nums.get(i);


            //长度为1，2的情况已经分析了，现在从3开始
            for(int length = 3 ; length <= realLength ; length ++){
                for(int begin = 0 ; begin + length - 1 < realLength ; begin++){

                    int end = begin + length - 1;
                    for(int part = begin ; part < end ; part++){

                        if((part == begin && getSum(part + 1 , end) >= m)
                                || (part == end - 1 && getSum(begin , part) >= m)
                                || getSum(begin , part) >= m && getSum(part + 1 , end) >= m)
                            dp[begin][end] |= (dp[begin][part] && dp[part + 1][end]);
                    }
                }
            }

            for(int i = 0 ; i < realLength ; i ++){

                for(int j = i + 2;  j < realLength ; j ++){
                    //最多可以拆成几个部分
                    for(int part = i ; part < j ; part ++){

                        //可以拆分成两个部分
                        if((part == i && getSum(part + 1 , j) >= m)
                        || (part == j - 1 && getSum(i , part) >= m)
                        || getSum(i , part) >= m && getSum(part + 1 , j) >= m)
                            dp[i][j] |= (dp[i][part] && dp[part + 1][j]);
                    }
                }
            }

            return dp[0][realLength - 1];
        }
    }
}
