public class P2414 {

    /**
     * 字母序连续字符串 是由字母表中连续字母组成的字符串。换句话说，
     * 字符串 "abcdefghijklmnopqrstuvwxyz" 的任意子字符串都是 字母序连续字符串 。
     *
     * 例如，"abc" 是一个字母序连续字符串，而 "acb" 和 "za" 不是。
     * 给你一个仅由小写英文字母组成的字符串 s ，返回其 最长 的 字母序连续子字符串 的长度。
     */

    public static void main(String[] args) {

        String s = "abcde";

        System.out.println(new Solution().longestContinuousSubstring(s));
    }


    static
    class Solution {
        public int longestContinuousSubstring(String s) {

            int left = 0;
            int right = 0;

            int max = Integer.MIN_VALUE;

            char[] str = s.toCharArray();

            for(int i = 0 ; i < str.length-1 ; i ++){

                if(str[i] + 1 == str[i+1]){

                    right ++;
                }else{

                    max = Math.max(max , right - left + 1);
                    left = i+1;
                    right = left;
                }
            }

            max = Math.max(max , right - left + 1);

            return max;
        }
    }
}
