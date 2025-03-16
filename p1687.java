package from_1600_to_1700;

public class p1687 {

    /**
     * 你有一辆货运卡车，你需要用这一辆车把一些箱子从仓库运送到码头。这辆卡车每次运输有 箱子数目的限制 和 总重量的限制 。
     *
     * 给你一个箱子数组 boxes 和三个整数 portsCount, maxBoxes 和 maxWeight ，其中 boxes[i] = [portsi, weighti] 。
     *
     * ports i 表示第 i 个箱子需要送达的码头， weights i 是第 i 个箱子的重量。
     * portsCount 是码头的数目。
     * maxBoxes 和 maxWeight 分别是卡车每趟运输箱子数目和重量的限制。
     * 箱子需要按照 数组顺序 运输，同时每次运输需要遵循以下步骤：
     *
     * 卡车从 boxes 队列中按顺序取出若干个箱子，但不能违反 maxBoxes 和 maxWeight 限制。
     * 对于在卡车上的箱子，我们需要 按顺序 处理它们，卡车会通过 一趟行程 将最前面的箱子送到目的地码头并卸货。如果卡车已经在对应的码头，那么不需要 额外行程 ，箱子也会立马被卸货。
     * 卡车上所有箱子都被卸货后，卡车需要 一趟行程 回到仓库，从箱子队列里再取出一些箱子。
     * 卡车在将所有箱子运输并卸货后，最后必须回到仓库。
     *
     * 请你返回将所有箱子送到相应码头的 最少行程 次数。
     */

    class Solution {
        /**
         * 思路：
         * dp[i]，解决前i个货物需要的行程次数，例如dp[10]代表解决index从0-9的需要消耗的形成数
         * 正常思路：
         * 对于dp[i],分析最后一趟能够解决的所有情况，与前面的dp[i - j]相搭配，选出最小的情况
         * 贪心优化：
         * 单调性分析：
         * 1.dp[i - 1] <= dp[i]
         * 2.cost(i ~ j) >= cost((i + 1) ~ j)
         * 同时，当i~j可以被一次往返解决的时候，遍历i~j，每次遇到与前一次不同的时候，行程数+1即可
         * 所以，当一次形成可以解决的时候，只需要找到dp最靠右的即可
         */

        public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {

            int n = boxes.length;
            int[] dp = new int[n + 1];

            dp[0] = 0;
            dp[1] = 2;//一来回

            int weight = boxes[0][1];
            int trip = 2;
            for(int l = 0 ,r = 1 ; r < n ; r++){

                weight += boxes[r][1];
                if(boxes[r][0] != boxes[r - 1][0])
                    trip++;

                while(r - l + 1 > maxBoxes || weight > maxWeight || dp[l] == dp[l + 1]){

                    weight -= boxes[l++][1];
                    if(boxes[l][0] != boxes[l - 1][0])
                        trip--;
                }

                dp[r + 1] = dp[l] + trip;
            }

            return dp[n];
        }
    }


}
