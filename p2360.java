package from_2300_to_2400;

import java.util.Arrays;

public class p2360 {

    /**
     * 给你一个 n 个节点的 有向图 ，节点编号为 0 到 n - 1 ，其中每个节点 至多 有一条出边。
     *
     * 图用一个大小为 n 下标从 0 开始的数组 edges 表示，节点 i 到节点 edges[i] 之间有一条有向边。如果节点 i 没有出边，那么 edges[i] == -1 。
     *
     * 请你返回图中的 最长 环，如果没有任何环，请返回 -1 。
     *
     * 一个环指的是起点和终点是 同一个 节点的路径。
     */

    /**
     * 先使用拓扑排序，排序后剩下的点就是在环中的点
     * 然后使用并查集得出最大的环
     */

    public static void main(String[] args) {

        int[] arr = new int[]{3,3,4,2,3};
        System.out.println(new Solution().longestCycle(arr));
    }


    static
    class Solution {

        public static int MAXN = 100_001;
        public static int[] inGre = new int[MAXN];//入度，用于拓扑排序
        public static Stack stack = new Stack();
        public static int[] symbol = new int[MAXN];
        public static int[] size = new int[MAXN];

        public int longestCycle(int[] edges) {

            int n = edges.length;
            Arrays.fill(inGre ,0 , n ,0);
            Arrays.fill(size , 0 , n , 1);
            for(int i = 0 ; i < n ; i ++)
                symbol[i] = i;

            stack.init();

            for(int v : edges)
                if(v != -1)
                    inGre[v] ++;

            for(int i = 0 ; i < n ; i ++){

                if(inGre[i] == 0)
                    stack.push(i);
            }

            while (!stack.isEmpty()){

                //int v = stack.pop();
                int to = edges[stack.pop()];
                if(to == -1)
                    continue;
                inGre[to] --;
                if(inGre[to] == 0)
                    stack.push(to);
            }

            for(int i = 0 ; i < n ; i ++){

                if(inGre[i] != 0)
                    setSameSymbol(i , edges[i]);

            }


            int res = -1;
            for(int i = 0 ; i < n ; i ++){

                if(inGre[i] != 0 && symbol[i] == i){

                    res = Math.max(res ,
                            size[i] == 1 ? 0 : size[i]);
                }
            }

            return res;
        }


        public static void setSameSymbol(int a , int b){

            a = getSymbol(a);
            b = getSymbol(b);
            if(a == b)
                return;
            if(size[a] < size[b]){

                int temp = a;
                a = b;
                b = temp;
            }

            symbol[b] = a;
            size[a] += size[b];
        }

        public static int getSymbol(int a){

            int res = a;
            while (symbol[res] != res)
                res = symbol[res];

            while (a != res){

                int temp = symbol[a];
                symbol[a] = res;
                a = temp;
            }

            return res;
        }


        static class Stack{

            public static int[] arr = new int[MAXN];
            public static int top,end;
            public int pop(){

                return arr[end++];
            }

            public boolean isEmpty(){

                return top == end;
            }

            public void push(int value){

                arr[top++] = value;
            }

            public void init(){

                top = end = 0;
            }
        }
    }

}
