import java.util.*;

public class p406 {

    /**
     * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
     * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
     *
     * 请你重新构造并返回输入数组 people 所表示的队列。
     * 返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
     */

    public static void main(String[] args) {

        int[][] people = new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        System.out.println(Arrays.deepToString(new Solution().reconstructQueue(people)));
    }



    static
    class Solution {

        public static Sort sort = new Sort();

        public int[][] reconstructQueue(int[][] people) {

            Arrays.sort(people , sort);//先根据身高排序


            List<int[]> ans = new ArrayList<int[]>();
            for (int[] person : people) {
                ans.add(person[1], person);
            }
            return ans.toArray(new int[ans.size()][]);

        }

        public static class Sort implements Comparator<int[]>{

            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0])
                    return o2[0] - o1[0];

                return o1[1] - o2[2];
            }
        }

    }
}
