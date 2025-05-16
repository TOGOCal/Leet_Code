import java.util.Arrays;

public class p1591 {

    /**
     * 给你一个奇怪的打印机，它有如下两个特殊的打印规则：
     *
     * 每一次操作时，打印机会用同一种颜色打印一个矩形的形状，每次打印会覆盖矩形对应格子里原本的颜色。
     * 一旦矩形根据上面的规则使用了一种颜色，那么 相同的颜色不能再被使用 。
     * 给你一个初始没有颜色的 m x n 的矩形 targetGrid ，其中 targetGrid[row][col] 是位置 (row, col) 的颜色。
     *
     * 如果你能按照上述规则打印出矩形 targetGrid ，请你返回 true ，否则返回 false 。
     */

    class Solution {

        public static int MAXC = 60 + 1;//最多颜色数

        //由于要使用拓扑排序，所以要构建图的结构，
        //图中指明a颜色可能覆盖了哪些颜色
//        public static int[] nodeFirstEdge = new int[MAXC];
//        public static int[] edgeNextEdge = new int[MAXC * MAXC];
//        public static int[] edgeTo = new int[MAXC * MAXC];
        //edge i j 代表i覆盖了j
        public static boolean[][] edge = new boolean[MAXC][MAXC];//这道题条件比较简单，使用邻接矩阵即可

        public static int[] inGre = new int[MAXC];//某个颜色一定被之上的某些颜色覆盖，这些颜色有多少个

        //记录每种颜色四个点的坐标coordinates[color] = {{x , y} ， {x , y} ， {x , y} ， {x , y}}
        //public static int[][][] coordinates = new int[MAXC][4][2];
        //通过最终结果化简，其实我们只需要记录4各极端坐标即可
        //left right up down
        public static int[][] coordinates = new int[MAXC][4];

//        public static int[][] position = new int[][]{
//                {Integer.MAX_VALUE , Integer.MAX_VALUE} , //左上点想要找x，y都最小的值，所以初始话时使用最大值
//                {Integer.MAX_VALUE , Integer.MIN_VALUE} , //右上
//                {Integer.MIN_VALUE , Integer.MAX_VALUE} , //左下
//                {Integer.MIN_VALUE , Integer.MIN_VALUE} //右下
//        };
        public static int[] position = new int[]{Integer.MAX_VALUE , Integer.MIN_VALUE , Integer.MAX_VALUE , Integer.MIN_VALUE};


        public static int[] queue = new int[MAXC];
        private static int end , top;
        private static void init(){

            end = top = 0;
        }
        private static int poll(){

            return queue[end++];
        }

        private static void push(int color){

            queue[top++] = color;
        }

        private static boolean isEmpty(){

            return end == top;
        }

        public boolean isPrintable(int[][] targetGrid) {

            for(boolean[] e : edge)
                Arrays.fill(e , false);
            Arrays.fill(inGre , -1);


            for(int i = 0 ; i < MAXC ; i ++){

//                int[][] temp = coordinates[i];
//                for(int j = 0 ; j < coordinates.length ; j ++){
//
//                    int[] t = temp[j];
//                    System.arraycopy(position[j], 0, t, 0, t.length);
//                }
                System.arraycopy(position, 0, coordinates[i], 0, 4);
            }//完成初始化


            //开始遍历
            for(int i = 0 ; i < targetGrid.length ; i ++){

                for(int j = 0 ; j < targetGrid[0].length ; j ++){

                    int color  = targetGrid[i][j];
                    inGre[color] = 0;//说明这个颜色出现过
                    int[] p = coordinates[color];
                    //left
                    p[0] = Math.min(p[0] , j);
                    //right
                    p[1] = Math.max(p[1] , j);
                    //up
                    p[2] = Math.min(p[2] , i);
                    //down
                    p[3] = Math.max(p[3] , i);

//                    int[][] position = coordinates[color];
//
//                    //左上
//                    position[0][0] = Math.min(position[0][0] , i);
//                    position[0][1] = Math.min(position[0][1] , j);
//
//                    //右上
//                    position[1][0] = Math.min(position[1][0] , i);
//                    position[1][1] = Math.max(position[1][1] , j);
//
//                    //左下
//                    position[2][0] = Math.max(position[2][0] , i);
//                    position[2][1] = Math.min(position[2][1] , j);
//
//                    //右下
//                    position[3][0] = Math.max(position[3][0] , i);
//                    position[3][1] = Math.max(position[3][1] , j);
                }
            }


            //开始分析每种颜色的覆盖情况
            for(int color = 0 ; color < MAXC ; color ++){

//                int[] p = coordinates[color][0];
//                if(p[0] == Integer.MIN_VALUE && p[1] == Integer.MIN_VALUE)
//                    continue;//说明这个颜色压根没有出现，或者被全覆盖
//                //被全覆盖的颜色不分析也不会有问题
//
//                int[] leftUp = coordinates[color][0];
//                int[] rightUp = coordinates[color][1];
//                int[] leftDown = coordinates[color][2];
//                int[] rightDown = coordinates[color][3];
//
//                int left = Math.min(leftUp[1] , leftDown[1]);
//                int right = Math.max(rightUp[1] , rightDown[1]);
//                int up = Math.min(leftUp[0] , rightUp[0]);
//                int down = Math.min(leftDown[0] , rightDown[0]);
                int[] p = coordinates[color];
                if(p[0] == Integer.MAX_VALUE)
                    continue;//未出现

                //0 1 2 3
                //l r u d
                for(int i = p[2] ; i <= p[3] ; i++){

                    for(int j = p[0] ; j <= p[1] ; j ++){

                        //其它颜色覆盖在了这个上面
                        int otherColor = targetGrid[i][j];
                        if(otherColor != color){

                            if(!edge[otherColor][color]){

                                inGre[color] ++;//入度 + 1；
                                edge[otherColor][color] = true;
                            }
                        }
                    }
                }
            }

            //开始拓扑排序
            //需要先进先出结构
            init();
            for(int color = 0 ; color < MAXC ; color++){

                //作为第一波加入的
                if(inGre[color] == 0)
                    push(color);

            }

            while (!isEmpty()){

                int color = poll();

                //将其覆盖的所有颜色inGre -1
                for(int i = 0 ; i < MAXC ; i ++){

                    if(edge[color][i]){

                        inGre[i] --;
                        if(inGre[i] == 0)
                            push(i);
                    }
                }
            }

            for(int i = 0 ; i < MAXC ; i ++){

                if(inGre[i] != 0 && inGre[i] != -1)
                    return false;
            }

            return true;
        }
    }
}
