import java.util.Arrays;
import java.util.Comparator;

public class p1338 {

    /**
     * 给你一个整数数组 arr。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。
     *
     * 返回 至少 能删除数组中的一半整数的整数集合的最小大小。
     */

    class Solution {

        public static int MAXN = 1_00_000 + 1;
        //lpublic static int[][] indexValue = new int[MAXN][2];
        public static int[] indexValue = new int[MAXN];
        public int minSetSize(int[] arr) {

            for(int i = 0 ; i < MAXN ; i ++){

//                indexValue[i][0] = i;
//                indexValue[i][1] = 0;
                indexValue[i] = 0;
            }

            for(int a : arr)
                indexValue[a] ++;

            Arrays.sort(indexValue);

            int res = 0;
            int sum = 0;
            for(int i = MAXN - 1 ; i >= 0 ; i --){

                res ++;
                sum += indexValue[i];
                if(sum >= (arr.length >> 1))
                    break;
            }

            return res;
        }
    }
}
