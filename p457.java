package from_400_to_500;

import java.util.Arrays;

public class p457 {

    /**
     * 存在一个不含 0 的 环形 数组 nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
     *
     * 如果 nums[i] 是正数，向前（下标递增方向）移动 |nums[i]| 步
     * 如果 nums[i] 是负数，向后（下标递减方向）移动 |nums[i]| 步
     * 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
     *
     * 数组中的 循环 由长度为 k 的下标序列 seq 标识：
     *
     * 遵循上述移动规则将导致一组重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
     * 所有 nums[seq[j]] 应当不是 全正 就是 全负
     * k > 1
     * 如果 nums 中存在循环，返回 true ；否则，返回 false 。
     */


    public static void main(String[] args) {

        System.out.println(new Solution().circularArrayLoop(new int[]{-2,1,-1,-2,-2}));
    }


    static
    class Solution {

        public static int MAXN = 5_000 + 1;

        public static int[] to = new int[MAXN];
        public static int[] inGre = new int[MAXN];

        public static int realNum;

        public static int[] queue = new int[MAXN];
        public static int top,tail;
        public static void init(){

            tail = top = 0;
        }

        public static int out(){

            return queue[tail++];
        }

        public static void add(int n){

            queue[top++] = n;
        }

        public static boolean isEmpty(){

            return top == tail;
        }

        public boolean circularArrayLoop(int[] nums) {


            Arrays.fill(inGre , 0);
            Arrays.fill(to , -1);
            realNum = nums.length;
            for(int i = 0 ; i < realNum ; i ++){

                if(nums[i] > 0){
                    int t = ((i + nums[i]) % realNum + realNum) % realNum;
                    to[i] = t;
                    inGre[t] ++;
                }
            }


            init();
            for(int i = 0 ; i < realNum ; i ++){

                if(inGre[i] == 0)
                    add(i);
            }

            while(!isEmpty()){

                int i = out();
                if(to[i] != -1){
                    inGre[to[i]] --;
                    if(inGre[to[i]] == 0)
                        add(to[i]);
                }
            }

            for(int i = 0 ; i < realNum ; i ++){

                //存在环，就检查是不是只有一个元素
                if(inGre[i] != 0 &&
                        to[i] != i){

                    return true;
                }
            }


            Arrays.fill(inGre , 0);
            Arrays.fill(to , -1);
            realNum = nums.length;
            for(int i = 0 ; i < realNum ; i ++){

                if(nums[i] < 0){
                    int t = ((i + nums[i]) % realNum + realNum) % realNum;
                    to[i] = t;
                    inGre[t] ++;
                }
            }


            init();
            for(int i = 0 ; i < realNum ; i ++){

                if(inGre[i] == 0)
                    add(i);
            }

            while(!isEmpty()){

                int i = out();
                if(to[i] != -1){
                    inGre[to[i]] --;
                    if(inGre[to[i]] == 0)
                        add(to[i]);
                }
            }

            for(int i = 0 ; i < realNum ; i ++){

                //存在环，就检查是不是只有一个元素
                if(inGre[i] != 0 &&
                        to[i] != i){

                    return true;
                }
            }

            return false;
        }
    }

}
