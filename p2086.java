package from_2000_to_2100;

public class p2086 {

    /**
     * 给你一个下标从 0 开始的字符串 hamsters ，其中 hamsters[i]  要么是：
     *
     * 'H' 表示有一个仓鼠在下标 i ，或者
     * '.' 表示下标 i 是空的。
     * 你将要在空的位置上添加一定数量的食物桶来喂养仓鼠。如果仓鼠的左边或右边至少有一个食物桶，就可以喂食它。更正式地说，如果你在位置 i - 1 或者 i + 1 放置一个食物桶，就可以喂养位置为 i 处的仓鼠。
     *
     * 在 空的位置 放置食物桶以喂养所有仓鼠的前提下，请你返回需要的 最少 食物桶数。如果无解请返回 -1 。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().minimumBuckets("H..H"));
    }

    static
    class Solution {
        public int minimumBuckets(String hamsters) {

            char[] str = hamsters.toCharArray();

            int l = str.length;
            int res = 0;
            for(int i = 0 ; i < l ; i ++){

                //需要解决这个位置
                if(str[i] == 'H'){

                    //优先选择右边
                    //如果每个位置都优先选择右边，则左边不会成为最优解
                    //因为左边的那个会选择右边来解决当前位置
                    if(i + 1 < l &&
                    str[i + 1] == '.'){
                        res ++;
                        if(i + 2 < l &&
                        str[i + 2] == 'H')
                            str[i + 2] = 'h';//顺便把这个也解决了
                    }else{
                        //只能放在左边
                        if(i - 1 < 0 ||
                        str[i - 1] != '.')
                            return -1;
                        res ++;
                    }
                }
            }

            return res;
        }
    }
}
