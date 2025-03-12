import java.util.Arrays;
import java.util.List;

public class p539 {

    /**
     * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
     */

    class Solution {
        public static int MAXN = 20_001;
        public static int[] arr = new int[MAXN << 1];
        public static int day = 24 * 60;
        public int findMinDifference(List<String> timePoints) {

            int index = 0;
            for(String s : timePoints){

                int t = getTime(s);
                arr[index << 1] = t;
                arr[index << 1 | 1] = t + day;
                index++;
            }

            int l = timePoints.size() << 1;
            Arrays.sort(arr , 0 , l);
            int res = Integer.MAX_VALUE;
            for(int i = 1 ; i < l ; i ++){

                int diff = arr[i] - arr[i - 1];
                if(diff < res)
                    res = diff;
            }

            return res;
        }

        public int getTime(String str){

            return  ((str.charAt(0) - '0') * 10 + (str.charAt(1) - '0')) * 60 +
                    (str.charAt(3) - '0') * 10 + (str.charAt(4) - '0');
        }
    }
}
