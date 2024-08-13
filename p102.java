import java.util.*;

public class p102 {

    /**
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
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
        public List<List<Integer>> levelOrder(TreeNode root) {

            if( root == null) return new ArrayList<List<Integer>>();

            Queue<TreeNode> queue = new LinkedList<>();

            queue.add(root);
            TreeNode thisLevelEnd = root;
            TreeNode nextLevelEnd = null;

            List<List<Integer>> res = new LinkedList<>();

            List<Integer> level = new ArrayList<>();

            while (!queue.isEmpty()){
                TreeNode node = queue.poll();
                level.add(node.val);


                if( node.left != null){

                    nextLevelEnd = node.left;
                    queue.add(node.left);
                }

                if( node.right != null){

                    nextLevelEnd = node.right;
                    queue.add(node.right);
                }


                if( node == thisLevelEnd){

                    res.add(level);
                    level = new ArrayList<>();
                    thisLevelEnd = nextLevelEnd;
                }

            }

            return res;
        }
    }

}
