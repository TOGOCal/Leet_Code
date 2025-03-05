import java.util.Arrays;
import java.util.Comparator;

public class p347 {

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     */


    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().topKFrequent(new int[]{5,2,5,3,5,3,1,1,3}, 2)));
    }


    static
    class Solution {

        public static int MAXN = 100001;
        public static int[] arr = new int[MAXN];
        public static int[][] count = new int[MAXN][2];
        public int[] topKFrequent(int[] nums, int k) {

            int realLength = nums.length;

            //时间复杂度n
            for(int i = 0 ; i < realLength; i ++){

                arr[i] = nums[i];
            }

            //时间复杂度nlogn
            Arrays.sort(arr , 0 , realLength);
            int index = 0;

            //时间复杂度n
            for(int i = 1 ; i < realLength ; i ++){

                if(arr[index] != arr[i])
                    arr[++index] = arr[i];
            }//去重

            for(int i = 0 ; i <= index ; i ++){

                int[] c = count[i];
                c[0] = 0;
                c[1] = i;
            }

            //时间复杂度nlogn
            for(int n : nums)
                count[binaryFound(n , 0 , index + 1)][0] ++;

            //这部分可以换成堆
            Arrays.sort(count, 0, index + 1, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o2[0] - o1[0];
                }
            });

            int[] res = new int[k];
            for(int i = 0 ; i < k ; i ++){

                res[i] = arr[count[i][1]];
            }

            return res;
        }

        public int binaryFound(int n , int left , int right){

            while (left <= right){

                int mid = (left + right) >> 1;
                if(arr[mid] < n)
                    left = mid + 1;
                else if(arr[mid] > n)
                    right = mid - 1;
                else
                    return mid;
            }

            return -1;
        }
    }
}
