import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class p15 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        int[] nums = new int[num];

        for(int i = 0 ; i < num ; i++){

            nums[i] = s.nextInt();
        }

        List<List<Integer>> res = new Solution().threeSum(nums);

        for(List<Integer> list : res){

            for(int i : list){

                System.out.print(i + " ");
            }

            System.out.println();
        }

        s.close();
    }

    static class Solution {
        List<List<Integer>> res;
        //int[] p;

        public List<List<Integer>> threeSum(int[] nums) {

            Arrays.sort(nums);
            res = new ArrayList<>();
            //p = new int[]{0, 1, 2};
            int l = nums.length;

            //定第一个数，剩下的按照两数之和处理
            for(int i = 0 ; i < l-2 ; i++){

                //如果第一个数和下一个数相同，则跳过
                if(i > 0 ){
                    if(nums[i] == nums[i-1]){

                        continue;
                    }
                }

                if(nums[i] + nums[l-2] + nums[l-1] < 0){

                    continue;
                }

                if(nums[i] + nums[i+1] + nums[i+2] > 0){

                    break;
                }

                //定第一个数，剩下的按照两数之和处理
                int p1 = i+1;
                int p2 = l-1;//双指针法
                int target = -nums[i];

                while(p1 < p2){

                    if(nums[p1] + nums[p2] > target){
                        //大于右指针左移
                        p2--;
                        continue;

                    } else if(nums[p1] + nums[p2] < target){
                        //小于左指针右移
                        p1++;
                        continue;
                    }


                    if(nums[p1] + nums[p2] == target){

                        res.add(Arrays.asList(nums[i], nums[p1], nums[p2]));
                        //不return的原因，可能会出现-5 -1 1 5 的情况
                        do {

                            p1++;
                        } while (p1 < l && nums[p1 - 1] == nums[p1]);

                        do{
                            //如果左指针的数和下一步移动的数相等，则左指针左移
                            p2 --;
                        }while(p2 >= 0 && nums[p2 + 1] == nums[p2]);

                    }

                }
            }

            return res;
        }




    }


//    static class Solution {
//        List<List<Integer>> res;
//        //int[] p;
//
//        public List<List<Integer>> threeSum(int[] nums) {
//
//            Arrays.sort(nums);
//            res = new ArrayList<>();
//             //p = new int[]{0, 1, 2};
//            int l = nums.length;
//
//            //定第一个数，剩下的按照两数之和处理
//            for(int i = 0 ; i < l-2 ; i++){
//
//                //如果第一个数和下一个数相同，则跳过
//                if(i > 0 ){
//                    if(nums[i] == nums[i-1]){
//
//                        continue;
//                    }
//                }
//
//                if(nums[i] + nums[l-2] + nums[l-1] < 0){
//
//                    continue;
//                }
//
//                if(nums[i] + nums[i+1] + nums[i+2] > 0){
//
//                    break;
//                }
//
//                //定第一个数，剩下的按照两数之和处理
//                int p1 = i+1;
//                int p2 = l-1;//双指针法
//                int target = -nums[i];
//
//                while(p1 < p2){
//
//                    if(nums[p1] + nums[p2] == target){
//
//                        res.add(Arrays.asList(nums[i], nums[p1], nums[p2]));
//                        //不return的原因，可能会出现-5 -1 1 5 的情况
//                        do {
//
//                            p1++;
//                        } while (p1 < l && nums[p1 - 1] == nums[p1]);
//
//                        do{
//                            //如果左指针的数和下一步移动的数相等，则左指针左移
//                            p2 --;
//                        }while(p2 >= 0 && nums[p2 + 1] == nums[p2]);
//
//                    } else if(nums[p1] + nums[p2] > target){
//                        //大于右指针左移
//
//                        do{
//                            //如果左指针的数和下一步移动的数相等，则左指针左移
//                            p2 --;
//                        }while(p2 >= 0 && nums[p2 + 1] == nums[p2]);
//
//                    } else if(nums[p1] + nums[p2] < target){
//                        //小于左指针右移
//
//                        do {
//
//                            p1++;
//                        } while (p1 < l && nums[p1 - 1] == nums[p1]);
//
//                    }
//
//                }
//            }
//
//            return res;
//        }
//
//
//
//
//    }


}
