public class p112 {

    /**
     *
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
        public boolean hasPathSum(TreeNode root, int targetSum) {

            return dfs(root, targetSum);
        }

        public boolean dfs(TreeNode node, int restTarget) {

            if(node == null){

                return false;
            }

            if(node.left == null && node.right == null) {

                if(restTarget == node.val) {

                    return true;
                }
                return false;
            }

            //不是最后一个

            boolean res = false;
            if(node.left!=null){

                res |= dfs(node.left, restTarget - node.val);
            }

            if(node.right!=null){

                res |= dfs(node.right, restTarget - node.val);
            }

            return res;
        }

    }
}
