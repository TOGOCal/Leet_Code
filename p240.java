public class p240 {

    /**
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     *
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     */

    public static void main(String[] args) {

        int[][] arr = new int[][]{{-5}};
        new Solution().searchMatrix(arr , -5);
    }


    static
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {

            //找到[0]位置第一个大于target的位置作为结束
            //[end]位置第一个小于target的位置的系啊一个元素作为开始

            int begin = 0,end = matrix.length-1;
            int left = 0;
            int right = matrix.length-1;

            while(left <= right){

                int mid = left + ((right - left) >> 1);

                if(matrix[mid][0] > target){

                    right = mid - 1;
                }else{

                    end = mid;
                    left = mid + 1;
                }
            }

            left = 0;
            right = matrix.length-1;

            int len = matrix[0].length;
            while(left <= right){

                int mid = left + ((right - left) >> 1);

                if(matrix[mid][len - 1] >= target){

                    begin = mid;
                    right = mid - 1;
                }else{

                    left = mid + 1;
                }
            }

            if(begin > end){

                return false;
            }

            for(int i = begin ; i <= end ; i++){

                int[] arr = matrix[i];

                if(found(arr , target)){

                    return true;
                }
            }

            return false;
        }

        public boolean found(int[] arr , int target){

            int left = 0;
            int right = arr.length-1;

            while (left <= right){

                int mid = left + ((right - left) >> 1);

                if(arr[mid] == target){

                    return true;
                }else if(arr[mid] > target){

                    right = mid - 1;
                }else{

                    left = mid + 1;
                }
            }

            return false;
        }
    }
}
