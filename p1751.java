package from_1700_to_1800;

import java.util.Arrays;
import java.util.Comparator;

public class p1751 {

    /**
     * 给你一个 events 数组，其中 events[i] = [startDayi, endDayi, valuei] ，表示第 i 个会议在 startDayi 天开始，第 endDayi 天结束，如果你参加这个会议，你能得到价值 valuei 。同时给你一个整数 k 表示你能参加的最多会议数目。
     *
     * 你同一时间只能参加一个会议。如果你选择参加某个会议，那么你必须 完整 地参加完这个会议。会议结束日期是包含在会议内的，也就是说你不能同时参加一个开始日期与另一个结束日期相同的两个会议。
     *
     * 请你返回能得到的会议价值 最大和 。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().maxValue(new int[][]{{1, 2, 4}, {3, 4, 3}, {2, 3, 1}}, 2));
    }


    static
    class Solution {

        public static int MAXN = 1000001;

        public int maxValue(int[][] events, int k) {

            Arrays.sort(events, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }//根据结束时间排序
            });

            int[][] dp = new int[events.length][k + 1];
            for(int i = 0 ; i < events.length;i ++)
                dp[i][0] = 0;

            for(int i = 1 ; i < k + 1 ; i ++)
                dp[0][i] = events[0][2];//从0到0不管选多少个，都只有0位置的收益

            for(int i = 1 ; i < events.length ; i ++){

                for(int j = 1 ; j <= k ; j ++){

                    //选这个位置
                    int res = events[i][2];
                    int pre = binaryFound(events[i][0] , events);
                    res += (pre == -1 ? 0 : dp[pre][j - 1]);
                    res = Math.max(res , dp[i -1][j]);
                    dp[i][j] = res;
                }
            }

            return dp[events.length - 1][k];
        }

        //从0-i位置中选最多j个
        public static int get(int i , int j , int[][] events){

            //决定这个位置选
            int max = events[i][2];
            //选出结束时间比当前end小的最靠右的位置
            int pre = binaryFound(events[i][0] , events);//需要比开始时间还早
            max += (pre == -1 ? 0 : get(pre , j - 1 , events));
            //决定这个位置不选
            max = Math.max(max , get(i - 1 , j , events));
            return max;
        }

        public static int binaryFound(int end , int[][] events){

            int left = 0;
            int right = events.length - 1;

            int res = -1;
            while (left <= right){

                int mid = (left + right) >> 1;
                int maybe = events[mid][1];//这个位置的结束时间
                if(maybe < end){
                    //可以选这个位置
                    res = mid;
                    left = mid + 1;
                }else {

                    right = mid - 1;
                }
            }

            return res;
        }


    }
}
