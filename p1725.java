package from_1700_to_1800;

public class p1725 {

    /**
     * 给你一个数组 rectangles ，其中 rectangles[i] = [li, wi] 表示第 i 个矩形的长度为 li 、宽度为 wi 。
     *
     * 如果存在 k 同时满足 k <= li 和 k <= wi ，就可以将第 i 个矩形切成边长为 k 的正方形。例如，矩形 [4,6] 可以切成边长最大为 4 的正方形。
     *
     * 设 maxLen 为可以从矩形数组 rectangles 切分得到的 最大正方形 的边长。
     *
     * 请你统计有多少个矩形能够切出边长为 maxLen 的正方形，并返回矩形 数目 。
     */

    class Solution {
        public int countGoodRectangles(int[][] rectangles) {

            int res = 0;
            int maxLen = 0;
            for(int[] rectangle : rectangles){

                int len = Math.min(rectangle[0] , rectangle[1]);
                if(len > maxLen){

                    res = 1;
                    maxLen = len;
                }else if(len == maxLen)
                    res ++;
            }

            return res;
        }
    }
}
