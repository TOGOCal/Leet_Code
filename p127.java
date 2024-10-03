import java.util.*;

public class p127 {

    /**
     * 字典 wordList 中从单词 beginWord 到 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
     * <p>
     * 每一对相邻的单词只差一个字母。
     * 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
     * sk == endWord
     * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，
     * 返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
     */


    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dog",  "tog", "cog");

        Solution solution = new Solution();
        System.out.println(solution.ladderLength(beginWord, endWord, wordList));
    }


    static
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            int length = beginWord.length();

            HashSet<String> set = new HashSet<>(wordList);

            if(!set.contains(endWord)) return 0;

            List<HashSet<String>> beginLevels = new ArrayList<>();
            beginLevels.add(new HashSet<>());
            beginLevels.add(new HashSet<>());

            List<HashSet<String>> endLevels = new ArrayList<>();
            endLevels.add(new HashSet<>());
            endLevels.add(new HashSet<>());

            int key = 0;
            //开始建图
            char[] begin = beginWord.toCharArray();

            for(int i = 0 ; i < length ; i ++){

                char notOk = begin[i];

                for(char c = 'a' ; c <= 'z' ; c ++){

                    if(c != notOk){

                        begin[i] = c;

                        String str = String.valueOf(begin);

                        if(set.contains(str)){

                            beginLevels.get(key).add(str);
                            set.remove(str);
                        }
                    }
                }

                begin[i] = notOk;

            }//完成第一层的后一层建立

            if(beginLevels.get(key).contains(endWord)) return 2;

            //完成最后一层的前一层建立
            char[] end = endWord.toCharArray();
            for(int i = 0 ; i < length ; i ++){

                char notOk = end[i];

                for(char c = 'a' ; c <= 'z' ; c ++){

                    if(c != notOk){

                        end[i] = c;

                        String str = String.valueOf(end);

                        if(beginLevels.get(key).contains(str)){

                            return 3;//在建立这一层的时候发现可以打到的
                        }

                        if(set.contains(str)){

                            endLevels.get(key).add(str);
                            set.remove(str);
                        }
                    }
                }

                end[i] = notOk;

            }


            //执行双向广搜
            int beginLevelKey = key;
            int endLevelKey = key;

            int level = 4;//之后都从4开始了
            while(true) {

                List<HashSet<String>> small;
                List<HashSet<String>> big;

                int smallKey;
                int bigKey;

                if(beginLevels.get(beginLevelKey).size() > endLevels.get(endLevelKey).size()){

                    small = endLevels;
                    big = beginLevels;

                    smallKey = endLevelKey;

                    endLevelKey ^=1;

                    bigKey = beginLevelKey;

                }else{

                    small = beginLevels;
                    big = endLevels;

                    smallKey = beginLevelKey;

                    beginLevelKey ^=1;

                    bigKey = endLevelKey;
                }



                if(small.get(smallKey).isEmpty()) return 0;

                //堆小的执行扩容

                for(String str : small.get(smallKey)){

                    char[] s = str.toCharArray();

                    for(int i = 0 ; i < length ; i ++) {

                        char notOk = s[i];

                        for (char c = 'a'; c <= 'z'; c++) {

                            if (c != notOk) {

                                s[i] = c;
                                String string = String.valueOf(s);
                                if (big.get(bigKey).contains(string)) {

                                    return level;//在建立这一层的时候发现可以
                                }

                                if (set.contains(string)) {

                                    small.get(smallKey ^ 1).add(string);
                                    set.remove(string);
                                }
                            }
                        }

                        s[i] = notOk;
                    }
                }

                small.get(smallKey).clear();

                level ++;
            }


        }

    }

}