import java.util.Scanner;

public class p48 {

    /**
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     */

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        int[][] matrix = new int[n][n];

        for(int i =0; i< n;i++){

            for(int j =0; j< n;j++){

                matrix[i][j] = s.nextInt();
            }
        }

        new Solution().rotate(matrix);

        s.close();
    }

    static class Solution {
        public void rotate(int[][] matrix) {

            int length = matrix.length;

            for(int i =0; i< length/2;i++){

                rotateByLayer(matrix , i , length);
            }
        }

        public void rotateByLayer(int[][] matrix , int l , int length){

            for(int i =0; i< length-2*l-1;i++){

                int help = matrix[l][l+i];
                matrix[l][l+i] = matrix[length-l-1 - i][l];
                matrix[length-l-1 - i][l] = matrix[length-l-1][length-l-1 - i];
                matrix[length-l-1][length-l-1 - i] = matrix[l+i][length-l-1];
                matrix[l+i][length-l-1] = help;
            }
        }
    }
}
