package from_2400_to_2500;

import java.util.Arrays;

public class p2407 {

    /**
     * 给你一个整数数组 nums 和一个整数 k 。
     *
     * 找到 nums 中满足以下要求的最长子序列：
     *
     * 子序列 严格递增
     * 子序列中相邻元素的差值 不超过 k 。
     * 请你返回满足上述要求的 最长子序列 的长度。
     *
     * 子序列 是从一个数组中删除部分元素后，剩余元素不改变顺序得到的数组。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().lengthOfLIS(new int[]{1,100,500,100000,100000} , 100000));
    }


    static
    class Solution {

        public static int MAXN = (1_00_000 << 1) + 1;
        public static int[] max = new int[MAXN << 2];
        public static void set(int index , int maxValue){

            setRecursion(index , index , maxValue,
                    0 , MAXN - 1 , 1);
        }

        private static void setRecursion(int left , int right , int value,
                                         int nowLeft , int nowRight , int treeIndex){

            if(left <= nowLeft && nowRight <= right){

                max[treeIndex] = Math.max(max[treeIndex] , value);
                return;
            }

            int mid = (nowLeft + nowRight) >> 1;
            if(left <= mid)
                setRecursion(left , right , value ,
                        nowLeft , mid , treeIndex << 1);


            if(mid < right)
                setRecursion(left , right , value ,
                        mid + 1 , nowRight , treeIndex << 1 | 1);

            max[treeIndex] = Math.max(max[treeIndex << 1] , max[treeIndex << 1 | 1]);
        }

        public static int getMax(int left , int right){

            return getRecursion(left , right ,
                    0 , MAXN - 1 , 1);
        }

        public static int getRecursion(int left , int right ,
                                       int nowLeft , int nowRight , int treeIndex){

            if(left <= nowLeft && nowRight <= right)
                return max[treeIndex];

            int mid = (nowLeft + nowRight) >> 1;
            int res = 0;
            if(left <= mid)
                res = Math.max(res ,
                        getRecursion(left , right ,
                                nowLeft , mid , treeIndex << 1));
            if(mid < right)
                res = Math.max(res ,
                        getRecursion(left , right,
                                mid + 1 , nowRight , treeIndex << 1 | 1));

            return res;
        }

        public int lengthOfLIS(int[] nums, int k) {

            Arrays.fill(max , 0);

            for(int i = nums.length - 1 ; i >= 0 ; i --){

                int num = nums[i];
                //寻找范围是严格大于这个数的
                int l = getMax(num + 1 , num + k);
                set(num , l + 1);
            }

            return getMax(0 , MAXN - 1);
        }
    }
}
