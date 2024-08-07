public class p63 {

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
     *
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     *
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     */

    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {

            if( obstacleGrid.length==0||obstacleGrid[0].length==0||obstacleGrid[0][0]==1){

                return 0;
            }

            obstacleGrid[0][0] =1;

            for( int i=1;i<obstacleGrid.length;i++){

                obstacleGrid[i][0] = (obstacleGrid[i][0]==1?0:obstacleGrid[i-1][0]);
            }

            for( int j=1;j<obstacleGrid[0].length;j++){

                obstacleGrid[0][j] = (obstacleGrid[0][j]==1?0:obstacleGrid[0][j-1]);
            }


            for( int i=1;i<obstacleGrid.length;i++){

                for( int j=1;j<obstacleGrid[0].length;j++){

                    obstacleGrid[i][j] = (obstacleGrid[i][j]==1?0:(obstacleGrid[i-1][j]+obstacleGrid[i][j-1]));
                }
            }

            return obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1];
        }
    }


}
