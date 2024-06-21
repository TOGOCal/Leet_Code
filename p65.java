import java.util.Scanner;

public class p65 {

    /**
     * 一般的，一个 有效数字 可以用以下的规则之一定义：
     * 一个 整数 后面跟着一个 可选指数。
     * 一个 十进制数 后面跟着一个 可选指数。
     * 一个 整数 定义为一个 可选符号 '-' 或 '+' 后面跟着 数字。
     * 一个 十进制数 定义为一个 可选符号 '-' 或 '+' 后面跟着下述规则：
     * 数字 后跟着一个 小数点 .。
     * 数字 后跟着一个 小数点 . 再跟着 数位。
     * 一个 小数点 . 后跟着 数位。
     * 指数 定义为指数符号 'e' 或 'E'，后面跟着一个 整数。
     * 数字 定义为一个或多个数位。
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        while(true){

            System.out.println(new Solution().isNumber(s.nextLine()));

        }


        //s.close();
    }

    static class Solution {
        public boolean isNumber(String s) {

            s = s.trim();

            char[] str = s.toCharArray();

            if(str.length == 1 && Character.isDigit(str[0])){

                return true;
            }

            int index = 0;
//            for(index = 0; index < str.length; index++){
//
//                if(str[index] != '0'){
//
//                    if( (str[index] == 'e' || str[index] == 'E'|| str[index] == '.') && index!=0 ){
//
//                        index --;
//                    }
//                    break;
//                }
//            }//消除前缀零

            return isInteger(str , index , str.length-1) ||
                    isDecimal(str , index , str.length-1) ||
                    isExponent(str , index , str.length-1);


        }

        //以下begin ,end 都是有效位置
        //一个 整数 定义为一个 可选符号 '-' 或 '+' 后面跟着 数字。
        public boolean isInteger(char[] str , int begin , int end){

            if(begin > end){

                return false;
            }
            
            if(str[begin] == '+' || str[begin] == '-'){

                begin++;
            }

            return isCheckNumber(str , begin , end);
        }

        /*
        一个 十进制数 定义为一个 可选符号 '-' 或 '+' 后面跟着下述规则：
        * 数字 后跟着一个 小数点 .。
        * 数字 后跟着一个 小数点 . 再跟着 数位。
        * 一个 小数点 . 后跟着 数位。
         */
        public boolean isDecimal(char[] str , int begin , int end){

            if(begin > end){

                return false;
            }

            if(str[begin] == '+' || str[begin] == '-'){

                begin++;
            }

            int i;
            for(i = begin; i <= end; i++){

                if(str[i] == '.'){

                    break;
                }
            }


            return  ( isCheckNumber(str , begin , i-1) && i == end ) ||
                    ( isCheckNumber(str , begin , i-1) && isCheckNumber(str, i+1 , end)) ||
                    ( i == begin && isCheckNumber(str , i+1 , end));

        }

        public boolean isCheckNumber(char[] str , int begin , int end){
            //数字 后跟着一个 小数点 .。
            //通过这句话可以得知数字的定义中不会有小数点
            if(begin > end){

                return false;
            }

            for(int i = begin; i <= end; i++){

                if(!Character.isDigit(str[i])){

                    return false;
                }
            }

            return true;
        }

        //是指数吗
        //一个正数或一个十进制数后面跟着一个指数都属于正确的范围
        public boolean isExponent(char[] str , int begin , int end){

            if(begin > end){

                return false;
            }

            int i;
            for(i = begin; i <= end; i++){

                if(str[i] == 'e' || str[i] == 'E'){

                    break;
                }
            }
            
            //保证前面是个十进制数或正数，后面是个整数
            return (isDecimal(str , begin , i-1) || isInteger(str , begin , i-1) ) && isInteger(str , i+1 , end);
        }

    }
}
