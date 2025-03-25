import java.util.Arrays;

public class p398 {

    /**
     * 给你一个可能含有 重复元素 的整数数组 nums ，请你随机输出给定的目标数字 target 的索引。你可以假设给定的数字一定存在于数组中。
     *
     * 实现 Solution 类：
     *
     * Solution(int[] nums) 用数组 nums 初始化对象。
     * int pick(int target) 从 nums 中选出一个满足 nums[i] == target 的随机索引 i 。如果存在多个有效的索引，则每个索引的返回概率应当相等。
     */

    public static void main(String[] args) {

        Solution solution = new Solution(new int[]{1, 2, 3, 3, 3});
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(1));
        System.out.println(solution.pick(3));
    }


    static
    class Solution {

        public static int MAXN = 20_001;
        public static int[] nodeFirstEdge = new int[MAXN];//在index位置的数出现的第一个位置的指针
        public static int[] edgeValue = new int[MAXN];//最多有这么多个数字，也就最多有这么多条边
        public static int[] edgeNextEdge = new int[MAXN];
        public static int[] size = new int[MAXN];
        public static int[] forSort = new int[MAXN];
        public static int realLength;

        public Solution(int[] nums) {

            for(int i = 0 ; i < nums.length ; i ++)
                forSort[i] = nums[i];
            Arrays.sort(forSort , 0 , nums.length);
            ///去重
            int index = 0;//0位置一定不重复
            for(int i = 1 ; i < nums.length ; i ++)
                if(forSort[i] != forSort[index])
                    forSort[++index] = forSort[i];
            realLength = index + 1;
            Arrays.fill(size , 0 , realLength , 0);
            Arrays.fill(nodeFirstEdge , 0 , realLength , 0);

            int cnt = 1;
            for(int i = 0 ; i < nums.length ; i ++){

                int value = nums[i];
                index = binaryFound(value);

                int pre = nodeFirstEdge[index];
                nodeFirstEdge[index] = cnt;
                edgeNextEdge[cnt] = pre;
                edgeValue[cnt] = i;
                size[index] ++ ;

                cnt++;
            }
        }

        //保证一定能够找到
        private static int binaryFound(int value){

            int left = 0;
            int right = realLength - 1;
            while (left <= right){

                int mid = (left + right) >> 1;
                if(forSort[mid] < value)
                    left = mid + 1;
                else if(value < forSort[mid])
                    right = mid - 1;
                else
                    return mid;
            }

            return -1;
        }

        public int pick(int target) {

            int index = binaryFound(target);
            int s = size[index];
            int go = (int)(Math.random() * s);//走几步

            int edge = nodeFirstEdge[index];
            for(int i = 0 ; i < go ; i ++)
                edge = edgeNextEdge[edge];

            return edgeValue[edge];
        }
    }
}
