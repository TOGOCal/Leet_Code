import java.util.Arrays;

public class p316 {

    /**
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的
     * 字典序最小（要求不能打乱其他字符的相对位置）。
     */

    public static void main(String[] args) {

        String s = "bcabc";

        System.out.println(new Solution().removeDuplicateLetters(s));;
    }

    static
    class Solution {

        public String removeDuplicateLetters(String s) {

            char[] str = s.toCharArray();

            int[] num = new int[26];

            for (char c : str) {

                num[c - 'a']++;
            }

            for(int i = 0 ; i < str.length -1 ; i++){

                if(str[i] > str[i+1]){

                    if(num[str[i] - 'a'] > 1){

                        num[str[i] - 'a'] --;
                        str[i] = 0;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();

            for (char c : str) {

                if (c != 0) {

                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }
}
