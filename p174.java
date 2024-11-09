public class p174 {

    /**
     * 恶魔们抓住了公主并将她关在了地下城 dungeon 的 右下角 。
     * 地下城是由 m x n 个房间组成的二维网格。我们英勇的骑士最初被安置在 左上角 的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
     *
     * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
     *
     * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；
     * 其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
     *
     * 为了尽快解救公主，骑士决定每次只 向右 或 向下 移动一步。
     *
     * 返回确保骑士能够拯救到公主所需的最低初始健康点数。
     *
     * 注意：任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
     */

    public static void main(String[] args) {

        Solution s = new Solution();
        int[][] dungeon = {{-2,-3,3},{-5,-10,1},{10,30,-5}};
        System.out.println(s.calculateMinimumHP(dungeon));
    }



    static
    class Solution {
        public int calculateMinimumHP(int[][] dungeon) {

            if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;


            int l = dungeon.length;
            int m = dungeon[0].length;

            //动态规划转义：要想从i，j到达最后的位置至少需要多少点的健康度
            //依赖关系：下面以及右边的更小值，所以基本情况的填充方案也是先填充这两单元
            int[] dp = new int[m];

            dp[m-1] = Math.max(0 , -dungeon[l-1][m-1])+ 1;//不能为0

            //最下面一列填充
            //最右边一列填充
            for(int i = m-2 ; i >= 0 ; i--){

                dp[i] = Math.max(1 , dp[i+1] - dungeon[l-1][i]);
            }

            //其余情况填充
            for(int i = l-2 ; i >= 0 ; i--){

                dp[m-1] = Math.max(1 , dp[m-1] - dungeon[i][m-1]) ;
                for(int j = m-2 ; j >= 0 ; j--)

                    dp[j] = Math.max(1 , Math.min(dp[j] , dp[j+1]) - dungeon[i][j]);
            }


            return dp[0];

        }
    }




//    class Solution {
//        public int calculateMinimumHP(int[][] dungeon) {
//
//            if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
//
//
//            int l = dungeon.length;
//            int m = dungeon[0].length;
//
//            //动态规划转义：要想从i，j到达最后的位置至少需要多少点的健康度
//            //依赖关系：下面以及右边的更小值，所以基本情况的填充方案也是先填充这两单元
//            int[][] dp = new int[l][m];
//
//            dp[l-1][m-1] = Math.max(0 , -dungeon[l-1][m-1])+ 1;//不能为0
//
//            //最下面一列填充
//            for(int i = l-2 ; i >= 0 ; i--){
//
//                dp[i][m-1] = Math.max(1 , dp[i+1][m-1] - dungeon[i][m-1]) ;
//            }
//
//            //最右边一列填充
//            for(int i = m-2 ; i >= 0 ; i--){
//
//                dp[l-1][i] = Math.max(1 , dp[l-1][i+1] - dungeon[l-1][i]);
//            }
//
//            //其余情况填充
//            for(int i = l-2 ; i >= 0 ; i--){
//
//                for(int j = m-2 ; j >= 0 ; j--)
//
//                    dp[i][j] = Math.max(1 , Math.min(dp[i+1][j] , dp[i][j+1]) - dungeon[i][j]);
//            }
//
//
//            return dp[0][0];
//
//        }
//    }
}
