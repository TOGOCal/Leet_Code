import java.util.Arrays;
import java.util.Scanner;

public class p28 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        String str1= s.nextLine();
        String str2 = s.nextLine();


        Solution solution = new Solution();

        System.out.println(solution.strStr(str1,str2));

        s.close();
    }

    static
    class Solution {
        public int strStr(String haystack, String needle) {

            if(haystack == null || needle == null || haystack.isEmpty() || needle.isEmpty() ||needle.length() > haystack.length()) return -1;

            if(haystack.equals(needle)) return 0;

            build(haystack);

            return found(needle , haystack.length());
        }

        public static int MAXN = 10001;//题目给定的最大长度
        public static int BASE = 131;


        public static long[] power = new long[MAXN];
        public static long[] hash = new long[MAXN];


        public int found(String s , int originalLength){

            //计算s的哈希函数
            long sHash = stringHashCode(s);
            int length = s.length();

            for(int i = 0 ; i < originalLength ; i++){

                long thisHash = get(i , i + length - 1);
                if(thisHash == sHash){

                    return i;
                }
            }

            return -1;
        }

        public long get(int l , int r){

            //防止超出界限
            long res = hash[r];
            if(l > 0) res -= hash[l - 1] * power[r - l + 1];

            return res;
        }


        private long stringHashCode(String s){

            long res=0;

            for(int i = 0 ; i < s.length() ; i++){

                res = res * BASE + v(s.charAt(i));
            }

            return res;
        }

        private void build(String s){

            Arrays.fill(hash, 0);//初始化，能够复用的只有power

            hash[0] = v(s.charAt(0));//第一个字符的hash值
            for(int i = 1 ; i < s.length() ; i++){

                hash[i] = hash[i - 1] * BASE + v(s.charAt(i));//溢出部分就等他自然溢出
            }//完成计算结果
        }

        private int v(char a){

            return a - 'a' + 1;
        }

        Solution(){

            power[0] = 1;
            for(int i = 1 ; i < MAXN ; i++){

                power[i] = power[i-1] * BASE;
            }
        }
    }



//    static
//    class Solution {
//        public int strStr(String haystack, String needle) {
//
//            if(haystack == null || needle == null || haystack.isEmpty() || needle.isEmpty() ||needle.length() > haystack.length()) return -1;
//
//            char[] bigger = haystack.toCharArray();
//            char[] smaller = needle.toCharArray();
//
//            if(smaller.length == 1) {
//
//                char aim = smaller[0];
//                for (int i = 0; i < bigger.length; i++) {
//
//                    if (bigger[i] == aim) return i;
//                }
//
//                return -1;
//            }//一定保证长度大于等于2
//
//            int[] maxBeforeLength = maxBeforeLength(smaller);
//
//
//            int p1 = 0;
//            int p2 = 0;
//
//            while(p1 < bigger.length && p2 < smaller.length){
//
//                if(bigger[p1] == smaller[p2]){
//
//                    p1++;
//                    p2++;
//                }else if(maxBeforeLength[p2] == -1){
//
//                    p1++;
//                }else{
//
//                    p2 = maxBeforeLength[p2];
//                }
//
//            }
//
//            return p2 == smaller.length ? p1 - p2 : -1;
//
//        }
//
//        public int[] maxBeforeLength(char[] arr){
//            //每个位置的最长前缀
//
//            int[] result = new int[arr.length];
//
//            result[0] = -1;
//            result[1] = 0;
//
//            int cn = 0;
//            int index = 2;
//            //cn为已知前面的最长相同子串长度
//
//            while( index < arr.length ){
//
//                if(arr[cn] == arr[index -1]){
//
//                    result[index] = cn +1;
//                    index++;
//                    cn++;
//                }else if(cn > 0){
//
//                    cn = result[cn];
//                }else{
//
//                    result[index++] = 0;
//                }
//
//            }
//
//            return result;
//
//        }
//    }
}
