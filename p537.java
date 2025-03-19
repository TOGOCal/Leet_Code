import java.math.BigInteger;

public class p537 {

    /**
     * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
     *
     * 实部 是一个整数，取值范围是 [-100, 100]
     * 虚部 也是一个整数，取值范围是 [-100, 100]
     * i2 == -1
     * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
     */


    public static void main(String[] args) {

        new Solution().complexNumberMultiply("1+-1i" , "0+0i");
    }


    static
    class Solution {
        public String complexNumberMultiply(String num1, String num2) {

            String[] n1 = num1.substring(0 , num1.length() - 1) .split("\\+");
            String[] n2 = num2.substring(0 , num2.length() - 1).split("\\+");

            BigInteger i1 = new BigInteger(n1[0]);
            i1 = i1.multiply(new BigInteger(n2[0]));
            i1 = i1.subtract(
                    new BigInteger(n1[1]).multiply(new BigInteger(n2[1]))
            );

            BigInteger i2 = new BigInteger(n1[1]);
            i2 = i2.multiply(new BigInteger(n2[0]));
            i2 = i2.add(
                    new BigInteger(n1[0]).multiply(new BigInteger(n2[1]))
            );

            StringBuilder sb = new StringBuilder();
            sb.append(i1);
            sb.append("+");
            sb.append(i2);
            sb.append("i");
            return sb.toString();
        }
    }
}
