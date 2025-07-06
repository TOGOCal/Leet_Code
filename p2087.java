package from_2000_to_2100;

import java.util.Comparator;
import java.util.PriorityQueue;

public class p2087 {

    /**
     * 给你一个 m x n 的网格图，其中 (0, 0) 是最左上角的格子，(m - 1, n - 1) 是最右下角的格子。给你一个整数数组 startPos ，startPos = [startrow, startcol] 表示 初始 有一个 机器人 在格子 (startrow, startcol) 处。同时给你一个整数数组 homePos ，homePos = [homerow, homecol] 表示机器人的 家 在格子 (homerow, homecol) 处。
     *
     * 机器人需要回家。每一步它可以往四个方向移动：上，下，左，右，同时机器人不能移出边界。每一步移动都有一定代价。再给你两个下标从 0 开始的额整数数组：长度为 m 的数组 rowCosts  和长度为 n 的数组 colCosts 。
     *
     * 如果机器人往 上 或者往 下 移动到第 r 行 的格子，那么代价为 rowCosts[r] 。
     * 如果机器人往 左 或者往 右 移动到第 c 列 的格子，那么代价为 colCosts[c] 。
     * 请你返回机器人回家需要的 最小总代价 。
     */

    class Solution {

        public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {

            int cost = 0;
            int from = startPos[0];
            int to = homePos[0];
            if(from < to)
                for(int i = from ; i < to ; i ++)
                    cost += rowCosts[i + 1];
            else
                for(int i = from ; i > to ; i --)
                    cost += rowCosts[i - 1];
            from = startPos[1];
            to = homePos[1];
            if(from < to)
                for(int i = from ; i < to ; i ++)
                    cost += colCosts[i + 1];
            else
                for(int i = from ; i > to ; i --)
                    cost += colCosts[i - 1];

            return cost;
        }


//        public static Comparator<int[]> comparator = new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0] - o2[0];
//            }
//        };
//
//        public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
//
//            PriorityQueue<int[]> heap = new PriorityQueue<>(comparator);
//            heap.add(new int[]{0 , startPos[0] , startPos[1]});
//
//            int m = rowCosts.length;
//            int n = colCosts.length;
//            boolean[][] visit = new boolean[m][n];
//
//            while (!heap.isEmpty()){
//                int[] out = heap.poll();
//                int cost = out[0];
//
//                int i = out[1];
//                int j = out[2];
//
//                if(visit[i][j])
//                    continue;
//
//                if(i == homePos[0] && j == homePos[1])
//                    return cost;
//
//                visit[i][j] = true;
//
//                //上下左右移动
//                if(i > 0)
//                    heap.add(new int[]{cost + rowCosts[i - 1], i - 1 , j});
//                if(j > 0)
//                    heap.add(new int[]{cost + colCosts[j - 1] , i , j - 1});
//                if(i < m - 1)
//                    heap.add(new int[]{cost + rowCosts[i + 1] , i + 1 , j});
//                if(j < n - 1)
//                    heap.add(new int[]{cost + colCosts[j + 1] , i , j + 1});
//            }
//            return -1;
//        }
    }
}
