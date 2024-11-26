public class p389 {

    /**
     * 给定两个字符串 s 和 t ，它们只包含小写字母。
     *
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     *
     * 请找出在 t 中被添加的字母。
     */
    class Solution {
        public char findTheDifference(String s, String t) {

            char res = t.charAt(t.length() - 1);
            for(int i = 0 ; i< s.length() ; i++){

                res ^= s.charAt(i);
                res ^= t.charAt(i);
            }

            return res;
        }

    }


//    class Solution {
//        public char findTheDifference(String s, String t) {
//
//            int[] arr = new int[26];
//            for(char c: s.toCharArray())
//                arr[c - 'a']++;
//            for(char c : t.toCharArray())
//                arr[c - 'a']--;
//            for(int i = 0 ; i < 26;i++)
//                if(arr[i] != 0)
//                    return (char)('a' + i);
//
//            return (char)-1;
//        }
//    }
}
