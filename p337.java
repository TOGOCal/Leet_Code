package from_300_to_400;

import java.util.HashMap;
import java.util.HashSet;

public class p337 {

    /**
     * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
     *
     * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
     *
     * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
     */


    public static class TreeNode {
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

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        System.out.println(new Solution().rob(root));
    }

    static
    class Solution {

        //public static HashMap<TreeNode , int[]> map;

        public int rob(TreeNode root) {

            //map = new HashMap<>();
            //return dfs(root , true);
            int[] dfs = dfs(root);
            return Math.max(dfs[0] , dfs[1]);
        }

//        public int dfs(TreeNode node , boolean can){
//
//            if(map.containsKey(node)){
//
//                int[] pre = map.get(node);
//                int ans = pre[0];
//                if(can)
//                    ans = Math.max(ans , pre[1]);
//                return ans;
//            }
//
//
//            int[] res = new int[2];
//            int ans = 0;
//            //这个位置不选
//            if(node.left != null)
//                ans += dfs(node.left , true);
//            if(node.right != null)
//                ans += dfs(node.right , true);
//            res[0] = ans;
//
//
//            int a = node.val;
//            if(node.left != null)
//                a += dfs(node.left , false);
//            if(node.right != null)
//                a += dfs(node.right , false);
//            res[1] = a;
//
//            if(can){
//                ans = Math.max(a , ans);
//            }
//
//            map.put(node , res);
//            return ans;
//        }

        public int[] dfs(TreeNode node){

            if(node == null)
                return new int[]{0,0};
            int[] left = dfs(node.left);
            int[] right = dfs(node.right);
            int[] res = new int[2];

            res[0] = Math.max(left[0] ,left[1]) + Math.max(right[0],right[1]);
            res[1] = left[0] + right[0] + node.val;
            return res;
        }
    }
}
