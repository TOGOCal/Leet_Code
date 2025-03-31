public class p2278 {

    /**
     * 给你一个字符串 s 和一个字符 letter ，返回在 s 中等于 letter 字符所占的 百分比 ，向下取整到最接近的百分比。
     */

    class Solution {
        public int percentageLetter(String s, char letter) {

            int count = 0;
            for(char c : s.toCharArray())
                if(c == letter)
                    count++;

            //计算count / length
            count *= 100;
            int length = s.length();

            return count / length;
        }
    }
}
