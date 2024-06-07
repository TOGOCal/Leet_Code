public class p66 {

    class Solution {
        public int[] plusOne(int[] digits) {

            int cin = 0;//进位

            digits[digits.length-1]++;

            for(int i = digits.length-1; i >= 0; i--) {

                digits[i] += cin;
                if(digits[i] >= 10){

                    digits[i] -= 10;
                    cin = 1;
                }else{
                    cin = 0;
                    break;
                }
            }

            if(cin == 1) {

                int[] res = new int[digits.length+1];
                res[0] = 1;
                System.arraycopy(digits, 0, res, 1, res.length - 1);
                return res;
            }

            return digits;
        }
    }
}
