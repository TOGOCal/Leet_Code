public class p304 {

    /**
     * 给定一个二维矩阵 matrix，以下类型的多个请求：
     *
     * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。
     * 实现 NumMatrix 类：
     *
     * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
     * int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。
     */

    class NumMatrix {

        int[][] prefixSum;

        public NumMatrix(int[][] matrix) {

            prefixSum = new int[matrix.length + 1][matrix[0].length + 1];

            for (int i = 0; i < matrix.length; i++) {

                for (int j = 0; j < matrix[0].length; j++)

                    prefixSum[i + 1][j + 1] = prefixSum[i + 1][j] + prefixSum[i][j + 1] + matrix[i][j] - prefixSum[i][j];
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {

            return prefixSum[row2 + 1][col2 + 1] - prefixSum[row1][col2 + 1] - prefixSum[row2 + 1][col1] + prefixSum[row1][col1];
        }
    }

}
