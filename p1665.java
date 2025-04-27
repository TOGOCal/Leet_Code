import java.util.Arrays;
import java.util.Comparator;

public class p1665 {
    /**
     * 给你一个任务数组 tasks ，其中 tasks[i] = [actuali, minimumi] ：
     *
     * actuali 是完成第 i 个任务 需要耗费 的实际能量。
     * minimumi 是开始第 i 个任务前需要达到的最低能量。
     * 比方说，如果任务为 [10, 12] 且你当前的能量为 11 ，那么你不能开始这个任务。如果你当前的能量为 13 ，你可以完成这个任务，且完成它后剩余能量为 3 。
     *
     * 你可以按照 任意顺序 完成任务。
     *
     * 请你返回完成所有任务的 最少 初始能量。
     */

    class Solution {

        //先做cost - limit较小的排到前面即可（贪心策略，麻了下次遇到我还是做不来啊）

        public int minimumEffort(int[][] tasks) {

            Arrays.sort(tasks, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));

            int ans = 0;
            int cur = 0;
            for(int[] t : tasks){

                int cost = t[0];
                int limit = t[1];

                if(cur < limit){

                    ans += (limit - cur);
                    cur = limit;
                }
                cur -= cost;
            }

            return ans;
        }
    }
}