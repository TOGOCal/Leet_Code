package from_400_to_500;

public class p423 {

    /**
     * 给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。
     */


    public static void main(String[] args) {

        System.out.println(new Solution().originalDigits("nnie"));
    }


    static
    //zero one two three four five six seven eight nine
    //其中x一定属于6  u一定属于4 w一定属于2 z一定属于0
    //three five seven eight nine
    //现在f一定属于5 s一定属于7 o一定属于1
    //three eight nine
    //其中n一定属于9
    //three eight
    //通过鸡兔同笼构建t和e的数量关系
    class Solution {

        public static String[] n = new String[]{"zero", "one","two","three","four","five","six","seven","eight","nine"};
        public static char[][] num = new char[10][];
        static {

            for(int i = 0 ; i < 10 ; i++){

                num[i] = n[i].toCharArray();
            }
//            num[0] = "zero".toCharArray();
//            num[1] = "one".toCharArray();
//            num[2] = "two".toCharArray();
//            num[3] = "three".toCharArray();
//            num[4] = "four".toCharArray();
//            num[5] = "five".toCharArray();
//            num[6] = "six".toCharArray();
//            num[7] = "seven".toCharArray();
//            num[8] = "eight".toCharArray();
//            num[9] = "nine".toCharArray();
        }
        public String originalDigits(String s) {

            int[] res = new int[10];
            int[] arr = new int[26];
            for(char c : s.toCharArray())
                arr[c - 'a'] ++;

            if(arr['x' - 'a'] > 0){

                int c = arr['x' - 'a'];
                for(char a : num[6])
                    arr[a - 'a'] -= c;
                res[6] += c;
            }

            if(arr['u' - 'a'] > 0){

                int c = arr['u' - 'a'];
                for(char a : num[4])
                    arr[a - 'a'] -= c;
                res[4] += c;
            }

            if(arr['w' - 'a'] > 0){

                int c = arr['w' - 'a'];
                for(char a : num[2])
                    arr[a - 'a'] -= c;
                res[2] += c;
            }

            if(arr['z' - 'a'] > 0){

                int c = arr['z' - 'a'];
                for(char a : num[0])
                    arr[a - 'a'] -= c;
                res[0] += c;
            }


            //===========================
            if(arr['f' - 'a'] > 0){

                int c = arr['f' - 'a'];
                for(char a : num[5])
                    arr[a - 'a'] -= c;
                res[5] += c;
            }

            if(arr['s' - 'a'] > 0){

                int c = arr['s' - 'a'];
                for(char a : num[7])
                    arr[a - 'a'] -= c;
                res[7] += c;
            }

            if(arr['o' - 'a'] > 0){

                int c = arr['o' - 'a'];
                for(char a : num[1])
                    arr[a - 'a'] -= c;
                res[1] += c;
            }

            //==============================
            if(arr['n' - 'a'] > 0){

                int c = arr['n' - 'a'] /2;
                for(char a : num[9])
                    arr[a - 'a'] -= c;
                res[9] += c;
            }

            if(arr['e' - 'a'] > 0){

                int count = arr['e' - 'a'] - arr['t' - 'a'];
                res[3] += count;
                res[8] += arr['t' - 'a'] - count;
            }

            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < 10; i ++){

                sb.append(String.valueOf(i).repeat(Math.max(0, res[i])));
            }

            return sb.toString();
        }
    }
}
