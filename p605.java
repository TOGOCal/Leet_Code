public class p605 {

    /**
     * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     *
     * 给你一个整数数组 flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false 。
     */


    public static void main(String[] args) {

        System.out.println(new Solution().canPlaceFlowers(new int[]{0}, 1));

    }

    static
    class Solution {

//        public static int MAXN = 20_001;
//        public static int[] dp = new int[MAXN];//从i位置往后，最多能够种植多少朵花
//        public static int realLength;
        public boolean canPlaceFlowers(int[] flowerbed, int n) {

//            realLength = flowerbed.length;

            int max = 0;
            int i = 0;
            for(; i < flowerbed.length ; i ++){

                if(flowerbed[i] == 1){

                    break;
                }
            }
            if(i == flowerbed.length){

                return (flowerbed.length + 1)>> 1 >= n;
            }

            max += i == 0 ? 0 : i >> 1;

            for(; i < flowerbed.length ; i ++){

                while(i < flowerbed.length && flowerbed[i] == 1)
                    i++;
                if(i == flowerbed.length - 1)
                    break;
                i ++;//这个位置才是能够种花的
                int pre = i;
                while(i < flowerbed.length && flowerbed[i] == 0)
                    i++;
                //max += [((i - 2) - pre + 1) + 1] >> 1
                max += (i - pre +
                        (i == flowerbed.length ? 1 : 0)
                        ) >> 1;
            }

            return max >= n;
        }
    }
}
