package from_400_to_500;

public class p419 {

    /**
     * 给你一个大小为 m x n 的矩阵 board 表示棋盘，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在棋盘 board 上放置的 舰队 的数量。
     *
     * 舰队 只能水平或者垂直放置在 board 上。换句话说，舰队只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状放置，其中 k 可以是任意大小。两个舰队之间至少有一个水平或垂直的空格分隔 （即没有相邻的舰队）。
     */


    //判断起始位置数量，起始位置的特点是上面和左边都没有舰队
    class Solution {
        public int countBattleships(char[][] board) {

            int count = 0;
            for(int i = 0 ; i < board.length ; i++){

                for(int j = 0 ; j < board[0].length ;j++){

                    if(board[i][j] == 'X'){

                        if((i - 1 < 0 || board[i-1][j] == '.') && (j - 1 < 0 || board[i][j-1] == '.')){

                            count++;
                        }
                    }
                }
            }

            return count;
        }
    }
}
