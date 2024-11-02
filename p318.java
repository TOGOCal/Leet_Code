public class p318 {

    /**
     * 给你一个字符串数组 words ，找出并返回 length(words[i]) * length(words[j]) 的最大值，
     * 并且这两个单词不含有公共字母。如果不存在这样的两个单词，返回 0 。
     */


    public static void main(String[] args) {

        Solution s = new Solution();

        System.out.println(s.maxProduct(new String[]{"abcw","baz","foo","bar","xtfn","abcdef"}));
    }

    static
    class Solution {

        public static int MAXN = 1001;
        public static int[] wordLength = new int[MAXN];
        public static int[] word = new int[MAXN];
        public static int length;


        public int maxProduct(String[] words) {

            length = words.length;

            for (int i = 0; i < length; i++) {

                String str = words[i];
                int a = 0;

                for(char c : str.toCharArray()){

                    a |= (1 << (c - 'a'));
                }

                word[i] = a;
                wordLength[i] = str.length();
            }//完成每个元素占位的判断

            int max = 0;
            for(int i = 0; i < length;i++){

                for(int j = i + 1; j < length ; j++){

                    if((word[i] & word[j]) == 0){

                        int r = wordLength[i] * wordLength[j];
                        if(r > max){

                            max = r;
                        }
                    }
                }
            }

            return max;
        }
    }
}
