public class p9 {

    public static void main(String[] args) {

    }

    class Solution {
        public boolean isPalindrome(int x) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();

            sb.append(x);
            sb2.append(x);
            sb2.reverse();
            return sb.toString().equals(sb2.toString());
        }
    }
}
