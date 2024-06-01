import java.util.Scanner;
import java.util.Stack;

public class p42 {

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     */

    public static void main(String[] args) {

        Scanner s= new Scanner(System.in);

        int num = s.nextInt();

        int[] height = new int[num];

        for(int i = 0 ; i < num ; i++){

            height[i] = s.nextInt();
        }

        System.out.println(new Solution().trap(height));

        s.close();
    }

    /**
     * 写一个方法：给定两边的高度以及距离求体积的方法
     * 方法触发条件：准备一个数 leftHeight 表示左边最高的柱子高度，
     * 准备 nowHeight，代表现在的柱子高度
     * 触发条件1：
     *  当nowHeight 下降趋势停止，开始上升，又下降的前一个
     * 条件2：
     *  当nowHeight > leftHeight，
     */


    static class Solution {
        //方法2：使用单调栈
        public int trap(int[] height) {

            Stack<Integer> stack = new Stack<>();

            int result = 0;

            for(int i = 0 ;i < height.length ; i++){

                while(!stack.isEmpty() && height[i] > height[stack.peek()]){

                    int index = stack.pop();
                    if(stack.isEmpty()){

                        break;
                    }

                    int before = stack.peek();
                    int heightDifferent = Math.min(height[i] , height[before]) - height[index];

                    result += (i - before - 1) * heightDifferent;
                }

                stack.push(i);
            }

            return result;
        }

    }

    /**
     * 目前已经对了，开始进行空间消减
     */

//    static class Solution {
//        //换个方法：双指针法
//        public int trap(int[] height) {
//
//            int p1 = 0;
//            int p2 = height.length - 1;//都是有效位置
//
//            int leftIndex = p1;
//            int rightIndex = p2;
//
//            int result = 0;
//
//            boolean key = true;
//
//            int i;
//
//            while(key){
//                //1.p1右移的时候遇到高的
//                i=0;
//                while(p1+i < height.length){
//                    if( height[leftIndex] <= height[p1+i] ){
//
//                        result += V(leftIndex , p1+i , height);
//                        leftIndex = p1+i;
//                        p1+=i;
//                        i = 0;
//                    }
//
//                    i++;
//                }
//
//                if(p1 + i>= height.length){
//
//                    key = false;
//                }
//            }
//
//            i = 0;
//            while(p2 - i >= p1){
//
//                if(height[p2 - i] >= height[rightIndex]){
//
//                    result += V(p2-i , rightIndex , height);
//                    rightIndex = p2 - i;
//                    p2-=i;
//                    i = 0;
//                }
//
//                i++;
//            }
//
//            result += V(leftIndex , rightIndex , height);
//
//            return result;
//        }
//
//        public int V(int leftIndex,int rightIndex , int[] height){
//
//            int V=0;
//
//            int leftHeight = height[leftIndex];
//            int rightHeight = height[rightIndex];
//
//            if(leftHeight < rightHeight){
//                for(int i = leftIndex; i < rightIndex ; i++){
//
//                    V += leftHeight - height[i];
//                }
//
//            }else{
//
//                for(int i = rightIndex; i > leftIndex ; i--){
//
//                    V += rightHeight - height[i];
//                }
//            }
//
//            return V;
//        }
//
//    }
}
