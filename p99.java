public class p99 {


    /**
     * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。
     * 请在不改变其结构的情况下，恢复这棵树 。
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
        //1000个节点
        public void recoverTree(TreeNode root) {


            TreeNode pre = new TreeNode(Integer.MIN_VALUE);

            TreeNode node = root;

            TreeNode x = null;
            TreeNode y = null;


            //进行中序遍历,第二次到达某个节点的时候进行检验
            while(node!=null){

                if(node.left==null){

                    //x为空的时候要找到第一个大于后面一个节点的节点
                    //y为空的时候要找到都一个小于前面节点的
                    if(x == null && pre.val > node.val) {

                        x = pre;
                    }

                    if(pre.val > node.val){

                        y = node;
                    }

                    pre = node;
                    node = node.right;
                }else{

                    TreeNode leftTreeMostRight = node.left;
                    while(leftTreeMostRight.right!=null&&leftTreeMostRight.right!=node){
                        leftTreeMostRight = leftTreeMostRight.right;
                    }

                    if(leftTreeMostRight.right==null){
                        leftTreeMostRight.right = node;
                        node = node.left;
                    }else{
                        leftTreeMostRight.right = null;

                        if(x == null && pre.val > node.val){

                            x = pre;
                        }

                        if(pre.val > node.val){

                            y = node;
                        }


                        pre = node;
                        node = node.right;
                    }
                }
            }

            swap(x , y);

        }


        public void swap(TreeNode x,TreeNode y){


            int temp = x.val;
            x.val = y.val;
            y.val = temp;
        }



    }
}
