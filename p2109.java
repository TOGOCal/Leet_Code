package from_2100_to_2200;

public class p2109 {

    /**
     * 给你一个下标从 0 开始的字符串 s ，以及一个下标从 0 开始的整数数组 spaces 。
     *
     * 数组 spaces 描述原字符串中需要添加空格的下标。每个空格都应该插入到给定索引处的字符值 之前 。
     *
     * 例如，s = "EnjoyYourCoffee" 且 spaces = [5, 9] ，那么我们需要在 'Y' 和 'C' 之前添加空格，这两个字符分别位于下标 5 和下标 9 。因此，最终得到 "Enjoy Your Coffee" 。
     * 请你添加空格，并返回修改后的字符串。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().addSpaces("LeetcodeHelpsMeLearn" ,
                new int[]{8 , 13 , 15}));
    }


    static
    class Solution {
        public String addSpaces(String s, int[] spaces) {

            int nowIndex = 0;
            int alreadyPut = 0;
            int i = 0;
            StringBuilder sb = new StringBuilder();
            char[] str = s.toCharArray();

            while (alreadyPut < spaces.length && i < str.length){

                if(nowIndex == alreadyPut + spaces[alreadyPut]){

                    sb.append(' ');
                    alreadyPut++;
                }
                else
                    sb.append(str[i++]);

                nowIndex++;
            }

            while (i < str.length)
                sb.append(str[i ++]);

            return sb.toString();
        }
    }
}
