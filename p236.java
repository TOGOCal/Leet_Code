package from_200_to_300;

import javax.swing.tree.TreeCellRenderer;

public class p236 {

    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     */



    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(new Solution().lowestCommonAncestor(root, root, root.left).val);
    }


    class Solution2{

        public static TreeNode foundOne = new TreeNode(Integer.MIN_VALUE);

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            return recursion(root , p.val , q.val);
        }

        public TreeNode recursion(TreeNode node,  int p , int q){

            if(node == null)
                return null;

            TreeNode left = recursion(node.left , p , q);
            TreeNode right = recursion(node.right , p , q);

            if(node.val == p || node.val == q){

                if((left != null && left.val == Integer.MIN_VALUE)
                        ||(right != null && right.val == Integer.MIN_VALUE) )
                    return node;
                else
                    return foundOne;
            }

            if(left == null){

                return right;
            }else if(left.val == Integer.MIN_VALUE){

                if(right != null && right.val == Integer.MIN_VALUE)
                    return node;
                else
                    return foundOne;

            }else{

                return left;
            }

        }
    }


    static
    class Solution {

        public static final int MAXN = 200001;
        public static final boolean[] path1 = new boolean[MAXN];
        public static int length1;//表示path的生效长度
        public static int length2;//表示path的生效长度

        public static final boolean[] path2 = new boolean[MAXN];
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            length1 = -1;
            length2 = -1;

            find(root , p.val , q.val);

            int a = Math.min(length1 , length2);

            TreeNode cur = root;
            for(int i = 0 ; i <= a; i ++){

                if(path1[i] ^ path2[i])
                    return cur;

                if(path1[i] && path2[i])
                    cur = cur.right;
                else
                    cur = cur.left;
            }

            return cur;
        }

        public static void find(TreeNode root  , int val1 , int val2){

            recursionP(root , val1 , 0);
            recursionQ(root , val2 , 0);
        }

        public static boolean recursionP(TreeNode node , int val , int depth){
            if(node == null)
                return false;

            if(node.val == val)
                return true;



            //在左边找到了
            if(recursionP(node.left , val , depth + 1)){

                path1[depth] = false;
                length1 = Math.max(length1 , depth);
                return true;
            }else if(recursionP(node.right , val , depth + 1)){

                path1[depth] = true;
                length1 = Math.max(length1 , depth);
                return true;
            }else{

                return false;
            }
        }

        public static boolean recursionQ(TreeNode node , int val , int depth){

            if(node == null)
                return false;
            if(node.val == val)
                return true;

            if(recursionQ(node.left , val , depth + 1)){

                path2[depth] = false;
                length2 = Math.max(length2 , depth);
                return true;
            }else if(recursionQ(node.right , val , depth + 1)){

                path2[depth] = true;
                length2 = Math.max(length2 , depth);
                return true;
            }else{

                return false;
            }
        }
    }
}
