package from_200_to_300;

public class p230 {

    /**
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        //中序遍历结果就是排序结果
        public int kthSmallest(TreeNode root, int k) {

            TreeNode cur = root;
            int count = 0;
            while (/*cur != null*/true) {

                if (cur.left == null) {

                    count++;
                    if (count == k)
                        return cur.val;
                    cur = cur.right;
                } else {

                    TreeNode mostRight = cur.left;
                    while (mostRight.right != null && mostRight.right != cur)
                        mostRight = mostRight.right;


                    if (mostRight.right == null) {

                        mostRight.right = cur;
                        cur = cur.left;
                    } else {

                        count++;
                        if (count == k)
                            return cur.val;
                        mostRight.right = null;
                        cur = cur.right;
                    }
                }
            }
        }
    }
}
