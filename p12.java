import java.util.HashMap;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，
 * 例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
 * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，
 * 数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给你一个整数，将其转为罗马数字。
 */

public class p12 {
    public static void main(String[] args) {

        int a = 1994;

        Solution s = new Solution();
        System.out.println(s.intToRoman(a));
    }

    public static class Solution2{

        public String intToRoman(int num) {

            String[] roman={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
            int arr[] = {1000,900,500,400,100,90,50,40,10,9,5,4,1};

            StringBuilder sb= new StringBuilder();

            for(int i=0; i< arr.length; i++){

                int howMany = num/arr[i];
                num%=arr[i];

                for(int ii=0;ii<howMany;ii++){

                    sb.append(roman[i]);
                }
            }

            return sb.toString();
        }
    }


    public static class Solution {
        public String intToRoman(int num) {
            int arr[] = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
            StringBuilder sb = new StringBuilder();

            for(int i:arr){

                int howMay = num/i;
                num %=i;

                for(int j=0;j<howMay;j++){

                    sb.append(map.get(i));
                }
            }

            return sb.toString();

        }

        HashMap<Integer, String> map = new HashMap<>(){{

            put(1000, "M");
            put(900, "CM");
            put(500, "D");
            put(400, "CD");
            put(100, "C");
            put(90, "XC");
            put(50, "L");
            put(40, "XL");
            put(10, "X");
            put(9, "IX");
            put(5, "V");
            put(4, "IV");
            put(1, "I");
        }};
    }
}
