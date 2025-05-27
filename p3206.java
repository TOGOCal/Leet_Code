public class p3206 {

    /**
     * 给你一个整数数组 colors ，它表示一个由红色和蓝色瓷砖组成的环，第 i 块瓷砖的颜色为 colors[i] ：
     *
     * colors[i] == 0 表示第 i 块瓷砖的颜色是 红色 。
     * colors[i] == 1 表示第 i 块瓷砖的颜色是 蓝色 。
     * 环中连续 3 块瓷砖的颜色如果是 交替 颜色（也就是说中间瓷砖的颜色与它 左边 和 右边 的颜色都不同），那么它被称为一个 交替 组。
     *
     * 请你返回 交替 组的数目。
     *
     * 注意 ，由于 colors 表示一个 环 ，第一块 瓷砖和 最后一块 瓷砖是相邻的。
     */


    class Solution {
        public int numberOfAlternatingGroups(int[] colors) {

            int res = 0;
            int length = colors.length;
            if(colors[0] != colors[1] && colors[0]!= colors[length - 1])
                res++;
            if(colors[length - 1] != colors[length - 2] && colors[length - 1] != colors[0])
                res++;

            boolean same = colors[0] == colors[1];
            for(int i = 1 ; i < colors.length - 1 ; i++){

                boolean isSame = colors[i] == colors[i + 1];
                if(!same && !isSame)
                    res ++;

                same = isSame;
            }

            return res;
        }
    }
}
