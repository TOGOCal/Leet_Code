import java.util.Arrays;

public class p827 {

    /**
     * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
     *
     * 返回执行此操作后，grid 中最大的岛屿面积是多少？
     *
     * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().largestIsland(new int[][]{{1 , 0}, {0 , 1}}));
    }


    static
    class Solution {

        public static int MAXN = 500 + 1;
        public static int n;
        public static int p(int i , int j){

            return i * n + j;
        }

        //并查集
        public static int[] symbol = new int[MAXN * MAXN];
        public static int[] size = new int[MAXN * MAXN];

        //为了实现set的去重功能
        public static boolean[] choose = new boolean[MAXN * MAXN];
        public static int[] temp = new int[4];

        public static int getSymbol(int a){
            int cur = a;
            while (symbol[cur] != cur)
                cur = symbol[cur];

            while (a != cur){

                int temp = symbol[a];
                symbol[a] = cur;
                a = temp;
            }

            return cur;
        }

        public static void setSameSet(int a, int b){

            a = getSymbol(a);
            b = getSymbol(b);

            if(a == b)
                return;

            if(size[a] < size[b]){

                int t = a;
                a = b;
                b = t;
            }

            size[a] += size[b];
            symbol[b] = a;
        }

        public static void init(){

            Arrays.fill(size , 1);
            for(int i = 0 ; i < symbol.length; i ++)
                symbol[i] = i;
        }

        public static int[] walk = new int[]{-1 , 0 , 1,  0 , -1};
        public int largestIsland(int[][] grid) {

            init();
            n = grid.length;
            int max = 0;
            for(int i = 0 ; i < n ; i ++){

                for(int j = 0 ; j < n ; j ++){

                    if(grid[i][j] == 0)
                        continue;

                    int pos = p(i , j);

                    //四个方向
                    for(int w = 0 ; w < 4 ; w ++){

                        int x = i + walk[w];
                        int y = j + walk[w + 1];
                        if(0 <= x && x < n &&
                        0 <= y && y < n &&
                        grid[x][y] == 1){

                            setSameSet(p(x , y) , pos);
                        }
                    }

                    max = Math.max(max ,
                            size[getSymbol(pos)]);
                }
            }

            //开始遍历可以被转换的地方
            for(int i = 0 ; i < n ; i ++){

                for(int j = 0 ; j < n ; j ++){

                    if(grid[i][j] == 1)
                        continue;

                    int num = 0;
                    //四个方向
                    for(int w = 0 ; w < 4 ; w ++){

                        int x = i + walk[w];
                        int y = j + walk[w + 1];
                        if(0 <= x && x < n &&
                                0 <= y && y < n &&
                        grid[x][y] == 1){

                            //有可能连接的必要
                            int s = getSymbol( p(x , y) );
                            if(!choose[s]){
                                choose[s] = true;
                                temp[num++] = s;
                            }
                        }
                    }

                    //统计
                    int res = 1;//当前位置
                    for(int c = 0 ; c < num ; c++)
                        res += size[temp[c]];

                    max = Math.max(max , res);

                    //复原
                    for(int c = 0 ; c < num ; c ++)
                        choose[temp[c]] = false;
                }
            }

            return max;
        }
    }
}
