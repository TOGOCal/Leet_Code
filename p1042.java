package from_1000_to_1100;

import java.util.Arrays;

public class p1042 {

    /**
     * 有 n 个花园，按从 1 到 n 标记。另有数组 paths ，其中 paths[i] = [xi, yi] 描述了花园 xi 到花园 yi 的双向路径。在每个花园中，你打算种下四种花之一。
     *
     * 另外，所有花园 最多 有 3 条路径可以进入或离开.
     *
     * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
     *
     * 以数组形式返回 任一 可行的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1、2、3、4 表示。保证存在答案。
     *
     */

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().gardenNoAdj(4, new int[][]{{1,2},{3,4}})));
    }


    static
    class Solution {

        public static int MAXN = 10_000 + 1 + 1;
        //public static int[] color = new int[MAXN];

        public static int[][] next = new int[MAXN][1 + 4 + 4];//第一个位置代表当前节点连接了几个点
        public static int[] queue = new int[MAXN << 1];
        public static int top , end;
        public static void init(){

            top = end = 0;
        }

        public static void push(int n){

            queue[top ++] = n;
        }

        public static int pop(){

            return queue[end ++];
        }

        public static boolean isEmpty(){
            return end == top;
        }

        public int[] gardenNoAdj(int n, int[][] paths) {

            for(int i = 0 ; i < MAXN ; i ++)
                next[i][0] = 0;

            for(int[] p : paths){

                int x = p[0];
                int y = p[1];
                next[x][++next[x][0]] = y;
                next[y][++next[y][0]] = x;
            }

            int[] color = new int[n];

            init();

            for(int index = 1 ; index <= n ; index ++){

                if(color[index - 1] != 0)
                    continue;

                push(index);
                while (!isEmpty()){

                    int x = pop();
                    if(color[x - 1] != 0)
                        continue;

                    int can = 0;
                    for(int i = 1 ; i <= next[x][0] ; i ++){

                        int reach = next[x][i];
                        if(color[reach - 1] == 0)
                            push(reach);
                        else
                            can |= (1 << color[reach - 1]);
                    }

                    for(int i = 1; i <= 4 ; i ++){

                        if((can & (1 << i)) == 0){

                            color[x - 1] = i;
                            break;
                        }
                    }
                }

            }


            return color;
        }
    }
}
