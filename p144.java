import java.util.ArrayList;
import java.util.List;

public class p144 {

    /**
     * 二叉树的心虚遍历
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
        public List<Integer> preorderTraversal(TreeNode root) {

            TreeNode nowNode = root;

            /*
            有左节点则向找左树的最右节点
            最右节点的右节点如果指向空则指向当前节点，同时当前节点向左移动
            如果最右节点的右节点指向当前节点，则指向空，当前节点向右移动
             */

            List<Integer> res = new ArrayList<>();

            while(nowNode!=null){

                if(nowNode.left!=null){

                    TreeNode mostRightNode = nowNode.left;

                    while(mostRightNode.right!=null&&mostRightNode.right!=nowNode){

                        mostRightNode = mostRightNode.right;
                    }

                    if(mostRightNode.right==null){
                        //第一次到达这个节点
                        res.add(nowNode.val);
                        mostRightNode.right = nowNode;
                        nowNode = nowNode.left;
                    }else{

                        mostRightNode.right = null;
                        nowNode = nowNode.right;
                    }

                }else{


                    res.add(nowNode.val);
                    nowNode = nowNode.right;
                }
            }

            return res;

        }
    }

}
