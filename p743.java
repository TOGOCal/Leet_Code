import java.util.*;

public class p743 {

    /**
     * 有 n 个网络节点，标记为 1 到 n。
     *
     * 给你一个列表 times，表示信号经过 有向 边的传递时间。
     * times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
     *
     * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
     */

    class Solution {

        public static final int MAXN = 101;
        public static final int[] nodeFirstEdge = new int[MAXN];
        public static final boolean[] visit = new boolean[MAXN];
        public static final int[] minDistance = new int[MAXN];

        public static final int MAXI = 6001;
        public static final int[] edgeNextEdge = new int[MAXI];
        public static final int[] edgeTo = new int[MAXI];
        public static final int[] edgeWeight = new int[MAXI];

        public static class DistanceComparator implements Comparator<int[]>{

            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        }

        public static DistanceComparator dc = new DistanceComparator();

        public int networkDelayTime(int[][] times, int n, int k) {

            Arrays.fill(visit , false);
            Arrays.fill(nodeFirstEdge , 0);
            Arrays.fill(minDistance , Integer.MAX_VALUE);

            int cnt = 1;//边的id
            for(int[] edge : times){

                int from = edge[0],to = edge[1],time = edge[2];

                int preId = nodeFirstEdge[from];
                nodeFirstEdge[from] = cnt;
                edgeNextEdge[cnt] = preId;
                edgeTo[cnt] = to;
                edgeWeight[cnt] = time;
                cnt ++;
            }//完成图的建立

            PriorityQueue<int[]> heep = new PriorityQueue<>(dc);

            heep.add(new int[]{k , 0});//最初的点：到k点距离为0
            //minDistance[k] = 0;//到iji的时间是0
            int finishNodeNum = 0;//解决了的数量
            int res = Integer.MIN_VALUE;
            while(!heep.isEmpty()){

                int[] from = heep.poll();
                while(!heep.isEmpty() && visit[from[0]])
                    from = heep.poll();
                if(visit[from[0]])
                    break;//如果到空了之后还是访问过的点，那说明没有解决方法了

                visit[from[0]] = true;
                finishNodeNum++;
                int thisDistance = from[1];

                res = Math.max(res , thisDistance);
                minDistance[from[0]] = thisDistance;
                if(finishNodeNum == n)
                    break;//说明已经解决完所有的点了

                int edgeId = nodeFirstEdge[from[0]];

                while (edgeId != 0){

                    int t = edgeTo[edgeId];

                    heep.add(new int[]{t , thisDistance + edgeWeight[edgeId]});

                    edgeId = edgeNextEdge[edgeId];
                }
            }//完成遍历

            if(finishNodeNum != n)
                return -1;


            //int res = Integer.MIN_VALUE;
//            for(int i = 1 ; i <= n ; i++)
//                res = Math.max(res , minDistance[i]);

            return res;
        }
    }
}
