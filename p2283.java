import java.util.Arrays;

public class p2283 {

    /**
     * 给你一个下标从 0 开始长度为 n 的字符串 num ，它只包含数字。
     *
     * 如果对于 每个 0 <= i < n 的下标 i ，都满足数位 i 在 num 中出现了 num[i]次，那么请你返回 true ，否则返回 false 。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().digitCount("1210"));
    }


    static
    class Solution {

        public static int[] count = new int[10];
        public boolean digitCount(String num) {

            int realLength = num.length();
            Arrays.fill(count , 0);
            for(int i = 0 ; i < realLength ; i ++){

                int v = num.charAt(i) - '0';
                count[i] -= v;
                count[v] ++;
            }

            for(int i = 0 ; i < 10 ; i ++){

                if(count[i] != 0)
                    return false;
            }

            return true;
        }
    }
}
