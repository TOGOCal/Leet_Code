package from_3100_to_3200;

import java.util.ArrayList;
import java.util.List;

public class p3148 {


    /**
     * 给你一个由 正整数 组成、大小为 m x n 的矩阵 grid。
     * 你可以从矩阵中的任一单元格移动到另一个位于正下方或正右侧的任意单元格（不必相邻）。
     * 从值为 c1 的单元格移动到值为 c2 的单元格的得分为 c2 - c1 。
     *
     * 你可以从 任一 单元格开始，并且必须至少移动一次。
     *
     * 返回你能得到的 最大 总得分。
     */


    public static void main(String[] args) {

        int[][] arr = new int[][]{{4,9},{5,2},{3,1}};
        List<List<Integer>> grid = new ArrayList<>();

        for(int i = 0 ; i < arr.length;i++){
            List<Integer> list = new ArrayList<>();
            for(int j = 0 ; j < arr[0].length; j++)
                list.add(arr[i][j]);

            grid.add(list);
        }

        System.out.println(new Solution().maxScore(grid));
    }


    static
    class Solution {

        /**
         * 由于从跳跃方式可知，每个位置对于其右下的任意点都是可以到达的，
         * 所以我们只需要知道每个点右下方向上最大的点的值是多少就可以了
         * 或者，我们也可以通过计算每个点左上最小的点是什么
         */


        public static final int MAXN = 1001;
        public static final int[][] min = new int[MAXN][MAXN];

        public int maxScore(List<List<Integer>> grid) {

            int m = grid.size();
            int n = grid.getFirst().size();

            //先处理第一排
            List<Integer> first = grid.getFirst();
            min[0][0] = first.getFirst();
            for(int i = 1 ; i < n ; i++)
                min[0][i] = Math.min(min[0][i - 1] , first.get(i));


            for(int i = 1 ; i < m ; i++){

                List<Integer> get = grid.get(i);
                min[i][0] = Math.min(min[i - 1][0] , get.getFirst());
                for(int j = 1 ; j < n ; j ++)
                    min[i][j] = Math.min(
                            Math.min(min[i][j - 1] , min[i - 1][j]),
                            get.get(j)
                    );

            }//完成min数组加工


            int res = Integer.MIN_VALUE;
            //遍历每个位置，得到最大值
            for(int i = 0 ; i < m ; i++) {

                List<Integer> get = grid.get(i);
                for (int j = 0; j < n; j++)
                    res = Math.max(
                            res,
                            get.get(j) - Math.min(i > 0 ? min[i - 1][j] :Integer.MAX_VALUE ,
                                    j > 0 ? min[i][j - 1] : Integer.MAX_VALUE)
                            );
            }

            return res;
        }
    }
}
