import java.util.Arrays;

public class p2580 {

    /**
     * 给你一个二维整数数组 ranges ，其中 ranges[i] = [starti, endi] 表示 starti 到 endi 之间（包括二者）的所有整数都包含在第 i 个区间中。
     *
     * 你需要将 ranges 分成 两个 组（可以为空），满足：
     *
     * 每个区间只属于一个组。
     * 两个有 交集 的区间必须在 同一个 组内。
     * 如果两个区间有至少 一个 公共整数，那么这两个区间是 有交集 的。
     *
     * 比方说，区间 [1, 3] 和 [2, 5] 有交集，因为 2 和 3 在两个区间中都被包含。
     * 请你返回将 ranges 划分成两个组的 总方案数 。由于答案可能很大，将它对 109 + 7 取余 后返回。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().countWays(new int[][]{{0,0},{8,9},{12,13},{1,3}}));
    }


    class Solution2 {

        public static int mod = 1_000_000_000 + 7;
        public static int power(long a , int b){

            long res = 1;
            while (b > 0){

                if((b & 1) == 1)
                    res = (res * a) % mod;
                a = (a * a) % mod;
                b >>= 1;
            }
            return (int)res;
        }

        public int countWays(int[][] ranges) {

            Arrays.sort(ranges , (o1 , o2) -> {
                return o1[0] - o2[0];
            });

            int count = ranges.length;
            int[] temp = new int[2];
            temp[0] = Integer.MIN_VALUE;
            temp[1] = Integer.MIN_VALUE;


            for(int[] range : ranges){

                //合并
                if(range[0] <= temp[1]){

                    count--;
                    temp[1] = Math.max(temp[1] , range[1]);
                }else{

                    temp[0] = range[0];
                    temp[1] = range[1];
                }
            }

            return power(2 , count);
        }


    }


    static
    class Solution {

        public static int MAXN = 1_00_000 + 1;
        public static int mod = 1_000_000_000 + 7;
        public static int[] multi = new int[MAXN];
        public static int[] inverse = new int[MAXN];
        static {

            multi[1] = 1;
            for(int i = 2 ; i < MAXN ; i ++)
                multi[i] = (int)(((long)multi[i - 1] * i) % mod);

            //求最大值的逆元
            inverse[MAXN - 1] = power(multi[MAXN - 1], mod - 2);

            for(int i = MAXN - 2 ; i >= 1 ; i --)
                inverse[i] = (int)(((long)(i + 1) * inverse[i + 1]) % mod);
        }

        //a的b次方，使用快速幂
        public static int power(long a , int b){

            long res = 1;
            while (b > 0){

                if((b & 1) == 1)
                    res = (res * a) % mod;
                a = (a * a) % mod;
                b >>= 1;
            }

            return (int)res;
        }

        public int countWays(int[][] ranges) {

            Arrays.sort(ranges , (o1 , o2) -> {
                return o1[0] - o2[0];
            });

            int count = ranges.length;
            int[] temp = new int[2];
            temp[0] = Integer.MIN_VALUE;
            temp[1] = Integer.MIN_VALUE;


            for(int[] range : ranges){

                //合并
                if(range[0] <= temp[1]){

                    count--;
                    temp[1] = Math.max(temp[1] , range[1]);
                }else{

                    temp[0] = range[0];
                    temp[1] = range[1];
                }
            }

            long res = 0;
            res += 2;//一个组一个都没有的方案有2个，或
            for(int i = 1 ; i <= count - 1 ; i ++)
                res = (res + C(count , i)) % mod;


            return (int)res;
        }


        //从n个中选m个
        public static int C(int n , int m) {

            return (int) (
                    (((long) multi[n] * inverse[n - m]) % mod
                    * inverse[m])
                            % mod);
        }

    }
}
