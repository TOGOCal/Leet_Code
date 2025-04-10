import java.util.Arrays;

public class p593 {

    /**
     * 给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
     *
     * 点的坐标 pi 表示为 [xi, yi] 。 输入没有任何顺序 。
     *
     * 一个 有效的正方形 有四条等边和四个等角(90度角)。
     */

    class Solution {

        //任意三个点构成等腰直角三角形
        public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {

            return is(p1, p2, p3) &&
                    is(p1, p2, p4) &&
                    is(p1, p3, p4) &&
                    is(p2, p3, p4);
        }

        public static int[] tool = new int[3];
        private boolean is(int[] p1 , int[] p2 , int[] p3){

            int a,b;
            a = p1[0] - p2[0];
            b = p1[1] - p2[1];
            tool[0] = a * a + b * b;
            a = p1[0] - p3[0];
            b = p1[1] - p3[1];
            tool[1] = a * a + b * b;
            a = p2[0] - p3[0];
            b = p2[1] - p3[1];
            tool[2] = a * a + b * b;
            if(tool[0] == 0 || tool[1] == 0 || tool[2] == 0)
                return false;

            //Arrays.sort(tool);
            for(int i = 0 ; i < 3 ; i ++){

                for(int j = i ; j < 2 ; j ++){

                    if(tool[j] > tool[j + 1])
                        swap(j , j + 1);
                }
            }
            return tool[0] == tool[1] && tool[0] + tool[1] == tool[2];
        }

        private void swap(int a , int b){

            int t = tool[a];
            tool[a] = tool[b];
            tool[b] = t;
        }

    }
}
