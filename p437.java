import com.sun.source.tree.Tree;

import java.util.HashMap;

public class p437 {

    /**
     * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     *
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
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

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);
        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);

        System.out.println(new Solution2().pathSum(root , 8));
    }


    static
    // 使用前缀和的方式
    class Solution2{

        public int pathSum(TreeNode root, int targetSum) {
            // buildPreSum(root , 0);
            HashMap<Long , Integer> countMap = new HashMap<>();
            countMap.put(0L, 1);
            return count(root , countMap , targetSum , 0);
        }

        private int count(TreeNode cur , HashMap<Long , Integer> countMap , int target , long preValue){

            if(cur == null)
                return 0;

            long val = cur.val + preValue;

            int res = 0;
            res += countMap.getOrDefault(val - target, 0);
            countMap.put(val , countMap.getOrDefault(val , 0) + 1);
            res += count(cur.left , countMap , target , val);
            res += count(cur.right , countMap , target , val);
            countMap.put(val , countMap.get(val) - 1);
            return res;
        }

//        private void buildPreSum(TreeNode cur, int preValue){
//            if(cur == null)
//                return;
//
//            cur.val += preValue;
//            buildPreSum(cur.left , cur.val);
//            buildPreSum(cur.right , cur.val);
//        }
    }


    class Solution {

        //不要求一定以这个点为头，有多少种情况
        public int pathSum(TreeNode root, int targetSum) {

            if(root == null)
                return 0;

            int res = rootSum(root , targetSum);//当前节点一定在路径中
            res += pathSum(root.left , targetSum);//左边有没有可能的
            res += pathSum(root.right , targetSum);
            return res;
        }

        //要求一定以这个点为头，有多少种情况
        private int rootSum(TreeNode cur , long targetSum){

            if(cur == null)
                return 0;

            int val = cur.val;
            int res = val == targetSum ? 1 : 0;

            res += rootSum(cur.left , targetSum - val);
            res += rootSum(cur.right , targetSum - val);

            return res;
        }
    }
}
