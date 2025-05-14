import java.util.Arrays;

public class p1540 {
    /**
     * 给你两个字符串 s 和 t ，你的目标是在 k 次操作以内把字符串 s 转变成 t 。
     *
     * 在第 i 次操作时（1 <= i <= k），你可以选择进行如下操作：
     *
     * 选择字符串 s 中满足 1 <= j <= s.length 且之前未被选过的任意下标 j （下标从 1 开始），并将此位置的字符切换 i 次。
     * 不进行任何操作。
     * 切换 1 个字符的意思是用字母表中该字母的下一个字母替换它（字母表环状接起来，所以 'z' 切换后会变成 'a'）。第 i 次操作意味着该字符应切换 i 次
     *
     * 请记住任意一个下标 j 最多只能被操作 1 次。
     *
     * 如果在不超过 k 次操作内可以把字符串 s 转变成 t ，那么请你返回 true ，否则请你返回 false 。
     */

    class Solution {

        //public static int MAXN = 1_00_000 + 1;
        public static int[] needChangeNum = new int[26];//需要变化i次的字符有多少个

        public boolean canConvertString(String s, String t, int k) {
            if(s.length() != t.length())
                return false;

            Arrays.fill(needChangeNum, 0);
            for(int i = 0 ; i < s.length() ; i ++){

                int need = (t.charAt(i) - s.charAt(i) + 26) % 26;
                needChangeNum[need] ++;
            }

            int times = k / 26;
            int last = k % 26;

            //切换0次的不用考虑
            for(int i = 1 ; i < 26 ; i ++){

                if(needChangeNum[i] <= times)
                    continue;//可行情况

                if(needChangeNum[i] == times + 1 && i <= last)
                    continue;

                return false;
            }

            return true;
        }
    }
}