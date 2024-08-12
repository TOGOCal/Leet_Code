import java.util.Arrays;
import java.util.Scanner;

public class p1846 {

    /**
     * 给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
     *
     * arr 中 第一个 元素必须为 1 。
     * 任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1 <= i < arr.length （数组下标从 0 开始），
     * 都满足 abs(arr[i] - arr[i - 1]) <= 1 。abs(x) 为 x 的绝对值。
     * 你可以执行以下 2 种操作任意次：
     *
     * 减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
     * 重新排列 arr 中的元素，你可以以任意顺序重新排列。
     * 请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的 最大值 。
     */


    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);

        int[] arr = new int[s.nextInt()];

        for (int i = 0; i < arr.length; i++) {

                arr[i] = s.nextInt();
        }

        System.out.println(new Solution().maximumElementAfterDecrementingAndRearranging(arr));



        s.close();
    }

    static class Solution {
        public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
            //桶排序
            //这个值最大只能到达arr.length,所以只需要准备这么大的容器就行了
            int[] dump = new int[arr.length+1];

            for (int j : arr) {

                if (j < dump.length) {

                    dump[j]++;
                }
            }//完成桶排

            int ans = 0;//最小为1

            int index = 0;//如果拍好序了，此时的指针指向哪个位置


            //初始的位置都是1
            for (int i = 1; i < dump.length;i++) {

                if(dump[i] != 0){
                    //有这个位置的数

                    //i是它在这个位置能够到达的最大值

                    if(ans + dump[i] <= i){

                        ans += dump[i];
                    }else{

                        ans = i;
                    }

                }
                index += dump[i];;//有这么多个数
            }

            //如果index没有填完就代表后面的范围都超过了，每个可以提供+1

            ans += (arr.length - index);

            return ans;
        }
    }


//    class Solution {
//        public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
//            Arrays.sort(arr);//排序
//
//            //由于是正整数数组，所以第一个一定是比1大的数字，因此直接将其置位成1就行了
//            arr[0] = 1;
//
//            int nextMax = 2;//系啊一个位置能达到的最大值
//
//            for (int i = 1; i < arr.length; i++) {
//
//                if(arr[i] >= nextMax){
//
//                    arr[i] = nextMax;
//                    nextMax+=1;
//                }
//            }
//
//
//            return arr[arr.length-1];
//
//        }
//    }
}
