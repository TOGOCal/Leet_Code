import java.util.Arrays;

public class p289 {

    /**
     * 根据 百度百科 ， 生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
     *
     * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
     * 每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。
     * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     *
     * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，
     * 其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。
     */


    public static void main(String[] args) {

        //[[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};

        new Solution().gameOfLife(board);
    }


    static
    class Solution {

        int m;
        int n;
        public void gameOfLife(int[][] board) {

            m = board.length;
            n = board[0].length;

            int[][] newBoard = new int[m][n];

            for(int i = 0; i < m; i++){

                for(int j = 0; j < n; j++)
                    newBoard[i][j] = getLiveCellNum(board, i, j);
            }

            for(int i = 0; i < m; i++){

                for(int j = 0; j < n; j++)
                    board[i][j] = newBoard[i][j];
            }
        }

        public int getLiveCellNum(int[][] board, int i, int j){

            //如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
            //如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
            //如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；

            boolean canSubtract = (j != 0);
            boolean canPlus = (j != n-1);

            int count = 0;

            if(i != 0){

                if(canSubtract && board[i-1][j-1] == 1) count++;

                if(board[i-1][j] == 1) count++;

                if(canPlus && board[i-1][j+1] == 1) count++;
            }

            if(canSubtract && board[i][j-1] == 1) count++;
            if(canPlus && board[i][j+1] == 1) count++;

            if(i != m-1){

                if(canSubtract && board[i+1][j-1] == 1) count++;

                if(board[i+1][j] == 1) count++;

                if(canPlus && board[i+1][j+1] == 1) count++;
            }

            if(board[i][j] == 1){//当前是活细胞




                //如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
                if(count < 2) return 0;
                //如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
                if(count == 2 || count == 3) return 1;
                //如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
                return 0;
            }

            // 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
            return count == 3 ? 1 : 0;
        }
    }
}
