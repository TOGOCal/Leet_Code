import java.util.Arrays;

public class p935 {

    /**
     * 象棋骑士有一个独特的移动方式，它可以垂直移动两个方格，水平移动一个方格，或者水平移动两个方格，垂直移动一个方格(两者都形成一个 L 的形状)。
     * 象棋骑士可能的移动方式如下图所示:
     * 我们有一个象棋骑士和一个电话垫，如下所示，骑士只能站在一个数字单元格上(即蓝色单元格)。
     * 给定一个整数 n，返回我们可以拨多少个长度为 n 的不同电话号码。
     * 你可以将骑士放置在任何数字单元格上，然后你应该执行 n - 1 次移动来获得长度为 n 的号码。所有的跳跃应该是有效的骑士跳跃。
     * 因为答案可能很大，所以输出答案模 109 + 7.
     */


    public static void main(String[] args) {

        System.out.println(new Solution().knightDialer(2));
    }

    static
    class Solution {

        public static int mod = 1_000_000_000 + 7;
        public static int MAXN = 5000 + 1;
        public static int[][] arr = new int[][]{{1,2,3} , {4,5,6} , {7,8,9} , {-1,0,-1}};
        public static int[][] move = new int[][]{{2 , 1} , {2 , -1} , {-2 , 1} , {-2 , -1},
                {1 , 2} , {1 , -2} , {-1 , -2} , {-1 , 2}};

        public static int[][] go = new int[10][10];
        public static int[][] dp = new int[10][MAXN];//目前在i位置，要找到一个长度为j的电话号码，有多少种不同的号码

        public static int M = arr.length;
        public static int N = arr[0].length;

        static {

            for(int[] g : go)
                Arrays.fill(g , - 1);

            int x , y;
            for(int i = 0 ; i < M; i ++){


                for(int j = 0 ; j < N; j ++){

                    int index = 0;
                    int value = arr[i][j];
                    if(value == -1)
                        continue;

                    for(int[] g : move){

                        x = i + g[0];
                        y = j + g[1];
                        if(0 <= x && x < M &&
                            0 <= y && y < N &&
                            arr[x][y] != -1){

                            go[value][index++] = arr[x][y];//每个位置接下来能够到达的位置
                        }
                    }
                }
            }

            for(int i = 0 ; i < 10 ; i ++)
                dp[i][1] = 1;//每个只找长度1，只有1种找法
        }

        public int knightDialer(int n) {

            for(int l = 2 ; l <= n ;l ++){

                long t = 0;
                for(int i = 0 ; i < M ; i ++){

                    for(int j = 0 ; j < N ; j ++){

                        t = 0;
                        int v = arr[i][j];
                        if(v == -1)
                            continue;

                        int[] g = go[v];
                        int index = 0;
                        while(g[index] != -1){

                            t = (t + dp[g[index]][l - 1]) % mod;
                            index++;
                        }

                        dp[v][l] = (int)t;
                    }
                }
            }

            int res = 0;
            for(int i = 0 ; i < M ; i ++){

                for(int j = 0 ; j < N ; j++){

                    if(arr[i][j] != -1)
                        res = (res + dp[arr[i][j]][n]) % mod;
                }
            }

            return res;
        }
    }
}
