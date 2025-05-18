public class p2426 {

    /**
     * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，两个数组的大小都为 n ，同时给你一个整数 diff ，统计满足以下条件的 数对 (i, j) ：
     *
     * 0 <= i < j <= n - 1 且
     * nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
     * 请你返回满足条件的 数对数目 。
     */

    /**
     * nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
     * nums1[i] - nums2[i] <= nums1[j] - nums2[j] + diff
     * arr[i] <= arr[j] + diff
     * //归并分治？
     */


    public static void main(String[] args) {

        System.out.println(new Solution().numberOfPairs(new int[]{-4,-4,4,-1,-2,5} , new int[]{-2,2,-1,4,4,3} , 1));
    }

    static
    class Solution {

        public static int MAXN = 1_00_000 + 1;
        public static int[] arr = new int[MAXN];
        public static int[] help = new int[MAXN];
        public static int realLength;
        public static int diff;

        public long numberOfPairs(int[] nums1, int[] nums2, int diff) {

            realLength = nums1.length;
            Solution.diff = diff;
            for(int i = 0 ; i < realLength ; i ++)
                arr[i] = nums1[i] - nums2[i];


            return recursion(0 , realLength - 1);
        }

        public long recursion(int left , int right){

            long res = 0;
            if(left >= right)
                return 0;

            int mid = (left + right) >> 1;
            res += recursion(left , mid);
            res += recursion(mid + 1 , right);

            int l1 = left;
            int l2 = mid + 1;

            while (l1 <= mid && l2 <= right){

                while(l2 <= right && arr[l1] > arr[l2] + diff){

                    l2 ++;
                }
                //对于出循环的l1来说，从此时的l2到right这些位置都是可以的位置
                res += (right - l2 + 1);
                l1++;
            }
            //出循环如果l1未用完，则之后都是+0，故而不用处理
            //出循环如果l2未用完，则没有课题统计的项了，也不用处理

            //进行归并排序
            int index = left;
            l1 = left;
            l2 = mid + 1;
            while (l1 <= mid && l2 <= right){

                if(arr[l1] < arr[l2])
                    help[index++] = arr[l1++];
                else
                    help[index++] = arr[l2++];
            }

            while (l1 <= mid)
                help[index++] = arr[l1++];
            while (l2 <= right)
                help[index++] = arr[l2++];

            for(int i = left ; i <= right ; i++)
                arr[i] = help[i];

            return res;
        }
    }
}
