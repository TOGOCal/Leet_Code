public class p1184 {

    /**
     * 环形公交路线上有 n 个站，按次序从 0 到 n - 1 进行编号。
     * 我们已知每一对相邻公交站之间的距离，distance[i] 表示编号为 i 的车站和编号为 (i + 1) % n 的车站之间的距离。
     *
     * 环线上的公交车都可以按顺时针和逆时针的方向行驶。
     *
     * 返回乘客从出发点 start 到目的地 destination 之间的最短距离。
     */

    class Solution {
        public int distanceBetweenBusStops(int[] distance, int start, int destination) {

            if(start == destination){

                return 0;
            }

            if(start > destination){

                int temp = start;
                start = destination;
                destination = temp;
            }


            int C = 0;//周长

            int length = 0;//顺着走

            for(int i = 0; i < distance.length; i++){

                C += distance[i];

                if(i >= start && i < destination)
                    length += distance[i];
            }

            return Math.min(length, C - length);
        }
    }
}
