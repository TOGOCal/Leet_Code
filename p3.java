
/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长
 * 子串
 * 的长度。
 * 
 * 示例 1:
 * 
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 
 */

import java.util.HashMap;
import java.util.Scanner;

public class p3 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String str = s.nextLine();

        Solution3 sl = new Solution3();
        int a = sl.lengthOfLongestSubstring(str);

        // int num = s.nextInt();

        // for (int i = 0; i < num; i++) {

        // StringBuilder sb = new StringBuilder();
        // for (int ii = 0; ii < 10; ii++) {

        // sb.append((char) (((int) Math.random() * 26) + 'a'));
        // }
        // String str = sb.toString();
        // int a = sl.lengthOfLongestSubstring(str);
        // int b = sl.usepower(str);

        // if (a != b) {

        // System.out.println("对于字符串:\n" + str);
        // System.out.println("两种计算方式分别为:" + a + " || " + b);
        // }
        // }

        System.out.println(a);
        s.close();
    }
}

class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> indexMap = new HashMap<>();

        int max = 0;
        int l = 0;

        int index = 0;
        for (int i = 0; i < s.length(); i++) {

            char a = s.charAt(i);
            if (indexMap.containsKey(a)) {

                max = Math.max(max, l);
                int p = indexMap.get(a);
                for (int ii = index; ii <= p; ii++) {

                    indexMap.remove(s.charAt(ii));
                    l--;
                }

                index = p + 1;
                indexMap.put(a, i);
                l++;

            } else {

                indexMap.put(a, i);
                l++;
            }
        }

        max = Math.max(max, l);

        return max;
    }

}