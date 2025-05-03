package from_2700_to_2800;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class p2800 {

    /**
     * 给你三个字符串 a ，b 和 c ， 你的任务是找到长度 最短 的字符串，且这三个字符串都是它的 子字符串 。
     * 如果有多个这样的字符串，请你返回 字典序最小 的一个。
     *
     * 请你返回满足题目要求的字符串。
     *
     * 注意：
     *
     * 两个长度相同的字符串 a 和 b ，如果在第一个不相同的字符处，a 的字母在字母表中比 b 的字母 靠前 ，那么字符串 a 比字符串 b 字典序小 。
     * 子字符串 是一个字符串中一段连续的字符序列。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().minimumString("ccba" , "cccb" , "a"));
    }


    static
    class Solution {

        public static int MAXN = 3;
        public static int[][] same = new int[MAXN][MAXN];//i的字符串结尾和j字符串的开头的重叠部分有多长

        public static String[] record = new String[MAXN];
        public static String[] res = new String[MAXN << 1];
        public static int num = 0;

        public static int BASE = 131;
        public static int MAXL = 101;
        public static int[] power = new int[MAXL];
        public static int[][] str = new int[MAXN][MAXL];
        public static int[] realLength = new int[MAXN];
        static {

            power[0] = 1;
            for(int i = 1 ; i < MAXL ; i ++)
                power[i] = power[i - 1] * BASE;

        }

        private int v(char a){

            return a - 'a' + 1;
        }

        private void function(int id , String string){

            record[id] = string;
            char[] crr = string.toCharArray();
            int[] s = str[id];


            realLength[id] = crr.length;
            if(crr.length == 0)
                return;

            s[0] = v(crr[0]);
            for(int i = 1 ; i < crr.length ; i ++){

                s[i] = s[i - 1] * BASE + v(crr[i]);
            }

        }

        //某个字符串前index个位置
        private int head(int id , int index){

            return get(str[id] , 0 , index);
        }

        //从index位置到tail位置的字符串
        private int tail(int id , int index){

            return get(str[id] , index , realLength[id] - 1);
        }

        private int get(int[] str , int begin ,int end){

            return begin == 0 ? str[end] :
                    (str[end] - (str[begin - 1] * power[end - begin + 1]));
        }


        public String minimumString(String a, String b, String c) {

            if(a.equals(b))
                b = "";
            if(a.equals(c))
                c = "";
            if(b.equals(c))
                c = "";

            if(a.contains(b) || c.contains(b))
                b = "";
            if(a.contains(c) || b.contains(c))
                c = "";
            if(b.contains(a) || c.contains(a))
                a = "";



            function(0 , a);
            function(1 , b);
            function(2 , c);
            int allLength = realLength[0] + realLength[1] + realLength[2];

            for(int i = 0 ; i < MAXN ; i ++){

                for(int j = 0 ; j < MAXN ; j ++){
                    if(i == j)
                        continue;

                    int length = 0;
                    int res = 0;
                    while(length < Math.min(realLength[i], realLength[j])){
                        if(tail(i , realLength[i] - 1 - length) == head(j , length))
                            res = length + 1;
                        length++;
                    }

                    same[i][j] = res;
                }
            }


            num = 0;
            int min = allLength;
            for(int i = 0 ; i < MAXN ; i ++){

                for(int j = 0 ; j < MAXN ; j ++){

                    if(i != j){

                        for(int k = 0 ; k < MAXN ; k ++){

                            //这是一种合理的排列
                            if(i != k && j != k){

                                StringBuilder r = new StringBuilder();
                                int l1 = same[i][j];
                                int l2 = same[j][k];
                                int length = allLength - l1 - l2;
                                if(length < min){

                                    min = length;
                                    num = 0;
                                    r.append(record[i], 0, realLength[i] - l1);
                                    r.append(record[j], 0, realLength[j] - l2);
                                    r.append(record[k]);

                                    res[num++] = r.toString();
                                }else if(length == min){

                                    r.append(record[i], 0, realLength[i] - l1);
                                    r.append(record[j], 0, realLength[j] - l2);
                                    r.append(record[k]);
                                    res[num++] = r.toString();
                                }
                            }
                        }
                    }
                }
            }


            Arrays.sort(res, 0, num);

            return res[0];
        }
    }
}
