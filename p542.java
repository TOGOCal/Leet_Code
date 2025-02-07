package from_500_to_600;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class p542 {

    /**
     * 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
     *
     * 两个相邻元素间的距离为 1 。
     */

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {1 , 1 , 1}})));
    }

    static
    class Solution {

        public static int[] move = new int[]{0 , 1 , 0 , -1 , 0};


        public int[][] updateMatrix(int[][] mat) {

            int m = mat.length;
            int n = mat[0].length;
            Queue<Integer> q = new LinkedList<>();
            boolean[][] visit = new boolean[m][n];
            int[][] res = new int[m][n];

            int level = 0;
            int levelCount = 0;
            for(int i = 0; i < m; i ++){

                for(int j = 0 ; j < n ; j++){

                    if(mat[i][j] == 0){

                        q.add(i * n + j);
                        visit[i][j] = true;
                        levelCount++;
                    }
                }
            }

            int nextLevel = 0;
            while (!q.isEmpty()){

                int p = q.poll();
                int i = p / n;
                int j = p % n;
                res[i][j] = level;
                for(int mo = 0 ; mo < 4 ; mo ++){

                    int x = i + move[mo];
                    int y = j + move[mo + 1];
                    if(0 <= x && x < m && 0 <= y && y < n && !visit[x][y]){

                        nextLevel++;
                        q.add(x * n + y);
                        visit[x][y] = true;
                    }
                }


                levelCount--;
                if(levelCount == 0){

                    levelCount = nextLevel;
                    nextLevel = 0;
                    level++;
                }
            }

            return res;
        }

    }

}
