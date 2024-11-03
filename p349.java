import java.util.Arrays;

public class p349 {

    /**
     * 给定两个数组 nums1 和 nums2 ，返回 它们的
     * 交集
     *  。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
     */

    class Solution {

        public static int MAXN = 1001;
        public static boolean[] exist = new boolean[MAXN];

        public int[] intersection(int[] nums1, int[] nums2) {
            Arrays.fill(exist , false);

            for(int a : nums1){

                exist[a] = true;
            }

            int[] arr = new int[nums1.length];
            int index = 0;
            for(int a : nums2){

                if(exist[a]){

                    arr[index++] = a;
                    exist[a] = false;
                }
            }

            int[] res = new int[index];
//            System.arraycopy(arr, 0, res, 0, index);

            for(int i = 0 ;i < index ;i ++){

                res[i] = arr[i];
            }

            return res;
        }
    }

//    class Solution {
//        public int[] intersection(int[] nums1, int[] nums2) {
//
//            Arrays.sort(nums1);
//            Arrays.sort(nums2);
//
//            int end1 = 0;
//            int end2 = 0;
//
//            for(int i = 1 ; i < nums1.length ; i++){
//
//                if(nums1[end1] != nums1[i]){
//
//                    nums1[++end1] = nums1[i];
//                }
//            }
//
//            for(int i = 1 ; i < nums2.length ; i++){
//
//                if(nums2[end2] != nums2[i]){
//
//                    nums2[++end2] = nums2[i];
//                }
//            }
//
//            int p1 = 0;
//            int p2 = 0;
//
//            int index = 0;
//            int[] arr = new int[nums1.length + nums2.length];
//
//            while(p1 <= end1 && p2 <= end2){
//
//                if(nums1[p1] < nums2[p2]){
//
//                    p1++;
//                }else if(nums1[p1] > nums2[p2]){
//
//                    p2++;
//                }else{
//
//                    arr[index++] = nums1[p1++];
//                    p2++;
//                }
//            }
//
//            int[] res = new int[index];
//
//            System.arraycopy(arr, 0, res, 0, index);
//
//
//            return res;
//        }
//    }
}
