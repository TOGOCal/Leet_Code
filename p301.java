import java.util.List;

public class p301 {

    /**
     * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
     *
     * 返回所有可能的结果。答案可以按 任意顺序 返回。
     */

    class Solution {

        public static int MAXN = 27;
        public static final int[] indexArray = new int[MAXN];
        int usefulLength;

        public List<String> removeInvalidParentheses(String s) {

            char[] str = s.toCharArray();

            int beginPlace =0;
            int endPlace = 0;
            int i;
            for(i =0 ;i < s.length() ; i ++){

                if(str[i] == '('){

                    break;
                }
            }

            beginPlace = i;
            for(i = s.length() -1 ; i >=0;i--){

                if(str[i] == ')'){

                    break;
                }
            }
            endPlace = i;//只有end与begin之间的括号才是有可能有效的

            usefulLength = 0;
            int numPre = 0;
            int numLater = 0;
            for(i = beginPlace ; i <= endPlace ; i ++){

                if(str[i] == '(' || str[i] == ')'){

                    if(str[i] == '('){

                        numPre ++;
                    }

                    if(str[i] == ')'){

                        numLater ++;
                    }

                    indexArray[usefulLength++] = i;
                }
            }

            return null;
        }
    }
}
