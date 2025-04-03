import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class p429 {

    /**
     * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
     * <p>
     * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
     */


// Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }



    class Solution {

        public static Node thisLevelEnd;
        public static Node nextLevelEnd;

        public List<List<Integer>> levelOrder(Node root) {

            Queue<Node> q = new LinkedList<>();
            q.add(root);
            thisLevelEnd = root;

            List<List<Integer>> res = new ArrayList<>();
            List<Integer> thisLevel = new ArrayList<>();
            if(root == null)
                return res;

            while (!q.isEmpty()){

                Node now = q.poll();
                thisLevel.add(now.val);

                for(Node node : now.children){

                    nextLevelEnd = node;
                    q.add(node);
                }

                if(now == thisLevelEnd){
                    
                    res.add(thisLevel);
                    thisLevel = new ArrayList<>();
                    thisLevelEnd = nextLevelEnd;
                }
            }

            return res;
        }
    }
}
