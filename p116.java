public class p116 {

    /**
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
     *
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 初始状态下，所有 next 指针都被设置为 NULL。
     */


// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};


    //递归方法
    class Solution {
        public Node connect(Node root) {

            dfs(root);
            return root;
        }

        public void dfs(Node node){

            if(node == null){

                return ;
            }

            if(node.left != null){

                node.left.next = node.right;

                Node next = node.next;

                if(next != null){

                    node.right.next = next.left;
                }
            }

            dfs(node.left);
            dfs(node.right);
        }

    }

//    class Solution {
//        public Node connect(Node root) {
//
//            if(root == null || root.right == null){
//
//                return root;
//            }
//
//            Node cur = root;
//
//            //使用morris遍历，第一次到达某个节点的时候进行操作
//            while(cur != null){
//
//                if(cur.left == null){
//
//                    cur = cur.right;
//                }else{
//
//                    Node leftTreeMostRight = cur.left;
//
//                    while(leftTreeMostRight.right != null && leftTreeMostRight.right != cur){
//                        leftTreeMostRight = leftTreeMostRight.right;
//                    }
//
//                    if(leftTreeMostRight.right == null){
//                        //第一次到达
//
//                        cur.left.next = cur.right;//完成单个的连接
//
//                        Node next = cur.next;//找到下一个节点
//
//                        if(next!= null){
//
//                            cur.right.next = next.left;
//                        }
//
//
//                        leftTreeMostRight.right = cur;
//                        cur = cur.left;
//
//                    }else{
//
//                        //第二次到达
//                        leftTreeMostRight.right = null;
//                        cur = cur.right;
//                    }
//                }
//            }
//
//            return root;
//        }
//    }
}
