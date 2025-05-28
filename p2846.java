import java.util.Arrays;

public class p2846 {

    /**
     * 现有一棵由 n 个节点组成的无向树，节点按从 0 到 n - 1 编号。
     * 给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，
     * 其中 edges[i] = [ui, vi, wi] 表示树中存在一条位于节点 ui 和节点 vi 之间、权重为 wi 的边。
     *
     * 另给你一个长度为 m 的二维整数数组 queries ，其中 queries[i] = [ai, bi] 。对于每条查询，请你找出使从 ai 到 bi 路径上每条边的权重相等所需的 最小操作次数 。在一次操作中，你可以选择树上的任意一条边，并将其权重更改为任意值。
     *
     * 注意：
     *
     * 查询之间 相互独立 的，这意味着每条新的查询时，树都会回到 初始状态 。
     * 从 ai 到 bi的路径是一个由 不同 节点组成的序列，从节点 ai 开始，到节点 bi 结束，且序列中相邻的两个节点在树中共享一条边。
     * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是第 i 条查询的答案。
     */

    public static void main(String[] args) {

        System.out.println(Arrays.toString(
                new Solution().minOperationsQueries(7,
                new int[][]{{0,1,1},{1,2,1},{2,3,1},{3,4,2},{4,5,2},{5,6,2}},
                new int[][]{{0,3},{3,6},{2,6},{0,6}})));
    }

    static
    class Solution {

        public static int MAXN = 1_00_00 + 1;//点数量
        public static int[] nodeFirstEdge = new int[MAXN];
        //由于是一棵树，m = n - 1，所以边树也不会超过MAXN，但是由于是无向图，所以*2
        public static int[] edgeNextEdge = new int[MAXN << 1];
        public static int[] edgeTo = new int[MAXN << 1];
        public static int[] edgeWeight = new int[MAXN << 1];

        //我们设0为根节点
        public static int[][] ST = new int[MAXN][(int)(Math.log(MAXN) / Math.log(2)) + 1];
        public static int[][] num = new int[MAXN][26 + 1];//从0到index位置中，不同值的边有几条
        public static int[] depth = new int[MAXN];//深度
        public static int realNum;

        private static void buildEdge(int from , int to , int weight , int cnt){

            int pre = nodeFirstEdge[from];
            nodeFirstEdge[from] = cnt;
            edgeNextEdge[cnt] = pre;
            edgeTo[cnt] = to;
            edgeWeight[cnt] = weight;
        }

        private static void buildST(){

            buildSTRecursion(0 , -1);

            for(int i = 1 ; i < ST[0].length ; i ++ ){

                for(int node = 0 ; node < realNum ; node++){

                    int pre = ST[node][i - 1];
                    ST[node][i] = (pre == -1 ? -1 : ST[pre][i - 1]);
                }
            }
        }


        private static void buildSTRecursion(int nowNode , int father){

            ST[nowNode][0] = father;
            depth[nowNode] = (father == -1 ? 0 : depth[father]) + 1;

            //复制父节点的边的情况
            if(father != -1)
                for(int i = 0 ; i < num[nowNode].length ; i ++)
                    num[nowNode][i] += num[father][i];

            for(int edgeId = nodeFirstEdge[nowNode] ; edgeId != 0 ; edgeId = edgeNextEdge[edgeId]){

                int to = edgeTo[edgeId];
                if(to != father){

                    int w = edgeWeight[edgeId];
                    num[to][w] ++;
                    buildSTRecursion(to , nowNode);
                }
            }
        }

        //求a，b公共祖先
        public static int lca(int a , int b){

            //保证a比b深
            if(depth[a] < depth[b]){

                int temp = a;
                a = b;
                b = temp;
            }

            //先升为同一高度
            int diff = depth[a] - depth[b];
            int index = 0;
            while (diff > 0){

                if((diff & 1) == 1)
                    a = ST[a][index];
                index ++;
                diff >>= 1;
            }

            //已经相等了
            if(a == b)
                return a;

            //还需要往上走
            for(int i = ST[0].length - 1 ; i >=0 ; i--){

                if (ST[a][i] != ST[b][i]) {
                    a = ST[a][i];
                    b = ST[b][i];
                }
            }

            //之前是不相等则移动，所以这里应该要再走一格
            return ST[a][0];
        }

        public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {

            Arrays.fill(nodeFirstEdge , 0);
            for(int[] N : num)
                Arrays.fill(N , 0);

            realNum = n;
            for(int[] w : num)
                Arrays.fill(w , 0);

            int cnt = 1;
            for(int[] edge : edges){

                buildEdge(edge[0] , edge[1] , edge[2] , cnt++);
                buildEdge(edge[1] , edge[0] , edge[2] , cnt++);
            }

            buildST();

            int[] res = new int[queries.length];
            for(int i = 0 ; i < queries.length ; i ++){

                int a = queries[i][0];
                int b = queries[i][1];
                int lca = lca(a , b);


                int all = depth[a] + depth[b] - (depth[lca] << 1);//总边数
                int r = 0;//求出最多的值的边数
                for(int j = 0 ; j < num[0].length ; j ++){

                    r = Math.max(r ,
                            num[a][j] + num[b][j] - (num[lca][j] << 1));
                }
                res[i] = all - r;
            }

            return res;
        }
    }
}
