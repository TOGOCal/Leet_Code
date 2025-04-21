import java.util.Arrays;

public class p2033 {

    /**
     * 给你一个大小为 m x n 的二维整数网格 grid 和一个整数 x 。每一次操作，你可以对 grid 中的任一元素 加 x 或 减 x 。
     *
     * 单值网格 是全部元素都相等的网格。
     *
     * 返回使网格化为单值网格所需的 最小 操作数。如果不能，返回 -1 。
     */

    class Solution {

        public static int MAXN = 1_00_001;
        public static int[] arr = new int[MAXN];
        public static int realLength;

        public int minOperations(int[][] grid, int x) {

            int index = 0;

            for(int[] g : grid)
                for(int a : g)
                    arr[index ++] = a;

            realLength = index;

            //检查是否任意两个书的插值都是x的倍数
            int value = arr[0];
            for(int i = 1; i < realLength ; i ++)
                if(abs(value - arr[i]) % x != 0)
                    return -1;


            Arrays.sort(arr , 0 , realLength);

            //找到中位数
            value = arr[realLength >> 1];

            int res = 0;
            for(int i = 0 ; i < realLength ; i ++)
                res += abs(value - arr[i]) / x;

            return res;
        }

        private int abs(int x){

            return x < 0 ? -x : x;
        }
    }
}
