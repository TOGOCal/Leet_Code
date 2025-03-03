import java.util.Arrays;

public class p466 {

    /**
     * 定义 str = [s, n] 表示 str 由 n 个字符串 s 连接构成。
     *
     * 例如，str == ["abc", 3] =="abcabcabc" 。
     * 如果可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。
     *
     * 例如，根据定义，s1 = "abc" 可以从 s2 = "abdbec" 获得，仅需要删除加粗且用斜体标识的字符。
     * 现在给你两个字符串 s1 和 s2 和两个整数 n1 和 n2 。由此构造得到两个字符串，其中 str1 = [s1, n1]、str2 = [s2, n2] 。
     *
     * 请你找出一个最大整数 m ，以满足 str = [str2, m] 可以从 str1 获得。
     */

    public static void main(String[] args) {

        System.out.println(
                new Solution().getMaxRepetitions("bbaa" , 2 , "b" , 1)
        );
    }



    static
    class Solution {

        public static int MAXN = 101;

        //从index位置开始，向后走多少的距离能够找到第一个为'a' + i的字符
        public static long[][] found = new long[MAXN][26];

        //从i位置出发，寻找到2 的j次方 个 str2 需要的最短长度
        public static long[][] suitableLength = new long[MAXN][32];


        public static int getPower(int n){

            int i = 0;
            while ((1 << i) <= (n >> 1))
                i++;
            return i;
        }

        public int getMaxRepetitions(String s1, int n1, String s2, int n2) {

            char[] str1 = s1.toCharArray();
            int length1 = str1.length;
            char[] str2 = s2.toCharArray();
            int length2 = str2.length;

            long[] last = found[length1 - 1];//从后往前推
            Arrays.fill(last , -1);
            for(int i = 0 ; i < length1 - 1 ; i ++){

                char c = (char) (str1[i] - 'a');
                if(last[c] == -1){

                    last[c] = 2 + i;//假设原本位置为长度1
                }
            }
            last[str1[length1 - 1] - 'a'] = 1;//特殊处理最后一个位置

            for(int i = length1 - 2 ;i >=0 ; i --){

                long[] now = found[i];
                long[] pre = found[i + 1];
                for(int j = 0 ; j < 26 ; j ++)
                    now[j] = (pre[j] == -1 ? -1 : pre[j] + 1);
                now[str1[i] - 'a'] = 1;
            }//完成查找

            for(int i = 0 ; i < length2 ; i ++)
                if(found[0][str2[i] - 'a'] == -1)
                    return 0;//一个都找不到，由于有字符没有出现


            //先填写表的第一列，从0位置出发，找到2^0(一个)str2的最短长度
            for(int i = 0 ;i < length1 ;i++){

                int index = i;
                for (char need : str2) {
                    index += found[index % length1][need - 'a'];//找到下一个字符
                }

                suitableLength[i][0] = index - i;
            }

            //开始倍增
            for(int i = 1; i < 32 ; i ++){

                for(int j = 0 ; j < length1 ; j ++){

                    suitableLength[j][i] = suitableLength[j][i - 1] +
                            suitableLength[
                                    (int)((j + suitableLength[j][i - 1]) % length1)
                                    ][i - 1];
                }
            }

            //寻找在n1个str1中，能够找到多少个str2
            int maxLength = n1 * length1;
            int p = getPower(maxLength);
            int res = 0;
            int index = 0;
            for(int i = p ; i >= 0 ;i --){

                if(maxLength >= suitableLength[index][i]){

                    maxLength -= (int)suitableLength[index][i];
                    index = (index + (int)suitableLength[index][i]) % length1;
                    res += (1 << i);
                }
            }

            return res / n2;
        }
    }
}
