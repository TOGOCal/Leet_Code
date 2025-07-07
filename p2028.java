package from_2000_to_2100;

import java.util.Arrays;

public class p2028 {

    /**
     * 现有一份 n + m 次投掷单个 六面 骰子的观测数据，骰子的每个面从 1 到 6 编号。观测数据中缺失了 n 份，你手上只拿到剩余 m 次投掷的数据。幸好你有之前计算过的这 n + m 次投掷数据的 平均值 。
     *
     * 给你一个长度为 m 的整数数组 rolls ，其中 rolls[i] 是第 i 次观测的值。同时给你两个整数 mean 和 n 。
     *
     * 返回一个长度为 n 的数组，包含所有缺失的观测数据，且满足这 n + m 次投掷的 平均值 是 mean 。如果存在多组符合要求的答案，只需要返回其中任意一组即可。如果不存在答案，返回一个空数组。
     *
     * k 个数字的 平均值 为这些数字求和后再除以 k 。
     *
     * 注意 mean 是一个整数，所以 n + m 次投掷的总和需要被 n + m 整除。
     */

    class Solution {
        public int[] missingRolls(int[] rolls, int mean, int n) {

//            int sum = Arrays.stream(rolls).sum();
            int sum = 0;
            for(int N : rolls)
                sum += N;
            int all = (rolls.length + n) * mean;
            int last = all - sum;
            if(last < n || last > 6 * n)
                return new int[]{};

            int[] res = new int[n];
            Arrays.fill(res , 1);//先全部填为1
            last -= n;
            int index = 0;
            while (last > 0 && index < n){

                int change = Math.min(last , 5);
                res[index++] += change;
                last -= change;
            }

            return res;
        }
    }
}
