import java.util.Arrays;

public class p994 {

    /**
     * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
     *
     * 值 0 代表空单元格；
     * 值 1 代表新鲜橘子；
     * 值 2 代表腐烂的橘子。
     * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
     *
     * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}}));
    }



    static
    class Solution {

        public static int MAXN = 10 | 1;
        public static int[] queue = new int[MAXN * MAXN];
        public static int top,end;
        public static boolean[] visit = new boolean[MAXN * MAXN];

        public static int[] move = new int[]{-1 , 0 , 1 , 0 , -1};

        Solution(){
            top = end = 0;
            Arrays.fill(visit , false);
        }

        public static void add(int v){

            queue[top++] = v;
        }

        public static int out(){

            return queue[end++];
        }

        public static boolean isEmpty(){

            return end == top;
        }


        public static int m,n;

        public static int change(int i , int j){

            return i * n + j;
        }

        public int orangesRotting(int[][] grid) {

            m = grid.length;
            n = grid[0].length;

            int thisLevelEnd = -1;
            int nextLevelEnd = -1;
            for(int i = 0 ; i < m ; i ++)
                for(int j = 0 ; j < n ; j ++)
                    if(grid[i][j] == 2){

                        thisLevelEnd = change(i, j);
                        add(thisLevelEnd);
                        visit[thisLevelEnd] = true;
                    }


            int level = -1;
            while (!isEmpty()){

                int temp = out();
                //上下左右
                int i = temp / n;
                int j = temp % n;
                grid[i][j] = 2;

                for(int a = 0 ; a < 4 ; a ++){

                    int x = i + move[a];
                    int y = j + move[a + 1];

                    int p = change(x , y);
                    if(0 <= x && x < m &&
                    0 <= y && y < n
                            && (grid[x][y] == 1)
                    && !visit[p]){

                        nextLevelEnd = p;
                        visit[p] = true;
                        add(p);
                    }
                }

                if(temp == thisLevelEnd){

                    level++;
                    thisLevelEnd = nextLevelEnd;
                }
            }

            for(int i = 0 ; i < m ; i ++){

                for(int j = 0 ; j < n ; j ++)
                    if(grid[i][j] == 1)
                        return -1;
            }

            return Math.max(level , 0);
        }
    }
}
