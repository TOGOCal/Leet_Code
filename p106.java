import java.util.HashMap;

public class p106 {

    /**
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
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

    //中序遍历 左 头 右
    //后序遍历 左 右 头
    class Solution {

        HashMap<Integer , Integer> foundPosition;

        public TreeNode buildTree(int[] inorder, int[] postorder) {

            foundPosition = new HashMap<>();

            for (int i = 0; i < inorder.length; i++) {

                foundPosition.put(inorder[i] , i);
            }

            return dfs( inorder,postorder , 0 , inorder.length-1 , 0 , postorder.length -1);
        }

        public TreeNode dfs(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {
            if (inStart > inEnd || postStart > postEnd) {
                return null;
            }

            int rootVal = postorder[postEnd];//找到头节点
            TreeNode root = new TreeNode(rootVal);
            int rootIndex = foundPosition.get(rootVal);//找到头节点在中序遍历中的位置
//            for (int i = inStart; i <= inEnd; i++) {
//                if (inorder[i] == rootVal) {
//                    rootIndex = i;
//                    break;
//                }
//            }


            int leftSize = rootIndex - inStart;
            root.left = dfs(inorder, postorder, inStart, rootIndex - 1, postStart , postStart + leftSize - 1);
            root.right = dfs(inorder, postorder, rootIndex + 1, inEnd, postStart + leftSize, postEnd - 1);

            return root;
        }
    }
}
