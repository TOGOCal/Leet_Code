public class p117 {

    /**
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
     *
     * 初始状态下，所有 next 指针都被设置为 NULL 。
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

    class Solution {
        public Node connect(Node root) {

            if(root == null) return null;

            Node cur = root;

            //遍历到一层开始连接下一层
            Node levelFirst = root.left == null ? root.right : root.left;

            Node list = null;
            while(cur != null){

                while(cur != null){

                    if(cur.left != null){

                        if(list != null){

                            list.next = cur.left;
                        }

                        list = cur.left;
                        list.next = cur.right;

                        if(levelFirst == null){

                            levelFirst = cur.left;
                        }

                    }

                    if(cur.right != null){

                        if(list != null){

                            list.next = cur.right;
                        }

                        if(levelFirst == null){

                            levelFirst = cur.right;
                        }

                        list = cur.right;
                    }

                    cur = cur.next;
                }


                cur = levelFirst;
                levelFirst = null;
                list = null;
            }

            return root;
        }
    }
}
