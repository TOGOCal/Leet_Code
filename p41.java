import java.util.Scanner;

public class p41 {

    /**
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     *
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案
     *
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        int[] arr = new int[num];

        for(int i = 0 ; i < num ; i++){

            arr[i] = s.nextInt();
        }

        System.out.println(new Solution().firstMissingPositive(arr));

        s.close();
    }

    /**
     * 快排的时间复杂度是O(NlogN
     * 桶排的空间复杂度达不到常数项
     *
     * 这里面还可能有重复的
     * 去重：
     */



    /**
     * 换种思路
     *
     * 数组大小size，
     * 说明不存在的最小正整数一定在这个范围，超出了这个范围的数可以直接不管
     * 如果存在使某个数i在 i-1的位置上
     */
    
    static class Solution {

        public int firstMissingPositive(int[] nums) {

            int size = nums.length;

            //int p = size;//边界

            for(int i = 0 ; i < nums.length ; i++){

                while(nums[i]-1 != i){
                    //这个位置的数不正确

                    if(nums[i] >=1 && nums[i] <= size){

                        int index = nums[i] - 1;//这个数该放在这个位置上

                        int a = nums[index];
                        nums[index] = nums[i];

                        if(a == nums[i]){

                            a = 0;
                        }

                        nums[i] = a;
                    }else{

                        break;
                    }
                }
            }


            for (int i = 0; i < size; i++){

                if(nums[i] != i+1){
                    return i+1;//本来应该在这个位置的数
                }
            }

            return size+1;
        }

    }

//    static class Solution {
//        public int firstMissingPositive(int[] nums) {
//
//            Information ifo = partition(nums);
//            int size = ifo.size;
//            int max= ifo.max;//如果全是正数，则max就是数组的长度
//
//            if(max == size){
//
//                return max +1;
//            }//说明第一个不存在的正数是后一个数
//
//            if(!ifo.one){
//
//                return 1;
//            }
//
//            int p1 = 1;
//            int p2 = max;//不存在的数的范围 有效边界
//
//            return check(nums,p1,p2,size);
//        }
//
//        public int check(int[] nums,int p1 , int p2 , int size){
//
//            if(p2 == p1){//进这个函数的边界都是有效边界
//                //说明这个范围逼近只有一个数
//                return p1;
//            }
//
//            //先准备两个数
//            int count1 = 0;
//            int count2 = 0;
//
//            int satisfy1 = 0;
//            int satisfy2 = 0;
//
//
//            int middle = ((p2 - p1)>>1) + p1;//中间数
//
//            satisfy1 = (middle - p1 +1);//p1 ~ middle 有多少个数（包括middle
//
//            satisfy2 = (p2 - middle);//middle ~ p2 有多少个数（不包括middle
//
//            for(int i = 0; i < size; i++){
//
//                if(nums[i] <= p2 && nums[i] >= p1){
//                    //在这个范围内
//
//                    if(nums[i] <= middle ){
//
//                        count1++;
//                    }else{
//
//                        count2++;
//                    }
//                }
//            }
//
//            if(count1 != satisfy1){
//                //说明不存在的数在这个范围
//
//                return check(nums,p1,middle,size);//p1 , middle 有效边界
//            }else if( count2 != satisfy2){
//
//                return check(nums,middle+1,p2,size);//middle+1 , p2 有效边界
//            }
//
//            System.out.println("逻辑上不应该走到这个return");
//            return 0;
//        }
//
//        public Information partition(int[] nums){
//            //将所用正数放在左侧，方便遍历
//            int size = nums.length;
//            int max = Integer.MIN_VALUE;
//            boolean one = false;
//
//            for(int i = 0; i < size; i++){
//
//                if(nums[i] > max){
//
//                    max = nums[i];
//                }
//
//                if(nums[i] == 1){
//
//                    one = true;
//                }
//
//                if(nums[i] <= 0){
//
//                    int a = nums[i];
//                    nums[i] = nums[size-1];
//                    nums[size-1] = a;//交换
//
//                    i --;//不确定换过来的数是否满足要求
//                    size --;
//                }
//            }
//
//            return new Information(size,max , one);
//        }
//
//        public class Information{
//
//            int size;
//            int max;
//            boolean one;//防止1 不存在
//
//            Information(int size ,int max , boolean one){
//
//                this.size = size;
//                this.max = max;
//                this.one = one;
//
//            }
//        }
//    }
}
