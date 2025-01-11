import java.util.Arrays;

public class p316 {

    /**
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的
     * 字典序最小（要求不能打乱其他字符的相对位置）。
     */

    public static void main(String[] args) {

        String s = "bbcaac";

        System.out.println(new Solution().removeDuplicateLetters(s));;
    }

    static
    class Solution {
        //单调栈

        /**
         * 只允许新加入的是严格大于上一个的
         * 否则连续弹出
         * 知道某个元素达到极限（不能再删了，就维持这个元素前面的顺序（使这个元素的影响尽量小
         */
        public static int MAXN = 10001;
        public static char[] stack = new char[MAXN];
        public static int size = 0;

        public static int[] count = new int[256];
        public static boolean[] canBeChosen = new boolean[256];
        public static boolean[] inStack = new boolean[256];

        public String removeDuplicateLetters(String s) {

            //完成初始化
            size = 0;
            Arrays.fill(count , 0);
            Arrays.fill(canBeChosen , false);
            Arrays.fill(inStack , false);

            char[] str = s.toCharArray();

            for(char c : str){

                count[c] ++;
                canBeChosen[c] = true;
            }


            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < str.length ; i++){

                char thisChar = str[i];

                if((!canBeChosen[thisChar]) || inStack[thisChar]){
                    //之前已经选择了这个元素了
                    count[thisChar] --;
                    continue;
                }

                //弹出之前的
                while(size > 0 && stack[size - 1] >= thisChar){

                    if(count[stack[size - 1]] > 1){//上一个元素的数量大于1

                        char c = stack[--size];
                        //需要弹出
                        count[c] --;//直接删除这个元素
                        inStack[c] = false;
                    }else{
                        //需要触发清理程序//这个元素只有一个了，那就保持之前的顺序
                        StringBuilder temp = new StringBuilder();
                        //将前面的全部弹出（因为至少要保留这一个字符
                        while(size > 0){

                            char c = stack[--size];
                            inStack[c] = false;
                            if(canBeChosen[c]){
                                temp.append(c);
                                canBeChosen[c] = false;
                            }

                        }
                        temp.reverse();
                        sb.append(temp);
                    }

                }

                //加入当前字符
                stack[size++] = thisChar;
                inStack[thisChar] = true;
            }

            //处理剩下的，剩下的一定是从小到大排列的，所以只需要一个一个看就可以了
            for(int i = 0 ;i < size ; i++){

                if(canBeChosen[stack[i]]){

                    sb.append(stack[i]);
                    canBeChosen[stack[i]] = false;
                }
            }

            return sb.toString();
        }
    }
}
