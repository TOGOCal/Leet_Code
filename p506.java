import java.util.Arrays;

public class p506 {

    /**
     * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
     *
     * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
     *
     * 名次第 1 的运动员获金牌 "Gold Medal" 。
     * 名次第 2 的运动员获银牌 "Silver Medal" 。
     * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
     * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
     * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
     */


    public static void main(String[] args) {

        int[] arr = {5,4,3,2,1};
        System.out.println(Arrays.toString(new Solution().findRelativeRanks(arr)));
    }


    static
    class Solution {

        public static final int MAXN = 10001;
        public static final int[] copy = new int[MAXN];
        public static int length;
        public static final String first = "Gold Medal";
        public static final String second = "Silver Medal";
        public static final String third = "Bronze Medal";

        public String[] findRelativeRanks(int[] score) {

            length = score.length;
            System.arraycopy(score, 0, copy, 0, length);

//            Arrays.sort(copy ,0, length);//排序

            quickSort(copy , 0 ,length - 1);

            String[] res = new String[length];
            for(int i = 0 ; i < length ; i++){

                int index = foundIndex(score[i]);
                if(index > 2)
                    res[i] = String.valueOf(index + 1);
                else if(index == 0)
                    res[i] = first;
                else if(index == 1)
                    res[i] = second;
                else if(index == 2)
                    res[i] = third;
            }

            return res;
        }

        public void quickSort(int[] arr , int left , int right){

            if(left >= right)
                return;

            int random = arr[(int)(Math.random() * (right - left)) + left];

            int p1 = left - 1;
            int p2 = right + 1;

            for(int i = left ; i < p2 ; i++){

                if(arr[i] > random)
                    swap(arr , ++p1 , i);
                else if(arr[i] < random)
                    swap(arr , --p2 , i--);
            }

            quickSort(arr , left , p1);
            quickSort(arr , p2 , right);
        }

        public void swap(int[] arr , int left , int right){

            int c = arr[left];
            arr[left] = arr[right];
            arr[right] = c;
        }


        public int foundIndex (int a){

            int left = 0 ;
            int right = length - 1;

            while (left <= right){

                int mid = (left + right) >> 1;
                if(a > copy[mid])
                    right = mid -1;
                else if(copy[mid] > a)
                    left = mid + 1;
                else
                    return mid;
            }

            return -1;
        }
    }
}
