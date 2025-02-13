package from_500_to_600;

import java.util.Arrays;

public class p538 {

    /**
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     *
     * 提醒一下，二叉搜索树满足下列约束条件：
     *
     * 节点的左子树仅包含键 小于 节点键的节点。
     * 节点的右子树仅包含键 大于 节点键的节点。
     * 左右子树也必须是二叉搜索树。
     */


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }




    //反序中序遍历可以让二叉搜索树从大到小排序
    class Solution2 {
        public TreeNode convertBST(TreeNode root) {
            helper(root, 0);
            return root;
        }

        int helper(TreeNode node, int val) {
            if (node == null) return val;
            node.val += helper(node.right, val);
            return helper(node.left, node.val);
        }
    }

    class Solution {

        public static int MAXN = 10001;
        public static int[] size = new int[MAXN << 1];//每个节点和其子节点的和
        public static int[] res = new int[MAXN << 1];


        public TreeNode convertBST(TreeNode root) {

            if(root == null)
                return root;



            dfs1(root);
            res[root.val + MAXN] = size[root.val + MAXN] - (root.left == null ? 0 : size[root.left.val + MAXN]);
            dfs2(root);

            TreeNode cur = root;
            while (cur != null){

                if(cur.left == null){

                    cur.val = res[cur.val + MAXN];
                    cur = cur.right;
                }else{

                    TreeNode mostRight = cur.left;
                    while (mostRight.right != null && mostRight.right != cur){

                        mostRight = mostRight.right;
                    }

                    if(mostRight.right == null){

                        cur.val = res[cur.val + MAXN];
                        mostRight.right = cur;
                        cur = cur.left;
                    }else{

                        mostRight.right = null;
                        cur = cur.right;
                    }
                }
            }

            return root;
        }

        public static int dfs1(TreeNode node){
            if(node == null)
                return 0;

            int s = node.val;
            s += dfs1(node.left);
            s += dfs1(node.right);

            size[node.val + MAXN] = s;
            return s;
        }

        public static void dfs2(TreeNode node){

            //左子树的当前节点比这个点小，所以左子树会在当前节点的基础上在加上一个左子树自生的值
            if(node.left != null){

                TreeNode left = node.left;
                res[left.val + MAXN] = res[node.val + MAXN] + left.val +
                        (left.right == null ? 0 : size[left.right.val + MAXN]);
                dfs2(node.left);
            }

            if(node.right != null){

                TreeNode right = node.right;
                res[right.val + MAXN] = res[node.val + MAXN] - node.val -
                        (right.left == null ? 0 : size[right.left.val + MAXN]);
                dfs2(node.right);
            }
        }

    }
}
