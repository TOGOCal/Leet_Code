public class p3210 {

    /**
     * 给你一个字符串 s 和一个整数 k。请你使用以下算法加密字符串：
     *
     * 对于字符串 s 中的每个字符 c，用字符串中 c 后面的第 k 个字符替换 c（以循环方式）。
     * 返回加密后的字符串。
     */

    class Solution {
        public String getEncryptedString(String s, int k) {

            char[] str = s.toCharArray();
            char[] res = new char[s.length()];

            for(int i = 0 ; i < str.length ; i ++)
                res[i] = str[(i + k) % str.length];

            return new String(res);
        }
    }
}
