package from_500_to_600;

import java.util.Arrays;

public class p514 {

    /**
     * 电子游戏“辐射4”中，任务 “通向自由” 要求玩家到达名为 “Freedom Trail Ring” 的金属表盘，并使用表盘拼写特定关键词才能开门。
     *
     * 给定一个字符串 ring ，表示刻在外环上的编码；给定另一个字符串 key ，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
     *
     * 最初，ring 的第一个字符与 12:00 方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
     *
     * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
     *
     * 您可以将 ring 顺时针或逆时针旋转 一个位置 ，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
     * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
     */


    public static void main(String[] args) {

        System.out.println(new Solution().findRotateSteps("godding" , "gd"));
    }

    static
    class Solution {

        //使用类似于链式前向星的方式存储每个字符在那些位置出现过
        public static final int MAXN = 101;
        public static final int[] nodeFirstEdge = new int[26];
        public static final int[] edgeNextEdge = new int[MAXN];
        public static final int[] edgeTo = new int[MAXN];
        public char[] r;

        public static final int[][] dp = new int[MAXN][MAXN];

        public int findRotateSteps(String ring, String key) {

            r = ring.toCharArray();
            Arrays.fill(nodeFirstEdge , 0);
            for(int[] d : dp)
                Arrays.fill(d , 0);

            int cnt = 1;
            for(int i = 0 ; i < r.length ; i ++){

                char thisChar = r[i];
                int preId = nodeFirstEdge[thisChar - 'a'];

                nodeFirstEdge[thisChar - 'a'] = cnt;
                edgeNextEdge[cnt] = preId;
                edgeTo[cnt] = i;

                cnt++;
            }//完成每个字符出现在哪个位置的存储

            char[] target = key.toCharArray();

            return minFunction(0 , target , 0);
        }

        public int minFunction(int nowIndex , char[] targetStr , int targetIndex){

            if(targetIndex == targetStr.length)
                return 0;

            char target = targetStr[targetIndex];//得到当前需要找的字符

            if(dp[nowIndex][targetIndex] != 0)
                return dp[nowIndex][targetIndex];

            //特殊处理
            if(r[nowIndex] == target){

                //加一是因为按键也算一次操作
                int res = 1 + minFunction(nowIndex , targetStr , targetIndex + 1);
                dp[nowIndex][targetIndex] = res;
                return res;
            }




            int edgeId = nodeFirstEdge[target - 'a'];
            int firstPlace = edgeTo[edgeId];//得到这个字符出现的第一个位置
            int lastPlace = -1;

            //由贪心得到：除非当前字符就是需要的字符，那么洗一个字符一定是表盘向左拨遇到的第一个莫表字符，或者表盘向右拨遇到的第一个字符
            Integer leftFirst = null;
            Integer rightFirst = null;
            //由于上面做了特判，所以以下place不可能等于nowIndex
            while(edgeId != 0){

                int place = edgeTo[edgeId];

                if(place > nowIndex)
                    rightFirst = place;
                else if(leftFirst == null)
                    leftFirst = place;

                edgeId = edgeNextEdge[edgeId];
                if(edgeId == 0)
                    lastPlace = place;
            }

            if(leftFirst == null)//说明并未在数组左边找到，那就取右边第一个
                leftFirst = firstPlace;
            if(rightFirst == null)
                rightFirst = lastPlace;

            //向左右找
            int res = Math.min(
                    (rightFirst < nowIndex ? r.length : 0) + rightFirst - nowIndex + minFunction(rightFirst , targetStr , targetIndex + 1),
                    nowIndex - leftFirst + (leftFirst < nowIndex ? 0 : r.length) + minFunction(leftFirst , targetStr , targetIndex + 1)
            ) + 1;
            dp[nowIndex][targetIndex] = res;
            return res;
        }
    }
}
