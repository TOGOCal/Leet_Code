import java.util.Arrays;

public class p2430 {

    /**
     * 给你一个仅由小写英文字母组成的字符串 s 。在一步操作中，你可以：
     *
     * 删除 整个字符串 s ，或者
     * 对于满足 1 <= i <= s.length / 2 的任意 i ，如果 s 中的 前 i 个字母和接下来的 i 个字母 相等 ，删除 前 i 个字母。
     * 例如，如果 s = "ababc" ，那么在一步操作中，你可以删除 s 的前两个字母得到 "abc" ，因为 s 的前两个字母和接下来的两个字母都等于 "ab" 。
     *
     * 返回删除 s 所需的最大操作数。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().deleteString("aaabaab"));
    }



    static
    class Solution {

        public static int mod = 13131;
        public static int MAXL = 4000 + 1;
        public static int[] hash = new int[MAXL];
        public static int[] power = new int[MAXL];
        public static int realLength;
        public static char[] str;
        public static int[] dp = new int[MAXL];//删除从i位置到结尾的字符串，需要最多几步

        Solution(){

            power[0] = 1;
            for(int i = 1 ; i < MAXL ; i++){

                power[i] = power[i-1] * mod;
            }
        }

        private void build(String s){

            str = s.toCharArray();
            realLength = str.length;

            int pre = 0;
            int v;
            for(int i = 0 ; i < realLength ; i ++){

                v = pre + changeValue(str[i]);
                hash[i] = v;
                pre = v * mod;
            }//计算哈希值
        }

        //得到左右边界分别为l，r的字符串的哈希值
        private int hash(int l , int r){

            return hash[r] - (
                    l == 0 ? 0 :
                            hash[l - 1] * power[r - l + 1]
                    );
        }

        private int changeValue(char a){

            return a - 'a' + 1;
        }


        public int deleteString(String s) {

            Arrays.fill(dp , 0);
            build(s);
            return f(0);
        }


        //删除从i到结尾，最多需要几步
        private int f(int l){

            if(l >= realLength)
                return 0;
            if(l == realLength - 1)
                return 1;//删除整个字符串

            if(dp[l] != 0)
                return dp[l];//最少都是1


            //检查有没有可能可以多几步
            int length = realLength - l;
            int max = 1;
            for(int i = 1 ; i <= (length >> 1) ; i++){

                int left = hash(l , l + i - 1);
                int right = hash(l + i , l + (i << 1) - 1);
                if(left == right){
                    //选择删除这玩意
                    max = Math.max(max ,
                            1 + f(l + i));
                }
            }

            dp[l] = max;
            return max;
        }

    }
}
