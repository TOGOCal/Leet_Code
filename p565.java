package from_500_to_600;


import java.util.Arrays;

public class p565 {

    /**
     * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到最大的集合S并返回其大小，其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
     *
     * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
     */


    public static void main(String[] args) {

        System.out.println(new Solution().arrayNesting(new int[]{5,4,0,3,1,6,2}));
    }

    class Solution2 {

        public int arrayNesting(int[] nums) {

            int ans = 0;
            int n = nums.length;
            for(int i = 0 ; i < n ; i ++){

                int thisAns = 0;
                while(nums[i] < n){

                    int o = nums[i];
                    nums[i] = n;
                    i = o;
                    thisAns ++;
                }
                ans = Math.max(ans , thisAns);
            }

            return ans;
        }
    }


    static
    class Solution {

        public static int MAXN = 1_00_001;
        public static int[] length = new int[MAXN];//从i位置开始的链条有多长
        public static int realLength;

        public static int[] stack = new int[MAXN];
        public static int stackTop = 0;
        public static boolean[] inStack = new boolean[MAXN];

        private void push(int n){

            stack[stackTop ++] = n;
            inStack[n] = true;
        }

        private int pop(){

            int n = stack[--stackTop];
            inStack[n] = false;
            return n;
        }

        public int arrayNesting(int[] nums) {

            Arrays.fill(length , 0);
            realLength = nums.length;

            for(int i = 0 ; i < realLength ; i ++){

                if(length[i] == 0){

                    push(i);
                    int val = nums[i];
                    while(true){

                        if(inStack[val]){

                            int pre = pop();
                            length[pre] = 1;
                            while (stackTop != 0){

                                int p = pop();
                                length[p] = length[pre] + 1;
                                pre = p;
                            }

                            break;
                        }

                        if(length[val] != 0){

                            int pre = val;
                            while(stackTop != 0){

                                int p = pop();
                                length[p] = length[pre] + 1;
                                pre = p;
                            }
                            break;
                        }

                        push(val);
                        val = nums[val];
                    }
                }
            }

            int max = 0;
            for(int i = 0 ; i < realLength ; i ++)
                max = Math.max(max , length[i]);

            return max;
        }
    }
}
