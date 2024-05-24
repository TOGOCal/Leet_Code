import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p22 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        List<String> result = new Solution().generateParenthesis(n);

        for(String str : result){

            System.out.println(str);

        }


        s.close();
    }

    static class Solution {

        List<String> result;
        int N;
        public List<String> generateParenthesis(int n) {

            result = new ArrayList<>();
            N = n;
            mainMethod( 0 , 0 , new StringBuilder());
            return result;
        }

        public void mainMethod(int howManyBefore ,int howManyLast ,StringBuilder sb){
            //通过暴力递归进行构建   多少个前括号     多少个后括号

            if(howManyLast == N){

                result.add(sb.toString());
                return ;
            }

            //加入 ( 的情况

            if(howManyBefore < N){

                sb.append("(");
                mainMethod(howManyBefore+1,howManyLast,sb);
                sb.deleteCharAt(sb.length()-1);
            }
            //加入 ) 的情况

            if(howManyLast < howManyBefore){

                sb.append(")");
                mainMethod(howManyBefore,howManyLast+1,sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }


}
