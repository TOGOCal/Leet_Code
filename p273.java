package from_200_to_300;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class p273 {

    /**
     * 将非负整数 num 转换为其对应的英文表示。
     */


    public static void main(String[] args) throws IOException {

        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        while(true){

            st.nextToken();
            String s = new Solution().numberToWords((int)st.nval);
            System.out.println(s);
        }
    }


    static
    class Solution {

        public static String[] dictionary = new String[]{"Billion" , "Million" , "Thousand" , "Hundred"};
        public static int[] number = new int[]{1000000000 , 1000000 , 1000 , 100};

        public static String[] dictionaryTy = new String[]{"Ninety" , "Eighty" , "Seventy" , "Sixty" , "Fifty" , "Forty" , "Thirty" , "Twenty"};
        public static int[] numberTy = new int[]{90 , 80 , 70 , 60 , 50 , 40 , 30 , 20};

        public static String[] dictionaryTeen = new String[]{"Ten" , "Eleven" , "Twelve" , "Thirteen" , "Fourteen" , "Fifteen" , "Sixteen" , "Seventeen" , "Eighteen" , "Nineteen"};
        public static int[] numberTeen = new int[]{10 , 11 , 12 , 13 , 14 , 15 , 16 , 17 , 18 , 19};

        public static String[] dictionaryN = new String[]{"Zero" , "One" , "Two" , "Three" , "Four" ,"Five" ,"Six" , "Seven" , "Eight" , "Nine"};

        public String numberToWords(int num) {

            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < dictionary.length - 1; i ++){

                int a = num / number[i];
                if(a > 0){

                    if(!sb.isEmpty())
                        sb.append(" ");

                    sb.append(functionHundred(a)).append(" ").append(dictionary[i]);
                }
                num -= a * number[i];
            }


            String s = functionHundred(num);
            if(!sb.isEmpty()){

                if(num == 0)
                    return sb.toString();
                sb.append(" ");
            }
            sb.append(s);

            return sb.toString();
        }

        //处理千位数
//        public String functionThousand(int n){
//            //这确实是个千位数
//            StringBuilder sb = new StringBuilder();
//            if(n >= 1000){
//
//                int a = n / 1000;
//                sb.append(dictionaryN[n]).append(" ").append(dictionary[2]);
//                n -= a * 1000;
//            }
//
//            if(n == 0){
//
//                if(!sb.isEmpty())
//                    return sb.toString();
//                else
//                    return dictionaryN[0];
//            }
//
//            String next = functionHundred(n);
//            sb.append(" ").append(next);
//            return sb.toString();
//        }

        public String functionHundred(int n){

            StringBuilder sb = new StringBuilder();
            if(n >= 100){
                int a = n / 100;
                sb.append(dictionaryN[a]).append(" ").append(dictionary[3]);
                n -= a * 100;
            }

            if(n == 0){

                if(!sb.isEmpty())
                    return sb.toString();
                else
                    return dictionaryN[0];
            }

            String next = functionTen(n);
            if(!sb.isEmpty())
                sb.append(" ");
            sb.append(next);
            return sb.toString();
        }

        public String functionTen(int n){

            if(10 <= n && n < 20){

                return dictionaryTeen[n - 10];
            }else if(n < 10){

                return dictionaryN[n];
            }

            int a = n / 10;
            StringBuilder sb = new StringBuilder();
            sb.append(dictionaryTy[9 - a]);
            n -= a * 10;
            if(n == 0){

                if(!sb.isEmpty())
                    return sb.toString();
                else
                    return dictionaryN[0];
            }

            if(!sb.isEmpty())
                sb.append(" ");
            sb.append(dictionaryN[n]);
            return sb.toString();
        }
    }
}
