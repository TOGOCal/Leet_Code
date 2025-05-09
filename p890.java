import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class p890 {

    /**
     * 你有一个单词列表 words 和一个模式  pattern，你想知道 words 中的哪些单词与模式匹配。
     *
     * 如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。
     *
     * （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
     *
     * 返回 words 中与给定模式匹配的单词列表。
     *
     * 你可以按任何顺序返回答案。
     */

    class Solution {

        public static int[] map = new int[26];
        public static int[] found = new int[26];

        public List<String> findAndReplacePattern(String[] words, String pattern) {

            List<String> res = new LinkedList<>();
            char[] p = pattern.toCharArray();
            for(String word : words){

                Arrays.fill(map , -1);
                Arrays.fill(found , -1);

                char[] crr = word.toCharArray();
                boolean can = true;
                for(int i = 0 ; i < crr.length ;i++){

                    //这个单词已有映射
                    if(map[p[i] - 'a'] != -1){

                        if(map[p[i] - 'a'] != crr[i] - 'a'){

                            can = false;
                            break;
                        }
                    }else{

                        if(found[crr[i] - 'a'] != -1){

                            if(map[crr[i] -'a'] != p[i] - 'a'){
                                can = false;
                                break;
                            }
                        }
                        map[p[i] -'a'] = crr[i] - 'a';//进行映射
                        found[crr[i] - 'a'] = p[i] - 'a';
                    }
                }

                if(can)
                    res.add(word);
            }

            return res;
        }
    }
}
