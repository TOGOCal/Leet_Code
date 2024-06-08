import java.util.ArrayList;
import java.util.List;

public class p17 {

    public static void main(String[] args) {

    }

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     */

    class Solution {

        List<String> res = new ArrayList<>();
        public List<String> letterCombinations(String digits) {

            if(digits==null || digits.isEmpty()) {

                return res;
            }

            char[] str= digits.toCharArray();

            backtracking(str,0,new StringBuilder());

            return res;
        }

        /**
         * 对照表：
         * 2:abc
         * 3:def
         * 4:ghi
         * ……
         * 不难发现，对应的字母是 (n-2)*3 + a ~ (n-2)*3 + a+2 的范围上
         */

        public void backtracking(char[] str,int index,StringBuilder sb) {

            if(index == str.length){

                res.add(sb.toString());
                return;
            }

            char c = str[index];

            int num = 3;

            if(c == '7' || c == '9'){

                num = 4;
            }

            int thisChar = 'a' + (c-'2')*3;
            if(c > '7'){

                thisChar+=1;//7占据了4个位置
            }

            for(int i =0; i < num; i++){

                sb.append( (char)(thisChar +i ) );
                backtracking(str,index+1,sb);
                sb.deleteCharAt(sb.length()-1);
            }

        }



    }
}
