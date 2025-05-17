import java.util.Random;

public class p961 {

    /**
     * 给你一个整数数组 nums ，该数组具有以下属性：
     *
     * nums.length == 2 * n.
     * nums 包含 n + 1 个 不同的 元素
     * nums 中恰有一个元素重复 n 次
     * 找出并返回重复了 n 次的那个元素。
     */

    class Solution {
        public int repeatedNTimes(int[] nums) {
            int n = nums.length;
            Random random = new Random();

            while (true) {
                int x = random.nextInt(n), y = random.nextInt(n);
                if (x != y && nums[x] == nums[y]) {
                    return nums[x];
                }
            }
        }
    }

}
