import java.beans.Visibility;
import java.util.*;

public class p207 {

    /**
     * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     *
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
     * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     *
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     */

    public static void main(String[] args) {

        int numCourses = 7;
        int[][] prerequisites =  {{1,0},{0,3},{0,2},{3,2},{2,5},{4,5},{5,6},{2,4}};

        System.out.println(new Solution().canFinish(numCourses , prerequisites));
//[[1,0],[0,3],[0,2],[3,2],[2,5],[4,5],[5,6],[2,4]]
    }



    static
    //先构建整张图，然后遍历，查看内部有没有环
    class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {

            Course[] courses = new Course[numCourses];
            Node[] nodes= new Node[numCourses];

            for(int i = 0; i < numCourses; i++){
                courses[i] = new Course(i);
                nodes[i] = new Node(courses[i]);
            }

            for(int[] pre : prerequisites){
                //0 完成需要先完成 1

                //courses[pre[0]].pre.add(courses[pre[1]]);
                courses[pre[1]].next.add(courses[pre[0]]);
                nodes[pre[0]].outPlus();
            }//完成整张图的构建


            Queue<Course> zeroInMap = new LinkedList<>();//入度为0的节点


            int solved = 0;
            for(Node node : nodes){

                if(node.outTime == 0){

                    zeroInMap.add(node.value);
                    solved++;
                }
            }//完成zeroInMap的完成

            //没有不需要依靠别人完成的节点
            if(zeroInMap.isEmpty()){

                return false;
            }

            while(!zeroInMap.isEmpty()){

                Course course = zeroInMap.poll();
                for(Course next : course.next){

                    int index = next.value;
                    Node node = nodes[index];//找到这个对应的node

                    node.outSubtract();

                    if(node.outTime == 0){

                        zeroInMap.add(next);
                        solved++;
                    }
                }
            }

            //在所有的能够解决的点都解决完了之后，还布恩那个解决的
            return solved == numCourses;
        }


        class Node{

            Course value;
            int outTime;//出度

            Node(Course value){

                this.value = value;
                outTime =0;
            }

            public void outPlus(){

                outTime++;
            }

            public void outSubtract(){

                outTime--;
            }
        }

        class Course{

            List<Course> next;
            int value;

            Course(int value){

                next = new ArrayList<>();
                this.value = value;
            }
        }
    }
}
