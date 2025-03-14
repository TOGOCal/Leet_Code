import java.util.Arrays;

public class p2551 {

    /**
     * 你有 k 个背包。给你一个下标从 0 开始的整数数组 weights ，其中 weights[i] 是第 i 个珠子的重量。同时给你整数 k 。
     *
     * 请你按照如下规则将所有的珠子放进 k 个背包。
     *
     * 没有背包是空的。
     * 如果第 i 个珠子和第 j 个珠子在同一个背包里，那么下标在 i 到 j 之间的所有珠子都必须在这同一个背包中。
     * 如果一个背包有下标从 i 到 j 的所有珠子，那么这个背包的价格是 weights[i] + weights[j] 。
     * 一个珠子分配方案的 分数 是所有 k 个背包的价格之和。
     *
     * 请你返回所有分配方案中，最大分数 与 最小分数 的 差值 为多少。
     */

    class Solution {

        public static int MAXN = 100001;
        public static int[] arr = new int[MAXN];

        public long putMarbles(int[] weights, int k) {

            //计算断点的两侧情况
            for(int i = 0 ; i < weights.length - 1 ; i ++){

                arr[i] = weights[i] + weights[i + 1];
            }

            Arrays.sort(arr , 0 , weights.length - 1);

            long res = 0;
            //选出k-1个间隙
            int l = weights.length - 1;
            for(int i = 0 ; i < k - 1; i ++){

                res += arr[l - 1 - i] - arr[i];
            }

            return res;
        }
    }
}
