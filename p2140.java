public class p2140 {

    /**
     * 给你一个下标从 0 开始的二维整数数组 questions ，其中 questions[i] = [pointsi, brainpoweri] 。
     *
     * 这个数组表示一场考试里的一系列题目，你需要 按顺序 （也就是从问题 0 开始依次解决），针对每个问题选择 解决 或者 跳过 操作。解决问题 i 将让你 获得  pointsi 的分数，但是你将 无法 解决接下来的 brainpoweri 个问题（即只能跳过接下来的 brainpoweri 个问题）。如果你跳过问题 i ，你可以对下一个问题决定使用哪种操作。
     *
     * 比方说，给你 questions = [[3, 2], [4, 3], [4, 4], [2, 5]] ：
     * 如果问题 0 被解决了， 那么你可以获得 3 分，但你不能解决问题 1 和 2 。
     * 如果你跳过问题 0 ，且解决问题 1 ，你将获得 4 分但是不能解决问题 2 和 3 。
     * 请你返回这场考试里你能获得的 最高 分数。
     */

    class Solution {

        public static int MAXN = 100_001;
        public static long[] dp = new long[MAXN];//从i位置开始往后选，能够获得的最大收益
        public long mostPoints(int[][] questions) {

            int length = questions.length;
            dp[length] = 0;
            dp[length - 1] = questions[length - 1][0];
            for(int i = length - 2 ; i >= 0; i --)
                dp[i] = Math.max(
                        questions[i][0] + dp[Math.min(i + questions[i][1] + 1 , length)],//选择这个位置
                        dp[i + 1]//不选这个位置
                );


            return dp[0];
        }
    }
}
