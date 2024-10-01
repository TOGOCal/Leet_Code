import java.util.*;

public class p2497 {

    /**
     * 给你一个 n 个点的无向图，节点从 0 到 n - 1 编号。给你一个长度为 n 下标从 0 开始的整数数组 vals ，其中 vals[i] 表示第 i 个节点的值。
     *
     * 同时给你一个二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条双向边。
     *
     * 星图 是给定图中的一个子图，它包含一个中心节点和 0 个或更多个邻居。换言之，星图是给定图中一个边的子集，且这些边都有一个公共节点。
     *
     * 下图分别展示了有 3 个和 4 个邻居的星图，蓝色节点为中心节点。
     *
     * 星和 定义为星图中所有节点值的和。
     *
     * 给你一个整数 k ，请你返回 至多 包含 k 条边的星图中的 最大星和 。
     */

    public static void main(String[] args) {

        //[1,2,3,4,10,-10,-20]
        int[] vals = new int[]{1,2,3,4,10,-10,-20};

        int[][] edges = new int[][]{{0,1},{1,2},{1,3},{3,4},{3,5},{3,6}};

        Solution solution = new Solution();
        System.out.println(solution.maxStarSum(vals, edges, 2));
    }

    static
    class Solution {
        public int maxStarSum(int[] vals, int[][] edges, int k) {
            int n = vals.length;
            int[][] graph = new int[n][];
            int[] count = new int[n];

            for (int[] edge : edges) {
                int u = edge[0], v = edge[1];
                if (vals[u] > 0) {
                    count[v]++;
                }
                if (vals[v] > 0) {
                    count[u]++;
                }
            }//得出每个由几个对应的，为了创建数组

            for (int i = 0; i < n; i++) {
                graph[i] = new int[count[i]];
            }//创建数组

            for (int[] edge : edges) {
                int u = edge[0], v = edge[1];
                //写入val。只写正数
                if (vals[v] > 0) graph[u][--count[u]] = vals[v];
                if (vals[u] > 0) graph[v][--count[v]] = vals[u];
            }


            int res = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                //得到最大值
                res = Math.max(res, maxSum(vals[i], graph[i], k));
            }
            return res;

        }

        private int maxSum(int val, int[] nums, int k) {
            if (k == 0) {
                return val;
            }

            if (nums.length <= k) {
                for (int num : nums) {
                    val += num;
                }
                return val;
            }

            int max = 0;
            for(int x:nums)
                if(x > max) max = x;//得到最大值


            int[] count = new int[max + 1];

            for (int num : nums)
                ++count[num];//桶排

            for (int i = count.length - 1; i > 0; i--) {
                int useCount = Math.min(count[i], k);
                val += useCount * i;
                k -= useCount;
                if (k == 0) {
                    break;
                }
            }//得到最大的几个

            return val;
        }
    }



//    class Solution {
//        public int maxStarSum(int[] vals, int[][] edges, int k) {
//
//            int constVar = (edges.length + 1);
//
//            //使用链式向前星
//            int[] nodeFirstEdgeId = new int[vals.length];
//            int[] edgeNextId = new int[constVar * 2];
//
//            int[] edgeTo = new int[constVar * 2];
//
//            int edgeId = 1;
//
//            //开始建图
//            for(int[] edge : edges){
//
//                int from = edge[0];
//                int to = edge[1];
//
//                int nextEdgeId = nodeFirstEdgeId[from];
//                nodeFirstEdgeId[from] = edgeId;
//
//                edgeNextId[edgeId] = nextEdgeId;
//                edgeTo[edgeId] = to;
//                edgeId++;
//
//                //由于是无向图（双向图）
//                nextEdgeId = nodeFirstEdgeId[to];
//                nodeFirstEdgeId[to] = edgeId;
//
//                edgeNextId[edgeId] = nextEdgeId;
//                edgeTo[edgeId] = from;
//
//                edgeId++;
//            }
//
//            //开始遍历
//            int max = Integer.MIN_VALUE;
//
//            //遍历每个节点
//            for(int i = 0; i < vals.length; i++){
//
//                int count = vals[i];
//
//                int nextEdgeId = nodeFirstEdgeId[i];
//
//                List<Integer> allVal = new ArrayList<>();
//
//                HashSet<Integer> visit = new HashSet<>();
//
//                while(nextEdgeId != 0){
//
//                    int to = edgeTo[nextEdgeId];
//                    visit.add(to);
//
//                    if(vals[to] > 0){
//                        allVal.add(vals[to]);
//                    }
//
//
//                    nextEdgeId = edgeNextId[nextEdgeId];
//                }
//
//
//                allVal.sort(new IntegerComparator());//从小到大排序
//
//                for(int j = 0; j < Math.min(k, allVal.size()); j++){
//
//                    count += allVal.get(j);
//                }
//
//
//                max = Math.max(max, count);
//            }
//
//            return max;
//        }
//
//        static class IntegerComparator implements Comparator<Integer>{
//
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        }
//    }
}
