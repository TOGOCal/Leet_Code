import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class p648 {

    /**
     * 在英语中，我们有一个叫做 词根(root) 的概念，可以词根 后面 添加其他一些词组成另一个较长的单词——我们称这个词为 衍生词 (derivative)。例如，词根 help，跟随着 继承词 "ful"，可以形成新的单词 "helpful"。
     *
     * 现在，给定一个由许多 词根 组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有 衍生词 用 词根 替换掉。如果 衍生词 有许多可以形成它的 词根，则用 最短 的 词根 替换它。
     *
     * 你需要输出替换之后的句子。
     */


    class Solution {

        public static int BASE = 131;
        public static int MAXN = 100 | 1;
        public static int[] pow = new int[MAXN];
        static {

            pow[0] = 1;
            for(int i = 1 ; i < MAXN ; i ++)
                pow[i] = pow[i - 1] * BASE;
        }

        public int hashcode(String s){

            char[] str = s.toCharArray();
            int ans = 0;
            for(char c : str){

                ans *= BASE;
                ans += c - 'a' + 1;
            }

            return ans;
        }


        public String replaceWords(List<String> dictionary, String sentence) {

            HashMap<Integer , String> map = new HashMap<>();
            for(String s : dictionary)
                map.put(hashcode(s) , s);


            String[] words = sentence.split(" ");

            StringBuilder res = new StringBuilder();
            for(String word : words){

                int ans = 0;
                boolean key = true;
                for(char c : word.toCharArray()){

                    ans *= BASE;
                    ans += c - 'a' + 1;
                    if(map.containsKey(ans)){
                        res.append(map.get(ans));
                        key = false;
                        break;
                    }
                }

                if(key)
                    res.append(word);

                res.append(" ");
            }
            res.setLength(res.length() - 1);

            return res.toString();
        }
    }
}
