import java.util.Arrays;
import java.util.HashMap;

public class p1713 {

    /**
     * 给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
     *
     * 每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,2] 。你可以在数组最开始或最后面添加整数。
     *
     * 请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。
     *
     * 一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().minOperations(new int[]{6,4,8,1,3,2} ,
                new int[]{4,7,6,2,3,8,6,1}));
    }


    static
    class Solution {
        public static int MAXN = 1_00_000 + 1;
        public static int[] max = new int[MAXN << 2];
        public static int realLength;

        public static int getMax(int left , int right){

            return getMaxRecursion(left , right , 0 , realLength - 1 , 1);
        }

        public static void setMax(int index , int length){

            setMaxRecursion(index , index , length ,
                    0 , realLength - 1 , 1);
        }

        public static void setMaxRecursion(int left, int right ,int length,
                                  int nowLeft , int nowRight , int treeIndex){

            if(left <= nowLeft && nowRight <= right){
                max[treeIndex] = Math.max(max[treeIndex] , length);
                return;
            }

            int mid = (nowLeft + nowRight) >> 1;
            if(left <= mid)
                setMaxRecursion(left , right , length,
                        nowLeft , mid , treeIndex << 1);
            if(mid < right)
                setMaxRecursion(left , right , length ,
                        mid + 1 , nowRight , treeIndex << 1 | 1);

            max[treeIndex] = Math.max(max[treeIndex << 1] , max[treeIndex << 1 | 1]);
        }

        public static int getMaxRecursion(int left , int right ,
                                          int nowLeft , int nowRight , int treeIndex){

            if(left <= nowLeft && nowRight <= right)
                return max[treeIndex];

            int mid = (nowLeft + nowRight) >> 1;
            int max = Integer.MIN_VALUE;

            if(left <= mid)
                max = Math.max(max ,
                        getMaxRecursion(left , right ,
                                nowLeft , mid , treeIndex << 1));

            if(mid < right)
                max = Math.max(max ,
                        getMaxRecursion(left , right ,
                                mid + 1, nowRight , treeIndex << 1 | 1));

            return max;
        }

        public int minOperations(int[] target, int[] arr) {

            HashMap<Integer , Integer> map = new HashMap<>();

            //进行映射
            //由于题目保证唯一，因此可以认为每个元素只会映射一个位置
            realLength = target.length;
            for(int i = 0 ; i < target.length ; i ++)
                map.put(target[i], i);

            Arrays.fill(max , 0);

            for (int j : arr) {

                //这个元素可以被选择
                if (map.containsKey(j)) {

                    int v = map.get(j);

                    //寻找小于这个数的最长长度
                    if (v == 0){

                        setMax(v, 1);//第一个位置往前的最长递增子序列一定只有1（前面不存在比这个数海啸的）
                        continue;
                    }
                    int m = getMax(0, v - 1);
                    setMax(v, m + 1);
                }
            }

            return realLength -  getMax(0 , realLength - 1);
        }
    }
}