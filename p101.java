public class p101 {

    /**
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
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

    class Solution {
        public boolean isSymmetric(TreeNode root) {

            if (root == null) {
                return true;
            }

            return dfs(root.left, root.right);
        }

        public boolean dfs(TreeNode left, TreeNode right) {

            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }

            return dfs(left.left, right.right) && dfs(left.right, right.left);

        }
    }

}
