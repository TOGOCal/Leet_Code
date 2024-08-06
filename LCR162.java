public class LCR162 {

    /**
     * 注：这道题是纯也物体，了解即可
     * 问题背景：
     * 给定一个数n
     * 判断从1 到 n的所有数中1出现的次数
     * 例如：n=12中，1出行了1此， 1 2 中有1个1
     */

    /**
     * 思路：
     * 递归调用，将每一个数的最高位数字去掉再进行递归，直到达到个位数
     * 例如
     * 23124 = （3125 - 23124中1的个数） + （0 - 23124中1的个数）
     * 直到达到个位
     * 3125 - 23124中1的个数 处理方案：
     * 先假设范围是 3125 - 13124中一的个数
     * 在此基础上，明显的，我们可看出来
     * 万位上1的个数有 3125个
     * 千，百，十，个位上1的个数分析如下：
     * 当千位是1的时候，百位，十位，个位数可以任意选取，不管这三位选什么，在这个区间上都只能找到一个数字，所以千位上1的个数是 1000
     * 同理，百位上1的个数是 1000 ，因为千位，十位，个位可以任意选取
     * 因此，在万位是1的请款下， 1的个数是 3125 + 1000 + 1000 + 1000 = 5125
     * 万位：n=5 ， (n-1) * (10 的 (n-2)次方) + 前面的数
     * 当最高位不是1的时候，例如3125 - 23124
     * 其实可以被分成两个小区间， 3125 - 13124 和 13125 - 23124
     * 而每个小区间的1的个数是一样的，都是 (n-1) * (10 的 (n-2)次方)
     * 只是万位上1的个数变成了10000个
     * 如此便可以进行分析了
     */


    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.digitOneInNumber(100));
    }

    static
    class Solution{

        public int digitOneInNumber(int num) {

            return dfs(String.valueOf(num));
        }

        public int dfs(String s){

            if(s.length() == 1){

                //递归结束条件
                return s.equals("0") ? 0 : 1;
            }

            String below = new StringBuilder(s).deleteCharAt(0).toString();

            int thisCount = 0;
            if(s.charAt(0) == '1'){

                thisCount += Integer.parseInt(below) + 1;
            }else if(s.charAt(0) != '0'){

                thisCount += (int)Math.pow(10, s.length()-1);
            }

            thisCount += (s.charAt(0) - '0') *(s.length()-1) *  (int)Math.pow(10, s.length()-2);

            return thisCount + dfs(below);
        }
    }
}
