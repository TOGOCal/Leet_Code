public class p450 {

    /**
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     *
     * 一般来说，删除节点可分为两个步骤：
     *
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
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
        public TreeNode deleteNode(TreeNode root, int key) {

            TreeNode node = root;
            TreeNode pre = root;
            while(node != null && node.val != key){
                if(key < node.val){
                    pre= node;
                    node = node.left;
                }else{// if(node.val < key){
                    pre = node;
                    node = node.right;
                }
            }

            if(node == null)
                return root;

            //这个节点是头节点
            if(node == pre.left){

                pre.left = function(node);
            }else if(node == pre.right){

                pre.right = function(node);
            }else{
                //相等的情况，也就是node就是就是头节点
                //计算完之后直接返回即可
                return function(node);
            }

            return root;
        }


        //保证这个node不是空
        public TreeNode function(TreeNode node){

            if(node.left == null)
                return node.right;
            if(node.right == null)
                return node.left;

            //找到左边的最大值
            TreeNode leftMax = node.left;
            if(leftMax.right == null){

                leftMax.right = node.right;
                return leftMax;
            }

            TreeNode pre = leftMax;
            while(leftMax.right != null){

                pre = leftMax;
                leftMax = leftMax.right;
            }

            pre.right = leftMax.left;//将这个置为空
            leftMax.left = node.left;
            leftMax.right = node.right;

            return leftMax;
        }

    }
}
