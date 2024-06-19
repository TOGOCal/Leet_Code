import java.util.Scanner;

public class p13 {

    /**
     * 罗马数字转整数
     */
    public static void main(String[] args) {

        Scanner s= new Scanner(System.in);

        System.out.println(new Solution().romanToInt(s.nextLine()));

        s.close();
    }

    static class Solution {
        public int romanToInt(String s) {

            int result = 0;

            char[] str= s.toCharArray();

            int count = 1;//记录重复的个数
            int preNum = valueMap(str[0]);
            int help;//辅助交换

            for(int i = 1; i < str.length; i++){

                int curr = valueMap(str[i]);
                if(preNum < curr){

                    help = curr;
                    curr -= preNum;
                    preNum = help;
                    count = 0;//这个是两个一组的，下一个count不管
                    //这代表是像 IX 这种表示方式的
                    result += curr;
                }else if(preNum == curr || count == 0){

                    count++;
                    preNum = curr;
                }else{
                    //大于，触发结算

                    result += count* preNum;

                    count =1;//代表当前的数

                    preNum = curr;
                }
            }

            result += preNum * count;
            return result;

        }

        public int valueMap(char c){

            return switch (c) {
                case 'I' -> 1;
                case 'V' -> 5;
                case 'X' -> 10;
                case 'L' -> 50;
                case 'C' -> 100;
                case 'D' -> 500;
                case 'M' -> 1000;
                default -> 0;
            };
        }
    }

}
