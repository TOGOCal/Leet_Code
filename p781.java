package from_700_to_800;

import java.util.Arrays;

public class p781 {

    /**
     * 森林中有未知数量的兔子。提问其中若干只兔子 "还有多少只兔子与你（指被提问的兔子）颜色相同?" ，将答案收集到一个整数数组 answers 中，其中 answers[i] 是第 i 只兔子的回答。
     *
     * 给你数组 answers ，返回森林中兔子的最少数量。
     */

    class Solution {

        public static int MAXN = 1001;
        public static int[] count = new int[MAXN];//回答答案为index的兔子数量

        public int numRabbits(int[] answers) {

            Arrays.fill(count , 0);
            for(int a : answers)
                count[a]++;

            int res = 0;
            for(int i = 0 ; i < MAXN ; i ++){

                res += ((count[i] + i) / (i + 1)) * (i + 1);
            }

            return res;
        }
    }
}
