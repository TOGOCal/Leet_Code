import java.util.ArrayList;
import java.util.List;

public class p152 {

    /**
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续
     * 子数组
     * （该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     *
     * 测试用例的答案是一个 32-位 整数。
     */

    public static void main(String[] args) {

        int[] nums = {-2,3,-4};

        System.out.println(new Solution().maxProduct(nums));
    }


    static
    class Solution {
        public int maxProduct(int[] nums) {

            if(nums.length == 1){

                return nums[0];
            }

            //动态规划，以这个结尾或者不一这个结尾

            int[] choose = new int[nums.length];
            int[] notChoose = new int[nums.length];

            if(nums[0] != 0){

                choose[0] = nums[0];
                notChoose[0] = 1;
            }else{

                choose[0] = 0;
                notChoose[0] = 1;
            }


            int max = Math.max(0 , nums[0]);
            for(int i = 1; i < nums.length; i++){

                if(nums[i] == 0){

                    choose[i] = 0;
                    notChoose[i] = Math.max(choose[i-1] , notChoose[i-1]);
                }


                //只选择这个,或者让前面的和这个乘起来
                int c = Math.max(nums[i] , nums[i] * choose[i-1]);

                int nc = Math.max(notChoose[i-1] ,choose[i-1]);

                choose[i] = c;
                notChoose[i] = nc;

                max = Math.max(max , c);
            }


            return max;

        }


    }

//    class Solution {
//        public int maxProduct(int[] nums) {
//
//            if (nums.length == 1) {
//
//                return nums[0];
//            }
//
//            List<Integer> help = new ArrayList<>();
//
//            int now = 1;
//            boolean isUseful = false;
//
//            for (int num : nums) {
//
//                if (num == 0) {
//
//                    if (isUseful) {
//
//                        help.add(now);
//                    }
//
//                    help.add(0);
//                    now = 1;
//
//                    isUseful = false;
//                } else if(num < 0){
//
//                    if(isUseful){
//
//                        help.add(now);
//                    }
//
//
//                    help.add(num);
//                    now = 1;
//                    isUseful = false;
//                }else{
//
//                    now *= num;
//                    isUseful = true;
//                }
//
//            }
//            if(isUseful){
//
//                help.add(now);
//            }//完成预处理
//
//            int size = help.size();
//
//            int[] arr = new int[size];//前缀积数组
//
//            now = 1;
//
//            for (int i = 0; i < size; i++) {
//
//                if (help.get(i) == 0) {
//
//                    arr[i] = 0;
//                    now = 1;
//                } else {
//                    now *= help.get(i);
//
//                    arr[i] = now;
//                }
//            }
//
//
//            int max = Integer.MIN_VALUE;
//            for (int i = 0; i < size; i++) {
//
//                int thisMax = help.get(i);
//
//                if(thisMax == 0){
//
//                    max = Math.max(max, 0);
//                    continue;
//                }
//
//                for (int j = i + 1; j < size; j++) {
//
//                    //不用再继续考虑剩下的情况了，再乘也是0
//                    if (help.get(j) == 0) {
//
//                        thisMax = Math.max(thisMax, 0);
//                        break;
//                    }
//
//                    //得到从i到j的积分（包括
//                    if (i == 0) {
//
//                        thisMax = Math.max(thisMax, arr[j]);
//                    } else {
//
//                        thisMax = Math.max(thisMax, arr[j] / (arr[i - 1] ==0 ? 1 : arr[i-1]));
//                    }
//
//                }
//
//
//                max = Math.max(max, thisMax);
//            }
//
//            return max;
//        }
//    }
}
