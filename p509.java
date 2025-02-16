package from_500_to_600;

public class p509 {

    /**
     * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     *
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给定 n ，请计算 F(n) 。
     */

    class Solution {

        public static int MAXN = 31;
        public static int[] res = new int[MAXN];
        static {

            res[0] = 0;
            res[1] = 1;

            int[][] r = new int[][]{{1,0}};
            int[][] a = new int[][]{
                    {1,1},
                    {1,0}
            };

            for(int i = 2 ; i < MAXN ; i++){

                r = matrixMulti(r ,a);
                res[i] = r[0][0];
            }
        }

        public static int[][] matrixMulti(int[][] a , int[][] b){

            int m = a.length;
            int n = b[0].length;
            int same = b.length;
            if(same != a[0].length)
                throw new ArithmeticException();

            int[][] r = new int[m][n];

            for(int i = 0 ; i < m ; i ++)
                for(int s = 0 ; s < same ; s ++){

                    int temp = a[i][s];
                    for(int j = 0 ; j < n ; j ++)
                        r[i][j] += temp * b[s][j];
                }

            return r;
        }

        public int fib(int n) {

            return res[n];
        }
    }
}
