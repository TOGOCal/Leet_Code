import java.util.Arrays;

public class p576 {

    /**
     * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
     *
     * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7 取余 后的结果。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().findPaths(2, 2, 2, 0, 0));
    }


    class Solution2 {

        public static int MAXN = 51;
        public static int MOD = 1_000_000_000 + 7;
        public static int[][] dp = new int[MAXN][MAXN];
        public static int[][] dpNew = new int[MAXN][MAXN];
        //走i步到达j，k位置的路径数
        public static int[] go = new int[]{-1 , 0 , 1 , 0 , -1};

        public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {

            for(int[] d: dp)
                Arrays.fill(d , 0);
            for(int[] d: dpNew)
                Arrays.fill(d , 0);

            dp[startRow][startColumn] = 1;

            int res = 0;
            for(int move = 0 ; move < maxMove ; move ++){

                for(int j = 0 ; j < m ; j ++){

                    for(int k = 0 ; k < n ; k ++){

                        long count = dp[j][k];
                        for(int i = 0 ; i < 4 ; i ++){

                            int j1 = j + go[i];
                            int k1 = k + go[i + 1];
                            if(0 <= j1 && j1 < m && 0 <= k1 && k1 < n){

                                dpNew[j1][k1] = (int)((dpNew[j1][k1] + count) % MOD);
                            }else{

                                res = (int)((res + count) % MOD);
                            }
                        }
                    }
                }

                int[][] temp = dpNew;
                dpNew = dp;
                dp = temp;
                for(int[] d : dpNew)
                    Arrays.fill(d , 0);
            }

            return res;
        }
    }


    static
    class Solution {

        public static int MAXN = 51;
        public static int MOD = 1_000_000_000 + 7;
        public static int[][][] dp = new int[MAXN][MAXN][MAXN];
        //走i步到达j，k位置的路径数
        public static int[] go = new int[]{-1 , 0 , 1 , 0 , -1};

        public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
            for(int[][] D : dp)
                for(int[] d : D)
                    Arrays.fill(d , 0);

            dp[0][startRow][startColumn] = 1;

            int res = 0;
            for(int move = 0 ; move < maxMove ; move ++){

                int[][] temp = dp[move];
                int[][] next = dp[move +1];
                for(int j = 0 ; j < m ; j ++){

                    int[] t = temp[j];
                    for(int k = 0 ; k < n ; k ++){

                        long count = t[k];
                        for(int i = 0 ; i < 4 ; i ++){

                            int j1 = j + go[i];
                            int k1 = k + go[i + 1];
                            if(0 <= j1 && j1 < m && 0 <= k1 && k1 < n){

                                next[j1][k1] = (int)((next[j1][k1] + count) % MOD);
                            }else{

                                res = (int)((res + count) % MOD);
                            }
                        }
                    }
                }
            }

            return res;
        }
    }
}
