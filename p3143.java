import java.util.*;

public class p3143 {

    /**
     * 给你一个二维数组 points 和一个字符串 s ，其中 points[i] 表示第 i 个点的坐标，s[i] 表示第 i 个点的 标签 。
     *
     * 如果一个正方形的中心在 (0, 0) ，所有边都平行于坐标轴，且正方形内 不 存在标签相同的两个点，那么我们称这个正方形是 合法 的。
     *
     * 请你返回 合法 正方形中可以包含的 最多 点数。
     *
     * 注意：
     *
     * 如果一个点位于正方形的边上或者在边以内，则认为该点位于正方形内。
     * 正方形的边长可以为零。
     */


    public static void main(String[] args) {
        int[][] points = {{2,2},{-1,-2},{-4,4},{-3,1},{3,-3}};
        String s = "abdca";
        Solution solution = new Solution();
        System.out.println(solution.maxPointsInsideSquare(points,s));
    }

    //思路2：得到某个符号的最近属于正方形
    static
    class Solution {
        public int maxPointsInsideSquare(int[][] points, String s) {

            int[] index = new int[26];

            int limit = Integer.MAX_VALUE;

            for(int i = 0 ; i < 26 ; i++){

                index[i] = Integer.MAX_VALUE;
            }

            for(int i = 0 ; i < points.length ; i++){

                char c = s.charAt(i);

                int belong = Math.max(Math.abs(points[i][0]), Math.abs(points[i][1]));
                //这个点曾经被赋值过
                if(index[c - 'a'] != Integer.MAX_VALUE){

                    //界限
                    limit = Math.min(limit , Math.max(belong , index[c - 'a']) - 1);
                }

                    //这个点没有赋值过
                index[c - 'a'] = Math.min(index[c - 'a'] , belong);

            }

            int count = 0;
            for(int i = 0 ; i < 26 ; i++){

                if(index[i] <= limit && index[i] != Integer.MAX_VALUE){

                    count++;
                }
            }

            return count;
        }

    }



    //思路：某个点位于哪个正方形内是由x，y中大的值决定的，所以只需要记录x，y中大的值，然后排序，然后遍历，找到最大的正方形即可
//    class Solution {
//        public int maxPointsInsideSquare(int[][] points, String s) {
//
//            TreeMap<Integer , boolean[]> map = new TreeMap<>();
//
//            int limit = Integer.MAX_VALUE;
//
//            for(int i = 0 ; i < points.length ; i++){
//
//                int belong = Math.max(Math.abs(points[i][0]), Math.abs(points[i][1]));
//                char c = s.charAt(i);
//
//                if(belong <= limit){
//                    if(map.containsKey(belong)){
//
//                        //之前加过这个点
//                        boolean[] b = map.get(belong);
//                        if(b[c - 'a']){
//
//                            limit = belong-1;//belong这个正方形已经不满足要求了。至少要limit
//                        }else{
//
//                            b[c - 'a'] = true;
//                        }
//                    }else{
//
//                        boolean[] b = new boolean[26];
//                        b[c - 'a'] = true;
//                        map.put(belong , b);
//                    }
//                }
//
//            }
//
//
//            boolean[] b = new boolean[26];
//
//            int count = 0;
//            while(true){
//                Map.Entry<Integer, boolean[]> integerEntry = map.pollFirstEntry();
//
//                if(integerEntry == null){
//
//                    break;
//                }
//
//                if(integerEntry.getKey() > limit){
//
//                    break;
//                }
//
//                boolean[] bp = integerEntry.getValue();
//
//                int possibleCount = 0;
//                for(int i = 0 ; i < 26 ; i++){
//
//                    if(bp[i]){
//
//                        if(!b[i]){
//
//                            b[i] = true;
//                            possibleCount++;
//                        }else{
//
//                            return count;
//                        }
//                    }
//                }
//
//                count += possibleCount;
//            }
//
//            return count;
//
//        }
//
//
//    }
}
