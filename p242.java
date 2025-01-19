package from_200_to_300;

import java.util.Arrays;

public class p242 {

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的
     * 字母异位词
     */

    class Solution {

        public static int[] count = new int[26];
        public boolean isAnagram(String s, String t) {

            //Arrays.fill(count , 0);

            for(char c : s.toCharArray())
                count[c - 'a']++;


            for(char c : t.toCharArray())
                count[c - 'a']--;


            boolean key = true;
            for(int i = 0 ; i < 26 ; i++){

                if(count[i] != 0)
                    key = false;
                count[i] = 0;
            }

            return key;
        }
    }
}
