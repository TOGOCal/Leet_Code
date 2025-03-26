public class p530 {

    /**
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     *
     * 差值是一个正数，其数值等于两值之差的绝对值。
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

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(6);
        System.out.println(new Solution().getMinimumDifference(root));
    }


    static
    class Solution {

        public static int MAXN = 10_001;
        public static int[] value = new int[MAXN];
        public static int realLength;

        public int getMinimumDifference(TreeNode root) {

            realLength = 0;
            TreeNode cur = root;

            while (cur != null){

                if(cur.left == null){

                    value[realLength++] = cur.val;
                    cur = cur.right;
                }else{

                    TreeNode mostRight = cur.left;
                    while (mostRight.right != null && mostRight.right != cur)
                        mostRight = mostRight.right;

                    if(mostRight.right == null){

                        mostRight.right = cur;
                        cur = cur.left;
                    }else{

                        value[realLength++] = cur.val;
                        mostRight.right = null;
                        cur = cur.right;
                    }
                }
            }

            int res = Integer.MAX_VALUE;
            for(int i = 1 ; i < realLength ; i ++)
                res = Math.min(res , Math.abs(value[i] - value[i - 1]));

            return res;
        }
    }
}
