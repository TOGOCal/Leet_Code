public class p404 {

    /**
     * 给定二叉树的根节点 root ，返回所有左叶子之和。
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
        public int sumOfLeftLeaves(TreeNode root) {

            if(root == null || (root.left == null && root.right == null))
                return 0;

            int res = 0;
            if(root.left != null)
                res += getSum(root.left , true);
            if(root.right != null)
                res += getSum(root.right , false);

            return res;

        }

        public int getSum(TreeNode node , boolean isLeft){

            //这个点是叶子节点
            if(node.left == null && node.right == null)
                return isLeft ? node.val : 0;

            int res = 0;
            if(node.left != null)
                res += getSum(node.left , true);
            if(node.right != null)
                res += getSum(node.right , false);

            return res;
        }
    }
}
