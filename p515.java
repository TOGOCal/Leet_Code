package from_500_to_600;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p515 {

    /**
     * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
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

        public static int MAXN = 10001;
        public static int length;
        public static int[] max = new int[MAXN];

        public List<Integer> largestValues(TreeNode root) {

            if(root == null)
                return new ArrayList<>();

            length = -1;
            Arrays.fill(max , Integer.MIN_VALUE);
            recursion(root , 0);

            List<Integer> res = new ArrayList<>();
            for(int i = 0 ; i <= length ; i ++)
                res.add(max[i]);

            return res;
        }

        public void recursion(TreeNode node, int level){

            if(level > length){

                length = level;
                max[level] = node.val;
            }else{

                max[level] = Math.max(max[level] , node.val);
            }

            if(node.left != null)
                recursion(node.left , level + 1);
            if(node.right != null)
                recursion(node.right , level + 1);
        }
    }
}
