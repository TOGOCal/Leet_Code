public class p338 {

    /**
     * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
     */

    class Solution {

        public static int MAXN = 100001;
        public static int[] mem = new int[MAXN];

        public int[] countBits(int n) {

            int[] res = new int[n + 1];
            for (int i = 0; i <= n; i++)
                res[i] = bitCountDP(i);
            return res;
        }

        //动态规划版本
        public static int bitCountDP(int i){

            if(i == 0){

                return 0;
            }

            if(mem[i] != 0){

                return mem[i];
            }

            int lastOne = i & (-i);

            mem[i] = bitCountDP(i - lastOne) + 1;

            return mem[i];
        }

//        public static int bitCount(int i) {
//
//            int c = i;
//
//            if(mem[i] != -1){
//
//                return mem[i];
//            }
//
//            // HD, Figure 5-2
//            i = i - ((i >>> 1) & 0x55555555);
//            i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
//            i = (i + (i >>> 4)) & 0x0f0f0f0f;
//            i = i + (i >>> 8);
//            i = i + (i >>> 16);
//            mem[c] = i & 0x3f;
//            return i & 0x3f;
//        }


    }
}
