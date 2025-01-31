package from_500_to_600;

public class p528 {

    /**
     * 给你一个 下标从 0 开始 的正整数数组 w ，其中 w[i] 代表第 i 个下标的权重。
     *
     * 请你实现一个函数 pickIndex ，它可以 随机地 从范围 [0, w.length - 1] 内（含 0 和 w.length - 1）选出并返回一个下标。选取下标 i 的 概率 为 w[i] / sum(w) 。
     *
     * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
     */

    class Solution {

        public static int MAXN = 10001;
        public static int[] preSum = new int[MAXN];
        public static int realLength;

        public Solution(int[] w) {

            realLength = w.length;
            preSum[0] = 0;
            for(int i = 0 ; i < realLength ; i ++)
                preSum[i + 1] = preSum[i] + w[i];
        }

        public int pickIndex() {

            int a = (int)(Math.random() * preSum[realLength]);
            return binarySearch(a);
        }

        private int binarySearch(int i){

            int left = 0;
            int right = realLength - 1;

            int res = 0;
            while(left <= right){

                int mid = (left + right) >> 1;
                if(preSum[mid] <= i){

                    res = mid;
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }

            return res;
        }
    }
}
