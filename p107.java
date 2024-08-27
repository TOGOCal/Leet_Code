import java.util.*;

public class p107 {


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
        public List<List<Integer>> levelOrderBottom(TreeNode root) {

            if(root == null) return new ArrayList<>();

            Queue<TreeNode> q = new LinkedList<>();

            TreeNode thisLevelEnd = root;
            TreeNode nextLevelEnd = null;

            List<List<Integer>> ans = new ArrayList<>();

            q.add(root);

            List<Integer> level = new ArrayList<>();

            while(!q.isEmpty()){

                TreeNode node = q.poll();

                level.add(node.val);

                if(node.left != null){

                    q.add(node.left);
                    nextLevelEnd = node.left;
                }

                if(node.right != null){

                    q.add(node.right);
                    nextLevelEnd = node.right;
                }


                if(node == thisLevelEnd){

                    thisLevelEnd = nextLevelEnd;

                    ans.add(level);

                    level = new ArrayList<>();
                }
            }

            Collections.reverse(ans);

            return ans;
        }
    }
}
