public class p319 {

    /**
     * 初始时有 n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭第二个。
     *
     * 第三轮，你每三个灯泡就切换第三个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换第 i 个灯泡的开关。直到第 n 轮，你只需要切换最后一个灯泡的开关。
     *
     * 找出并返回 n 轮后有多少个亮着的灯泡。
     */

    public static void main(String[] args) {

        new Solution().bulbSwitch(1);
    }

    static
    class Solution {

        /**
         * 第i轮改变的是i的整数倍
         * 因此某个位置的数被改变几次与其约数数量有关
         * 除了完全平方数，其他的约数都成对出现
         */

        public int bulbSwitch(int n) {

            if(n ==0){

                return 0;
            }

            int left = 1;
            int right = 46340;

            int res = -1;
            while(left <= right){

                int mid = (left + right) >> 1;
                if(mid * mid <= n){

                    res = mid;
                    left = mid + 1;
                }else{

                    right = mid - 1;
                }
            }

            return res;
        }
    }
}
