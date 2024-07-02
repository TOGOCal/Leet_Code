import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p56 {

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int[][] intervals = new int[s.nextInt()][2];

        for(int i = 0 ; i < intervals.length ; i++){

            intervals[i][0] = s.nextInt();
            intervals[i][1] = s.nextInt();
        }

        new Solution().merge(intervals);

        s.close();
    }

    static class Solution {
        public int[][] merge(int[][] intervals) {

            sortByFirst(intervals , 0 , intervals.length - 1);

            List<Node> list = new ArrayList<>();
            int start = intervals[0][0];
            int end = intervals[0][1];;
            for(int i = 1 ; i < intervals.length ; i++){

                if(intervals[i][0] <= end){
                    //后一个开始位置在这之内，说明可以合并
                    end = Math.max(end , intervals[i][1]);
                }else{
                    //说明断开
                    Node newNode = new Node(start , end);
                    list.add(newNode);

                    start = intervals[i][0];
                    end = intervals[i][1];
                }


            }

            //最后别忘了添加
            Node newNode = new Node(start , end);
            list.add(newNode);

            int [][] res = new int[list.size()][2];

            for(int i = 0 ; i < list.size() ; i++){

                res[i][0] = list.get(i).begin;
                res[i][1] = list.get(i).end;
            }

            return res;

        }

        class Node{

            int begin;
            int end;//有效位置

            public Node(int begin , int end){

                this.begin = begin;
                this.end = end;
            }
        }

        //start , end 位置都是有效位置
        public void sortByFirst(int[][] intervals , int start , int end){

            if (start >= end) {

                return ;
            }

            int rand = (int)(Math.random() * (end - start + 1)) + start;

            rand = intervals[rand][0];

            int p1 = start - 1;
            int p2 = end + 1;

            for(int i = start ; i< p2;i++){

                if(intervals[i][0] < rand){

                    p1++;
                    swap(intervals , p1 , i);
                }else if(intervals[i][0] > rand){

                    p2 -- ;
                    swap(intervals , p2 , i);
                    i--;
                }
            }


            sortByFirst(intervals , start , p1);
            sortByFirst(intervals , p2 , end);
        }


        public void swap(int[][] intervals , int i , int j){

            int[] temp = intervals[i];
            intervals[i] = intervals[j];
            intervals[j] = temp;
        }


    }

}
