package from_500_to_600;

import java.util.Arrays;

public class p547 {

    /**
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     *
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     *
     * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
     *
     * 返回矩阵中 省份 的数量。
     */

    class Solution {
        public static int MAXN = 201;
        public static int[] symbol = new int[MAXN];
        public static int[] size = new int[MAXN];

        public int findCircleNum(int[][] isConnected) {

            for(int i = 0 ; i < isConnected.length ; i ++){

                symbol[i] = i;
                size[i] = 1;
            }

            for(int i = 0 ; i < isConnected.length ; i++){

                int[] arr = isConnected[i];
                for(int j = 0 ; j < arr.length ; j++){

                    if(arr[j] == 1)
                        setSameSet(i , j);
                }
            }

            int res = 0;
            for(int i = 0 ; i < isConnected.length; i++){

                if(symbol[i] == i){

                    res++;
                }
            }

            return res;
        }

        public int getSymbol(int a){
            int cur = a;
            while(symbol[cur] != cur){

                cur = symbol[cur];
            }

            while(a != cur){

                int temp = symbol[a];
                symbol[a] = cur;
                a = temp;
            }

            return cur;
        }

        public void setSameSet(int a , int b){

            a = getSymbol(a);
            b = getSymbol(b);
            if(a != b){

                if(size[a] > size[b]){

                    int temp = a;
                    a = b;
                    b = temp;
                }

                symbol[a] = b;
                size[b] += size[a];
            }
        }
    }
}
