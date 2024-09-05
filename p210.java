import java.util.*;

public class p210 {

    /**
     * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。
     * 给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
     *
     * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
     * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
     */


    public static void main(String[] args) {

        int[][] prerequisites = {{1,0}};

        System.out.println(Arrays.toString(new Solution().findOrder(2, prerequisites)));
    }


    static
    //拓扑排序
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {

            if( numCourses == 0){

                return new int[0];
            }

            int[] res = new int[numCourses];
            Node[] nodes = new Node[numCourses];
            for(int i = 0; i < numCourses; i++){
                nodes[i] = new Node(i);
            }

            for(int[] pre : prerequisites){

                nodes[pre[1]].next.add(nodes[pre[0]]);
                nodes[pre[0]].in++;
            }

            Queue<Node> queue = new LinkedList<>();

            for(Node node : nodes){

                if(node.in == 0){

                    queue.add(node);
                }
            }

            int index = 0;
            while(!queue.isEmpty()){

                Node nowNode = queue.poll();

                for(Node next : nowNode.next){

                    next.in--;
                    if(next.in == 0){

                        queue.add(next);
                    }
                }

                res[index++] = nowNode.val;
            }

            return index == numCourses ? res : new int[0];
        }

        class Node{

            int val;
            int in;//入度
            List<Node> next;//之前需要完成的

            public Node(int val){
                this.val = val;
                next = new ArrayList<>();
            }
        }
    }
}
