public class p543 {

    /**
     * 给你一棵二叉树的根节点，返回该树的 直径 。
     *
     * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
     *
     * 两节点之间路径的 长度 由它们之间边数表示。
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
        public int diameterOfBinaryTree(TreeNode root) {

            int[] res = new int[2];
            get(root , res);
            return res[1];
        }

        public void get(TreeNode cur , int[] res){

            if(cur.left == null && cur.right == null){

                res[0] = 0;
                res[1] = 0;
                return;
            }

            //单边最长，绝对能最长

            int rightOneMax , rightRealMax , leftOneMax , leftRealMax;
            if(cur.left != null){
                get(cur.left , res);
                leftOneMax = res[0];
                leftRealMax = res[1];
            }else{
                leftOneMax = -1;
                leftRealMax = 0;
            }

            if(cur.right != null){

                get(cur.right , res);
                rightOneMax = res[0];
                rightRealMax = res[1];
            }else{

                rightOneMax = -1;
                rightRealMax = 0;
            }



            res[0] = Math.max(leftOneMax , rightOneMax) + 1;
            res[1] = Math.max
                    (Math.max(leftRealMax , rightRealMax),
                            leftOneMax + rightOneMax + 2);
        }
    }
}
