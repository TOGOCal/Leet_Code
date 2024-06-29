import java.util.ArrayList;
import java.util.Scanner;

public class p60 {

    /**
     * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
     */

    /**
     * 分析：
     * n个数有n!种排列
     * 则以 1 开头的有(n-1)!种排列
     * 2开头的有(n-1)!种排列
     * 因此可以确定是由那个数开头的
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int k = s.nextInt();

        System.out.println(new Solution().getPermutation(n, k));


        s.close();
    }


    static class Solution {
        public String getPermutation(int n, int k) {

            ArrayList<Integer> list = new ArrayList<>();

            for(int i = 1; i <= n; i++){

                list.add(i);
            }

            //int[] nums = new int[n];
            k--;//因为是从0开始的
            StringBuilder sb = new StringBuilder();

            int factorial;
            for(int i = 1; i <= n; i++){

                factorial = factorial(n-i);

                int num = k/factorial;//现在还剩的数字中的第几个选在这里

                k %= factorial;

                sb.append(list.get(num));

                list.remove(num);
            }

            return sb.toString();

        }


        public int factorial(int n) {

            int res = 1;

            for(int i = 1; i <= n; i++){

                res *= i;
            }

            return res;
        }

    }


}
