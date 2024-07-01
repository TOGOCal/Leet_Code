public class p67 {

    /**
     *
     * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
     */

    class Solution {
        public String addBinary(String a, String b) {

            char[] crrA = a.toCharArray();
            char[] crrB = b.toCharArray();

            StringBuilder sb = new StringBuilder();

            int cin = 0;
            int lengthA = crrA.length;
            int lengthB = crrB.length;
            int length = Math.min(lengthA, lengthB);

            int i;
            for(i = 0; i < length; i++){

                int A = crrA[lengthA -1 - i] - '0';
                int B = crrB[lengthB -1 - i] - '0';

                int sum = A + B + cin;

                sb.append(sum % 2);
                cin = sum >> 1;
            }

            while(i < lengthA){

                int A = crrA[lengthA -1 - i] - '0';

                int sum = A + cin;

                sb.append(sum % 2);
                cin = sum >> 1;

                i++;
            }

            while(i < lengthB){

                int B = crrB[lengthB -1 - i] - '0';

                int sum = B + cin;

                sb.append(sum % 2);
                cin = sum >> 1;

                i++;

            }

            if(cin == 1){

                sb.append(1);
            }

            return sb.reverse().toString();
        }
    }
}
