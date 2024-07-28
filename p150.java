import java.util.Scanner;
import java.util.Stack;

public class p150 {

    /**
     * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
     * 请你计算该表达式。返回一个表示表达式值的整数。
     *
     * 注意:
     * 有效的算符为 '+'、'-'、'*' 和 '/' 。
     * 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
     * 两个整数之间的除法总是 向零截断 。
     * 表达式中不含除零运算。
     * 输入是一个根据逆波兰表示法表示的算术表达式。
     * 答案及所有中间计算结果可以用 32 位 整数表示。
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        String[] tokens = s.nextLine().split(" ");

        Solution solution = new Solution();

        System.out.println(solution.evalRPN(tokens));

        s.close();

    }

    static
    class Solution {

        String[] tokens;
        int index;
        public int evalRPN(String[] tokens) {

            this.tokens = tokens;
            index = tokens.length -1;


            return dfs();
        }

        public int dfs(){

            switch (tokens[index--]){

                case "+":

                    return dfs() + dfs();

                case "-":

                    return - dfs() + dfs();


                case "*":

                    return dfs() * dfs();

                case "/":

                    int d = dfs();
                    return dfs() / d;
            }

            return Integer.parseInt(tokens[index + 1]);

        }

    }

//    static
//    class Solution {
//        public int evalRPN(String[] tokens) {
//
//            Stack<Integer> preNumber = new Stack<>();
//
//            for(String token : tokens){
//
//                char first = token.charAt(0);
//
//                if( token.length() == 1 && ( first == '+' || first == '-' || first == '*' || first == '/')){
//                    //b / a
//
//                    int a = preNumber.pop();
//                    int b = preNumber.pop();
//
//
//                    switch(first){
//
//                        case '+':
//                            preNumber.push(a + b);
//                            break;
//
//                        case '-':
//                            preNumber.push(b - a);
//                            break;
//
//                        case '*':
//                            preNumber.push(a * b);
//                            break;
//
//                        case '/':
//                            preNumber.push(b / a);
//                            break;
//
//                    }
//
//                }else{
//
//                    preNumber.push(Integer.parseInt(token));
//                }
//
//            }
//
//            return preNumber.pop();
//        }
//    }
}
