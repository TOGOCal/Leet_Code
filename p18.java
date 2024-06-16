import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class p18 {

    /**
     * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
     *
     * 0 <= a, b, c, d < n
     * a、b、c 和 d 互不相同
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     */

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int[] nums = new int[s.nextInt()];

        for(int i = 0 ; i < nums.length ; i++){

            nums[i] = s.nextInt();
        }

        new Solution().fourSum(nums , s.nextInt());

        s.close();
    }

    static class Solution {

        //使用双重循环枚举情况，在内部使用二重指针
        public List<List<Integer>> fourSum(int[] nums, int target) {

            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();

            if(nums.length <4 || !basicCheck(nums , target)){

                return res;
            }

            for(int i = 0 ; i < nums.length ; i++){

                if(i == 0 || nums[i] != nums[i-1]){
                    //位置1去重
                    for(int j = i+1 ; j< nums.length ; j++){
                        //位置2去重
                        if(j == i+1 || nums[j] != nums[j-1]){

                            int p1= j+1;
                            int p2 = nums.length-1;


                            while(p1 < p2){

                                int sum = nums[p1] + nums[p2];
                                int thisTarget = target - nums[i] - nums[j];

                                if(thisTarget > nums[p2] + nums[p2-1]){

                                    break;
                                }

                                if(thisTarget < nums[p1] + nums[p1+1]){

                                    break;
                                }

                                if(sum == thisTarget){

                                    res.add(Arrays.asList(nums[i] , nums[j] , nums[p1] , nums[p2]));

                                    do{

                                        p1++;
                                    }while (p1 < p2 && nums[p1] == nums[p1-1]);

                                    do{

                                        p2--;
                                    }while (p1 < p2 && nums[p2] == nums[p2+1]);

                                }else if(sum < thisTarget){

                                    do{

                                        p1++;
                                    }while (p1 < p2 && nums[p1] == nums[p1-1]);
                                }else{

                                    do{

                                        p2--;
                                    }while (p1 < p2 && nums[p2] == nums[p2+1]);
                                }
                            }



                        }
                    }
                }

            }

            return res;
        }

        public boolean basicCheck(int[] nums , int target){

            return (long)nums[0] + nums[1] + nums[2] + nums[3] <= target &&
                    (long)nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] + nums[nums.length - 4] >= target;
        }


    }

//    static class Solution {
//
//        //使用双重循环枚举情况，在内部使用二重指针
//        public List<List<Integer>> fourSum(int[] nums, int target) {
//
//            Arrays.sort(nums);
//            List<List<Integer>> res = new ArrayList<>();
//
//            if(nums.length <4 || !basicCheck(nums , target)){
//
//                return res;
//            }
//
//            for(int i = 0 ; i < nums.length ; i++){
//
//                if(i == 0 || nums[i] != nums[i-1]){
//                    //位置1去重
//                    for(int j = i+1 ; j< nums.length ; j++){
//                        //位置2去重
//                        if(j == i+1 || nums[j] != nums[j-1]){
//
//                            List<Information> ifo = twoPointsFound(nums , target - nums[i] - nums[j] , j+1 , nums.length-1);
//
//                            for(Information info : ifo){
//
//                                res.add(Arrays.asList(nums[i] , nums[j] , nums[info.index1] , nums[info.index2]));
//                            }
//                        }
//                    }
//                }
//
//            }
//
//            return res;
//        }
//
//        public boolean basicCheck(int[] nums , int target){
//
//            return (long)nums[0] + nums[1] + nums[2] + nums[3] <= target &&
//                    (long)nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] + nums[nums.length - 4] >= target;
//        }
//
//        public List<Information> twoPointsFound(int[] nums , int target , int startIndex , int endIndex){
//
//            int p1 = startIndex;
//            int p2 = endIndex;
//
//            List<Information> resIfo = new ArrayList<>();
//
//            while(p1 < p2){
//
//                int sum = nums[p1] + nums[p2];
//
//                if(sum == target){
//
//                    resIfo.add(new Information(p1 , p2));
//
//                    do{
//
//                        p1++;
//                    }while (p1 < p2 && nums[p1] == nums[p1-1]);
//
//                    do{
//
//                        p2--;
//                    }while (p1 < p2 && nums[p2] == nums[p2+1]);
//
//                }else if(sum < target){
//
//                    do{
//
//                        p1++;
//                    }while (p1 < p2 && nums[p1] == nums[p1-1]);
//                }else{
//
//                    do{
//
//                        p2--;
//                    }while (p1 < p2 && nums[p2] == nums[p2+1]);
//                }
//            }
//
//            return resIfo;
//        }
//
//        public class Information{
//
//            int index1;
//            int index2;
//
//            Information ( int index1, int index2) {
//
//                this.index1 = index1;
//                this.index2 = index2;
//            }
//        }
//
//    }


//    static class Solution {
//    四重循环，时间复杂度谁用谁知道，玩玩就好了
//
//        List<List<Integer>> res;
//        public List<List<Integer>> fourSum(int[] nums, int target) {
//
//            res = new ArrayList<>();
//            Arrays.sort(nums);
//
//            if(nums.length <4){
//
//                return res;
//            }
//
//            if(!basicCheck(nums , target)){
//
//                return res;
//            }
//
//            method(nums , new ArrayList<>() , target , 0);
//
//            return res;
//        }
//
//        public boolean basicCheck(int[] nums , int target){
//
////            long sum1 = (long)nums[0] + nums[1] + nums[2] + nums[3];
////            long sum2 = (long)nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] + nums[nums.length - 4];
////
////            if(sum1 > target || sum2 < target ){
////
////                return false;
////            }
////
////            return true;
//
//            return (long)nums[0] + nums[1] + nums[2] + nums[3] <= target &&
//                    (long)nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] + nums[nums.length - 4] >= target;
//        }
//
//        public void method(int[] nums , List<Integer> list, int restTarget , int beginPosition){
//
//            if(list.size() == 4){
//
//                if(restTarget == 0){
//
//                    res.add(new ArrayList<>(list));
//                }
//                return;
//            }
//
//
//            for(int i = beginPosition ; i < nums.length ; i++){
//
//                if(i == beginPosition || nums[i] != nums[i - 1]){
//                    //完成去重
//
//                    if(nums[i] > 0 && nums[i] > restTarget) {
//
//                        break;//因为完成了排序，所以后面的数都是比这个大的，这个数不满足后面也一定不会满足
//                    }else{
//
//                        list.add(nums[i]);
//                        method(nums , list , restTarget - nums[i] , i + 1);//选择这个数
//                        list.removeLast();
//
//                    }
//
//
//                }
//
//
//            }
//        }
//    }
}


