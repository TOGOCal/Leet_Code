
/**
 * 待优化
 */

import java.util.Scanner;;

public class p5 {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        String str = s.nextLine();

        Solution so = new Solution();
        str = so.longestPalindrome(str);
        System.out.println(str);
        s.close();
    }
}

class Solution {

    public String longestPalindrome(String s) {
        StringBuilder str = new StringBuilder();

        int p1 = 0;
        int p2 = 0;
        int l = 0;
        for (int i = 0; i < s.length(); i++) {

            for (int ii = i; ii < s.length(); ii++) {

                if (s.charAt(i) == s.charAt(ii)) {

                    for (int a = 0; a < (ii - i + 1) / 2; a++) {

                        if (s.charAt(i + a) != s.charAt(ii - a)) {

                            break;
                        }

                        if (a == (ii - i + 1) / 2 - 1) {

                            if (l < ii - i + 1) {

                                l = ii - i + 1;
                                p1 = i;
                                p2 = ii;
                            }
                        }
                        // 1 2 3 4 5 6
                    }
                }
            }
        }

        for (int i = 0; i + p1 <= p2; i++) {

            str.append(s.charAt(i + p1));
        }
        return str.toString();
    }
}
