import java.util.Arrays;

public class p383 {

    /**
     * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
     *
     * 如果可以，返回 true ；否则返回 false 。
     *
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     */

    class Solution {
        public static int[] count = new int[26];
        public boolean canConstruct(String ransomNote, String magazine) {

            Arrays.fill(count,0);
            char[] r = ransomNote.toCharArray();
            char[] m = magazine.toCharArray();

            for(char c : r)
                count[c - 'a']++;
            for(char c : m)
                count[c - 'a']--;

            for(int a : count)
                if(a > 0)
                    return false;

            return true;
        }
    }
}
