import javax.imageio.metadata.IIOMetadataNode;
import java.util.TreeSet;

public class p363 {

    /**
     * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
     *
     * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
     */

    public static void main(String[] args) {

        int[][] arr = {{2,2,-1}};

        System.out.println(new Solution().maxSumSubmatrix(arr, 3));
    }

    static
    class Solution {

        public static int MAXN = 101;
        public static int[][] sum = new int[MAXN][MAXN];

        private void buildSum(int[][] arr){

            for(int i = 0 ; i < arr.length;i++){

                for(int j = 0 ; j < arr[0].length ; j ++){

                    sum[i+1][j+1] = sum[i+1][j] + sum[i][j+1] - sum[i][j] + arr[i][j];
                }
            }
        }

        private int getSum(int i1 , int j1 , int i2 , int j2){

            assert i1 <= i2 && j1 <= j2;
            return sum[i2+1][j2+1] - sum[i2+1][j1] - sum[i1][j2+1] + sum[i1][j1];
        }

        public int maxSumSubmatrix(int[][] matrix, int k) {

            buildSum(matrix);

            int m =matrix.length;
            int n = matrix[0].length;

            int max = Integer.MIN_VALUE;
            //枚举上下边界
            for(int up = 0; up < m ; up++){

                for(int down = up; down < m ; down++){

                    TreeSet<Integer> have = new TreeSet<>();
                    have.add(0);
                    for(int _n = 0 ; _n < n ; _n++){

                        int nowSum = getSum(up , 0 , down , _n);

                        //k >= s - w
                        //w >= s - k
                        int want = nowSum - k;

                        Integer found = have.ceiling(want);

                        if(found != null){

                            max = Math.max(nowSum - found , max);
                        }

                        have.add(nowSum);
                    }
                }
            }

            return max;
        }
    }


    class Solution2 {
        // 附上完整代码
        public int maxSumSubmatrix(int[][] matrix, int k) {
            int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
            // O(cols ^ 2 * rows)
            for (int l = 0; l < cols; l++) { // 枚举左边界
                int[] rowSum = new int[rows]; // 左边界改变才算区域的重新开始
                for (int r = l; r < cols; r++) { // 枚举右边界
                    for (int i = 0; i < rows; i++) { // 按每一行累计到 dp
                        rowSum[i] += matrix[i][r];
                    }
                    max = Math.max(max, dpmax(rowSum, k));
                    if (max == k) return k; // 尽量提前
                }
            }
            return max;
        }
        // 在数组 arr 中，求不超过 k 的最大值
        private int dpmax(int[] arr, int k) {
            int rollSum = arr[0], rollMax = rollSum;
            // O(rows)
            for (int i = 1; i < arr.length; i++) {
                if (rollSum > 0) rollSum += arr[i];
                else rollSum = arr[i];
                if (rollSum > rollMax) rollMax = rollSum;
            }
            if (rollMax <= k) return rollMax;
            // O(rows ^ 2)
            int max = Integer.MIN_VALUE;
            for (int l = 0; l < arr.length; l++) {
                int sum = 0;
                for (int r = l; r < arr.length; r++) {
                    sum += arr[r];
                    if (sum > max && sum <= k) max = sum;
                    if (max == k) return k; // 尽量提前
                }
            }
            return max;
        }

    }

    class Solution3{

        public static int MAXN = 101;
        public static int[][] sum = new int[MAXN][MAXN];

        private void buildSum(int[][] arr){

            for(int i = 0 ; i < arr.length;i++){

                for(int j = 0 ; j < arr[0].length ; j ++){

                    sum[i+1][j+1] = sum[i+1][j] + sum[i][j+1] - sum[i][j] + arr[i][j];
                }
            }
        }

        private int getSum(int i1 , int j1 , int i2 , int j2){

            assert i1 <= i2 && j1 <= j2;
            return sum[i2+1][j2+1] - sum[i2+1][j1] - sum[i1][j2+1] + sum[i1][j1];
        }

        public int maxSumSubmatrix(int[][] matrix, int k) {

            buildSum(matrix);

            int m = matrix.length;
            int n = matrix[0].length;
            int max = Integer.MIN_VALUE;
            for(int i1 = 0 ; i1 < m ; i1++ ){

                for(int j1 = 0 ;j1 < n ;j1 ++){

                    for(int i2 = i1;i2 < m;i2++){

                        for(int j2 = j1; j2< n ;j2++){

                            int sum = getSum(i1 , j1 , i2 ,j2);

                            if(sum < k){
                                max = Math.max(sum , max);
                            }else if(sum == k){

                                return k;
                            }
                        }
                    }
                }
            }

            return max;
        }
    }
}
