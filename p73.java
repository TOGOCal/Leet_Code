import java.util.Scanner;

public class p73 {

    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);


        int[][] matrix = new int[s.nextInt()][s.nextInt()];


        for( int i = 0; i < matrix.length; i++) {


            for( int j = 0; j < matrix[0].length; j++) {

                matrix[i][j] = s.nextInt();


            }
        }


        Solution solution = new Solution();

        solution.setZeroes(matrix);

        s.close();
    }

    static class Solution {
        public void setZeroes(int[][] matrix) {

            boolean[] col = new boolean[matrix[0].length];//有哪些列被置零了
            boolean[] row = new boolean[matrix.length];//有哪些行被置零了


            for (int i = 0; i < matrix.length; i++) {

                if( row[i]) continue;//如果该行被置零了，则跳过

                for (int j = 0; j < matrix[0].length; j++) {

                    if( col[j]) continue;//如果该列被置零了，则跳过

                    int a = matrix[i][j];
                    if( matrix[i][j] == 0) {

                        col[j] = true;
                        row[i] = true;
                    }

                }


            }

            for( int j = 0; j < matrix[0].length; j++) {

                if( col[j]) {
                    for( int k = 0; k < matrix.length; k++) {

                        matrix[k][j] = 0;
                    }
                }

            }

            for( int k = 0; k < matrix.length; k++) {

                if( row[k]) {
                    for( int j = 0; j < matrix[0].length; j++) {

                        matrix[k][j] = 0;
                    }
                }
            }
        }
    }
}
