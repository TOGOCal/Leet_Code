import java.util.Scanner;

public class p38 {

    /**
     * 「外观数列」是一个数位字符串序列，由递归公式定义：
     *
     * countAndSay(1) = "1"
     * countAndSay(n) 是 countAndSay(n-1) 的行程长度编码。
     *
     * 行程长度编码（RLE）是一种字符串压缩方法，
     * 其工作原理是通过将连续相同字符（重复两次或更多次）替换为字符重复次数（运行长度）和字符的串联。
     * 例如，要压缩字符串 "3322251" ，
     * 我们将 "33" 用 "23" 替换，将 "222" 用 "32" 替换，将 "5" 用 "15" 替换并将 "1" 用 "11" 替换。
     * 因此压缩后字符串变为 "23321511"。
     */

    public static void main(String[] args) {

        Scanner s= new Scanner(System.in);

        int num = s.nextInt();

        System.out.println(new Solution().countAndSay(num));

        s.close();
    }

    static class Solution {
        public String countAndSay(int n) {

            if(n==0){

                return "";
            }

            return countAndSay1(n).toString();
        }

        public StringBuilder countAndSay1(int n) {

            if(n==1){

                return new StringBuilder().append('1');
            }

            return method(countAndSay1(n-1));
        }

        public StringBuilder method(StringBuilder str){
            //加工单个字符串
           StringBuilder sb = new StringBuilder();

           //char[] s= str.toCharArray();

           int count = 1;
           char c = str.charAt(0);
           for(int i=1;i<str.length();i++){

               char c1 = str.charAt(i);
               if(c1 == c){

                   count++;
               }else{

                   sb.append(count);
                   sb.append(c);

                   count = 1;
                   c = c1;
               }
           }


           if(count != 0){

               sb.append(count);
               sb.append(c);
           }

           return sb;
        }
    }
}
