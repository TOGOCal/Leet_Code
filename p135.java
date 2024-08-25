public class p135 {

    /**
     * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
     *
     * 你需要按照以下要求，给这些孩子分发糖果：
     *
     * 每个孩子至少分配到 1 个糖果。
     * 相邻两个孩子评分更高的孩子会获得更多的糖果。
     * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     */


    class Solution {
        public int candy(int[] ratings) {

            int length = ratings.length;

            int[] left = new int[length];
            int[] right = new int[length];

            left[0] = 1;
            right[length-1]=1;
            for(int i = 1; i < ratings.length-1; i++){

                if(ratings[i] > ratings[i-1]){

                    left[i] = left[i-1] + 1;
                }else{

                    left[i] = 1;
                }

                int position = length-1-i;
                if(ratings[position] > ratings[position+1]){

                    right[position] = right[position+1] + 1;
                }else{

                    right[position] = 1;
                }
            }

            if(ratings[length-1] > ratings[length-2]){

                left[length-1] = left[length-2] + 1;
            }else{

                left[length-1] = 1;
            }

            if(ratings[0] > ratings[1]){

                right[0] = right[1] + 1;
            }else{

                right[0] = 1;
            }

            int sum = 0;
            for(int i = 0; i < length; i++){

                sum += Math.max(left[i], right[i]);
            }

            return sum;
        }
    }
}
