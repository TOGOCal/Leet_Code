import java.util.Arrays;

public class p2658 {

    /**
     * 给你一个下标从 0 开始大小为 m x n 的二维整数数组 grid ，其中下标在 (r, c) 处的整数表示：
     *
     * 如果 grid[r][c] = 0 ，那么它是一块 陆地 。
     * 如果 grid[r][c] > 0 ，那么它是一块 水域 ，且包含 grid[r][c] 条鱼。
     * 一位渔夫可以从任意 水域 格子 (r, c) 出发，然后执行以下操作任意次：
     *
     * 捕捞格子 (r, c) 处所有的鱼，或者
     * 移动到相邻的 水域 格子。
     * 请你返回渔夫最优策略下， 最多 可以捕捞多少条鱼。如果没有水域格子，请你返回 0 。
     *
     * 格子 (r, c) 相邻 的格子为 (r, c + 1) ，(r, c - 1) ，(r + 1, c) 和 (r - 1, c) ，前提是相邻格子在网格图内。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().findMaxFish(new int[][]{{0 , 4}}));
    }

    static
    class Solution {

        public static int MAXN = 10;
        public static int[] symbol = new int[MAXN * MAXN];//用于并查集
        public static int[] size = new int[MAXN * MAXN];

        public static int p(int i , int j){

            return i * MAXN + j;
        }

        public static int getSymbol(int i , int j){

            int realP = p(i , j);

            int cur = realP;
            while(symbol[cur] != cur){

                cur = symbol[cur];
            }

            while (realP != cur){

                int temp = symbol[realP];
                symbol[realP] = cur;
                realP = temp;
            }

            return cur;
        }

        public static void setSameSet(int i1 , int j1  , int i2 , int j2){

            int p1 = getSymbol(i1 , j1);
            int p2 = getSymbol(i2 , j2);

            if(p1 == p2)
                return;

            symbol[p1] = p2;
            size[p2] += size[p1];
        }

        public static int[] walk = new int[]{1 , 0 , -1 , 0 , 1};

        public int findMaxFish(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            for(int i = 0 ; i < symbol.length ; i++)
                symbol[i] = i;
            Arrays.fill(size , 0);

            for(int i = 0 ; i < m ; i ++)
                for(int j = 0 ; j < n ; j ++)
                    size[p(i , j)] = grid[i][j];


            for(int i = 0 ; i < m; i ++)
                for(int j = 0 ; j < n; j ++){

                    if(grid[i][j] == 0)
                        continue;
                    for(int k = 0 ; k < 4 ; k ++){

                        int x = i + walk[k];
                        int y = j + walk[k + 1];
                        if(x < 0 || x >= m ||
                                y < 0 || y >= n ||
                                grid[x][y] == 0)
                            continue;

                        setSameSet(i , j , x , y);
                    }
                }

            int res = 0;
            for(int i = 0 ; i < size.length; i ++)
                res = Math.max(res , size[i]);

            return res;
        }
    }
}
