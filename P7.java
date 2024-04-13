import java.util.Scanner;

public class P7 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // StringBuilder sb = new StringBuilder(s.nextLine());

        // if (sb.charAt(0) == '-') {

        // sb.delete(0, 1);
        // sb.append('-');
        // sb.reverse();
        // } else {
        // sb.reverse();
        // }

        // if (Long.valueOf(sb.toString()) > Short.MAX_VALUE ||
        // Long.valueOf(sb.toString()) < Short.MIN_VALUE) {

        // System.out.println("0");
        // }

        // else {

        // System.out.println(sb.toString());
        // }

        int x = s.nextInt();

        System.out.println(Solution.reverse(x));
        s.close();
    }

    public static class Solution {
        public static int reverse(int x) {

            StringBuilder sb = new StringBuilder();
            boolean isMinZero = false;

            sb.append(x);

            if (sb.charAt(0) == '-') {

                sb.delete(0, 1);
                isMinZero = true;
            }

            if (isMinZero) {

                sb.append('-');
            }

            sb.reverse();

            if (Long.valueOf(sb.toString()) > Integer.MAX_VALUE || Long.valueOf(sb.toString()) < Integer.MIN_VALUE) {

                return 0;
            }

            else {

                return Integer.valueOf(sb.toString());
            }
        }
    }

}
