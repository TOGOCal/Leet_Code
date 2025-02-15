package from_400_to_500;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class p403 {

    /**
     * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。
     *
     * 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃 1 个单位（即只能从单元格 1 跳至单元格 2 ）。
     *
     * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃
     */

    public static void main(String[] args) {

        System.out.println(new Solution().canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
    }


    static
    class Solution {

        public static int MAXN = 2001;
        //                                         index pre
        public static boolean[][] dp = new boolean[MAXN][MAXN];

        public boolean canCross(int[] stones) {
            if(stones.length <= 1)
                return true;

            HashMap<Integer , Integer> have = new HashMap<>();
            for(int i = 0 ; i < stones.length ; i++)
                have.put(stones[i] , i);

            int realLength = stones.length;
            Arrays.fill(dp[realLength - 1] , true);

            for(int index = realLength - 2 ; index >= 1 ; index --){

                for(int pre = 1 ; pre <= index;pre ++){

                    long length = stones[index] - stones[index - pre];

                    boolean key = false;
                    for(int i = -1 ; i <= 1; i ++){

                        if(length + i >= 1){
                            //int nextIndex = binarySearch(stones , stones[index] + (int)length + i);
                            int nextIndex = have.getOrDefault(stones[index] + (int)length + i , -1);
                            if(nextIndex != -1)
//                                if(jump(stones , nextIndex , nextIndex - index))
//                                    return true;
                                if(dp[nextIndex][nextIndex - index]){
                                    key = true;
                                    break;
                                }
                        }
                    }

                    dp[index][pre] = key;
                }
            }


            //return jump(stones , 0 , 0);
            return dp[1][1] && stones[1] == 1;
        }


        //pre代表indexDiff
//        public boolean jump(int[] stones , int index , int pre){
//
//            if(index == stones.length - 1)
//                return true;
//
//            //保证index位置一定时有石头的
//            int length = stones[index] - stones[index - pre];
//            for(int i = -1 ; i <= 1; i ++){
//
//                if(length + i >= 1){
//
//                    int nextIndex = binarySearch(stones , stones[index] + length + i);
//                    if(nextIndex  != -1)
//                        if(jump(stones , nextIndex , nextIndex - index))
//                            return true;
//                }
//
//
//            }
//
//            return false;
//        }

        public int binarySearch(int[] arr , int target){

            int left = 0;
            int right = arr.length - 1;

            while (left <= right){

                int mid = (left + right) >> 1;
                if(arr[mid] < target)
                    left = mid + 1;
                else if(target < arr[mid])
                    right = mid - 1;
                else
                    return mid;
            }

            return -1;
        }
    }


    //由于最多跳2000次，所以上一次跳跃的距离也最多是2000，pre直接代表距离即可，不用代表index之差
    class Solution2 {
        public boolean canCross(int[] stones) {
            int n = stones.length;
            boolean[][] dp = new boolean[n][n];
            dp[0][0] = true;
            //大于了最远能跳的距离
            for (int i = 1; i < n; ++i) {
                if (stones[i] - stones[i - 1] > i) {
                    return false;
                }
            }
            for (int i = 1; i < n; ++i) {
                for (int j = i - 1; j >= 0; --j) {
                    int k = stones[i] - stones[j];
                    if (k > j + 1) {
                        break;
                    }
                    dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                    if (i == n - 1 && dp[i][k]) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

}
