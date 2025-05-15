import java.util.Arrays;

public class p2078 {

    /**
     * 街上有 n 栋房子整齐地排成一列，每栋房子都粉刷上了漂亮的颜色。给你一个下标从 0 开始且长度为 n 的整数数组 colors ，其中 colors[i] 表示第  i 栋房子的颜色。
     *
     * 返回 两栋 颜色 不同 房子之间的 最大 距离。
     *
     * 第 i 栋房子和第 j 栋房子之间的距离是 abs(i - j) ，其中 abs(x) 是 x 的绝对值。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().maxDistance(new int[]{1,1,1,6,1,1,1}));
    }



    //贪心的思路
    class Solution1{

        //至少包含一个断点
        public int maxDistance(int[] colors) {

            int l = colors.length;
            int colorLeft = colors[0];
            int colorRight = colors[l - 1];
            int res = 0;
            for(int i = 0 ; i < l ; i ++){

                int color = colors[i];
                if(color != colorLeft)
                    res = Math.max(i - 0 , res);
                if(color != colorRight)
                    res = Math.max(l - 1 - i , res);
            }

            return res;
        }
    }

    static
    class Solution {

        public static int MAXN = 100 + 1;
        public static int[][] position = new int[MAXN][2];//每个元素最左边出现的位置和最右边出现的位置

        public int maxDistance(int[] colors) {

            for(int[] p : position){

                p[0] = Integer.MAX_VALUE;
                p[1] = Integer.MIN_VALUE;
            }

            int maxColor = 0;
            for(int i = 0 ; i < colors.length ; i ++){

                int color = colors[i];
                maxColor = Math.max(maxColor , color);
                int[] p = position[color];
                p[0] = Math.min(i , p[0]);
                p[1] = Math.max(i , p[1]);
            }

            int res = 0;
            for(int i = 0 ; i <= maxColor ; i ++){
                if(position[i][0] == Integer.MAX_VALUE)
                    continue;

                //当前颜色的最左边找其它颜色的最右边
                int maxRight = Integer.MIN_VALUE;
                int minLeft = Integer.MAX_VALUE;
                for(int j = 0 ; j <= maxColor ; j ++){
                    if(i == j || position[j][0] == Integer.MAX_VALUE)
                        continue;

                    int[] p = position[j];
                    minLeft = Math.min(minLeft , p[0]);
                    maxRight = Math.max(maxRight , p[1]);
                }

                //当前位置最左边找最右边
                res = Math.max(res  ,
                        Math.max(
                                maxRight - position[i][0],
                                position[i][1] - minLeft
                        ));
            }

            return res;
        }
    }
}
