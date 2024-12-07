package from_200_to_300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p218 {

    /**
     * 城市的 天际线 是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回 由这些建筑物形成的 天际线 。
     *
     * 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
     *
     * lefti 是第 i 座建筑物左边缘的 x 坐标。
     * righti 是第 i 座建筑物右边缘的 x 坐标。
     * heighti 是第 i 座建筑物的高度。
     * 你可以假设所有的建筑都是完美的长方形，在高度为 0 的绝对平坦的表面上。
     *
     * 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
     *
     * 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
     */

    public static void main(String[] args) {

        int[][] buildings = new int[][]{{3,7,8},{3,8,7},{3,9,6},{3,10,5},{3,11,4},{3,12,3},{3,13,2},{3,14,1}};

        System.out.println(new Solution().getSkyline(buildings));;
    }


    static
    class Solution {

        public static final int MAXN = 10001;
        public static Heap heap = new Heap();

        public static final int[] edgeIndex = new int[MAXN << 1];
        public static int length;
        public static int edgeNum;

        private void produce(){

            int index = 0;
            Arrays.sort(edgeIndex, 0 , length << 1);
            for(int i = 1 ; i < length << 1 ; i++){

                if(edgeIndex[i] != edgeIndex[index])
                    edgeIndex[++index] = edgeIndex[i];
            }

            edgeNum = index + 1;
            //完成加工
        }

        //这里的扫描线是所有的边
        public List<List<Integer>> getSkyline(int[][] buildings) {

            heap.init();
            List<List<Integer>> res = new ArrayList<>();
            length = buildings.length;

            for(int i = 0 ; i < buildings.length ; i ++){

                edgeIndex[i << 1] = buildings[i][0];
                edgeIndex[i << 1 | 1] = buildings[i][1];
            }

            produce();

            int index = 0;

            //逐个遍历边
            for(int i = 0 ; i < edgeNum ; i++) {

                //弹出所有已经不能包含这个边的位置
                int nowEdgeIndex = edgeIndex[i];

                int preMaxHeight = heap.isEmpty() ? 0 : heap.peek()[0];
                while (!heap.isEmpty() && heap.peek()[1] <= nowEdgeIndex)//小于的也要弹出
                    heap.poll();

                //加入所有可以加入的边
                while (index < buildings.length && buildings[index][0] <= nowEdgeIndex){ //小于的也要加入
                    heap.insert(buildings[index][2] , buildings[index][1]);//加入所有能够加入的边
                    index ++;
                }

                //这个地方完全降到最下面了
                List<Integer> r = new ArrayList<>();
                r.add(nowEdgeIndex);
                if(heap.isEmpty()){

                    r.add(0);//高度为0
                    res.add(r);
                }else if(heap.peek()[0] != preMaxHeight){
                    //当前位置是个有效位置
                    r.add(heap.peek()[0]);
                    res.add(r);
                }
            }
            return res;
        }



        //需要定义高度的大根堆
        public static class Heap{

            private static final int[][] heap = new int[MAXN << 1][2];//高度，结尾位置
            private static int size = 0;

            public void init(){

                size = 0;
            }

            public boolean isEmpty(){

                return size == 0;
            }

            public int[] peek(){

                if(size == 0)
                    return null;

                return heap[0];
            }

            public int[] poll(){

                if(size == 0)
                    return null;

                int[] res = heap[0];

                swap(0 , --size);//最后位置和第0位置交换

                //最后一个位置失效了，相当于要从顶向下检查有没有不对劲的地方
                checkUpDown(0);


                return res;
            }

            private void checkUpDown(int index){

                int left = (index) << 1 | 1;
                int right = (index << 1 | 1) + 1;

                //可能需要将左边换上来
                if(left < size){

                    //由于是小根堆，需要头比左右节点中较大的还大，所以这里应该选出左右节点中较大的
                    int needCheckIndex = right < size ? //右边也需要检查吗
                            (heap[left][0] > heap[right][0] ? left : right) :
                            left;//选出了需要比较的位置

                    //有交换的必要
                    if(heap[needCheckIndex][0] > heap[index][0]){

                        swap(needCheckIndex , index);//做交换，index位置来到了needCheckIndex位置
                        checkUpDown(needCheckIndex);
                    }
                }
            }

            private void swap(int i , int j){

                int[] temp = heap[i];
                heap[i] = heap[j];
                heap[j] = temp;
            }

            public void insert(int height , int endIndex){

                //将其加到最后
                heap[size][0] = height;
                heap[size][1] = endIndex;
                checkDownUp(size);
                size ++;

                //由于改变了最后一个位置，所以需要向上检查有没有需要改变的
            }

            private void checkDownUp(int index){

//                int pre = ((index % 2 == 0) ?
//                        index - 2 : index - 1) >> 1;
                int pre = (index - (2 - index % 2)) >> 1;
                if(pre >= 0){
                    //有向上检查的必要
                    //如果当前位置较大，就需要上浮
                    if(heap[index][0] > heap[pre][0]){

                        swap(index , pre);
                        checkDownUp(pre);
                    }
                }
            }
        }
    }
}
