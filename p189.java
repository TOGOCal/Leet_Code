import java.util.Arrays;

public class p189 {

    /**
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     */


    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7};
        Solution s = new Solution();

        s.rotate(arr, 3);

        System.out.println(Arrays.toString(arr));
    }

    static
    class Solution {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            reverse(nums, 0, n - 1);//整体翻转
            reverse(nums, 0, k - 1);//分部分翻转
            reverse(nums, k, n - 1);
        }

        public void reverse(int[] nums, int left, int right) {
            while (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
    }


//    class Solution {
//
//        public static int MAXN = 100001;
//        public static boolean[] is = new boolean[MAXN];
//
//
//        public void rotate(int[] nums, int k) {
//
//            if(nums == null || nums.length == 0 || nums.length == 1){
//
//                return;
//            }
//
//            Arrays.fill(is, false);
//
//            int length = nums.length;
//            for(int i = 0; i < length ; i++){
//
//                if(is[i]){
//                    continue;
//                }
//
//                int index = i;
//                int temp = nums[index];
//                while(!is[index]){
//
//                    int p = getPosition(index , k, length);
//
//                    int newTemp = nums[p];
//                    nums[p] = temp;
//                    temp = newTemp;
//
//                    is[index] = true;
//
//                    index = p;
//                }
//            }
//        }
//
//        private int getPosition(int index , int k , int length){
//
//            int position = index + k;
//            if(position < length){
//
//                return position;
//            }
//
//            return (position - length) % length;
//        }
//    }
}
