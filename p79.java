import java.util.Scanner;

public class p79 {

    /**
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     */


    public static void main(String[] args) {

        Scanner s= new Scanner(System.in);


        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};



        Solution solution = new Solution();

        System.out.println(solution.exist(board, "ABCB"));

        s.close();
    }

    static
    class Solution {
        public boolean exist(char[][] board, String word) {


            if(word.length() == 1){

                char c = word.charAt(0);

                for(int i = 0; i < board.length; i++) {

                    for(int j = 0; j < board[0].length; j++) {

                        if(board[i][j] == c) {

                            return true;
                        }
                    }
                }
            }



            char[] s = word.toCharArray();

            for(int i = 0; i < board.length; i++) {

                for(int j = 0; j < board[0].length; j++) {

                    if(board[i][j] == s[0]){

                        if(dfs(board, s , i, j, 0)) {

                            return true;
                        }
                    }
                }
            }

            return false;
        }

        public boolean dfs(char[][] board, char[] word, int i, int j, int index) {

            if(index == word.length) {

                return true;
            }

            if(word[index] == board[i][j]) {

                board[i][j] = (char)0;

//                boolean res =
//                                (i!= 0 && dfs(board, word, i - 1, j, index + 1) ) ||
//                                (i != board.length - 1 && dfs(board, word, i + 1, j, index + 1) ) ||
//                                (j != 0 && dfs(board, word, i, j - 1, index + 1) ) ||
//                                (j != board[0].length - 1 && dfs(board, word, i, j + 1, index + 1) );
                if(i!= 0 && dfs(board, word, i - 1, j, index + 1) ) return true;

                if(i != board.length - 1 && dfs(board, word, i + 1, j, index + 1) ) return true;

                if(j != 0 && dfs(board, word, i, j - 1, index + 1) ) return true;

                if(j != board[0].length - 1 && dfs(board, word, i, j + 1, index + 1) ) return true;


                board[i][j] = word[index];

//                return res;
            }

            return false;
        }
    }
}
