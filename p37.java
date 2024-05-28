import java.util.Scanner;

public class p37 {

    /**
     * 编写一个程序，通过填充空格来解决数独问题。
     *
     * 数独的解法需 遵循如下规则：
     *
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("请输入数据，以空格分隔，代填的点用 . 表示");

        char[][] board = new char[9][9];

        for(int i = 0 ; i < 9 ; i++){

            String str = s.nextLine();
            for(int j = 0 ; j < 9 ; j++){

                board[i][j] = str.charAt(j*2);
            }
        }

        Solution solution = new Solution();

        solution.solveSudoku(board);

        for(int i = 0 ; i < 9 ; i++){

            for(int j = 0 ; j < 9 ; j++){

                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }

        s.close();
    }

    //方法1：暴力递归
    static class Solution {
        public void solveSudoku(char[][] board) {

            solve(board, 0, 0);
        }

        public boolean solve(char[][] board, int row, int col) {

            //开始检查的点
            if(col == 8){
                //之前决定的是某一排的最后位置
                col = 0;
                row++;
            }

            if(row == 9){
                //赋值完成
                return true;
            }

            boolean key = true;
            for(int i = row;i < 9 ; i++){

                int j;
                if(key){

                    j = col;
                    key = false;
                }else{

                    j =0;
                }
                for( ; j < 9 ; j++){//j只被赋值一次

                    if(board[i][j] == '.'){

                        for(char c = '1' ; c <= '9' ; c++){

                            board[i][j] = c;//赋值操作在check的外面
                            if(check(board, i, j)){


                                if(solve(board, i, j)){
                                    //之后有解决的方案了
                                    return true;
                                }//如果没有就继续 c的赋值操作
                            }
                        }

                        board[i][j] = '.';
                        return false;//如果这个位置c 1 - 9 都不行，就返回false；
                    }
                }
            }

            //假定情况：数独已经被填完，这样在遍历的时候就会超出，代码到达这里，所以返回值为true
            return true;
        }

        public boolean check(char[][] board, int row, int col) {

            char c= board[row][col];
            //保证检查的这一位不是 '.'
            for(int i =0 ; i<9 ; i++){

                if(c == board[row][i] && i != col){

                    return false;
                }
            }//行检查

            for(int i =0 ; i<9 ; i++){

                if(c == board[i][col] && i != row){

                    return false;
                }
            }//列检查


            int startRow = (row/3)*3;
            int startCol = (col/3)*3;

            for(int i = startRow ; i<startRow+3 ; i++){

                for(int j = startCol ; j<startCol+3 ; j++){

                    if(c == board[i][j] && (i != row || j != col)){

                        return false;
                    }
                }
            }

            return true;
        }




    }


}
