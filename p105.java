import java.util.HashMap;
import java.util.HashSet;

public class p105 {

    /**
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
     * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     */


    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
         int[] inorder = {9,3,15,20,7};
        Solution solution = new Solution();
        TreeNode treeNode = solution.buildTree(preorder, inorder);
        System.out.println(treeNode);
    }


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


    static
    class Solution {

        //HashMap<Integer, Integer> valueIndexMap;
        int[] valueIndexMap = new int[6001];

        public TreeNode buildTree(int[] preorder, int[] inorder) {

            //valueIndexMap = new HashMap<>();

            for(int i = 0; i < inorder.length; i++){

                //valueIndexMap.put(inorder[i], i);
                valueIndexMap[inorder[i]+3000] = i;
            }

            return dfs(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        }

        /*
            先序遍历的情况是   头    左先遍历情况    右先遍历情况
            中序遍历的情况是   左先遍历情况   头    右先遍历情况
            递归构建思路：确定当前每个树的头节点，再分别递归构建两棵树，最后将两棵树连接起来
             */
        public TreeNode dfs(int[] preorder, int[] inorder,
                        int limitPreLeft , int limitPreRight ,
                        int limitInoLeft , int limitInoRight){

            if(limitPreLeft > limitPreRight || limitInoLeft > limitInoRight){

                return null;
            }

            if(limitPreLeft == limitPreRight){//由于两个数组同步变化，所以只需要判断一个人就行了

                return new TreeNode(preorder[limitPreLeft]);
            }

            TreeNode root = new TreeNode(preorder[limitPreLeft]);//先序遍历的第一个元素就是头节点
            //visited.add(preorder[limitPreLeft]);//加入过这个点的值了，就不再加入

            //int headPosition = valueIndexMap.get(preorder[limitPreLeft]);
            int headPosition = valueIndexMap[preorder[limitPreLeft]+3000];
//            for(headPosition = limitInoLeft; headPosition <= limitInoRight; headPosition++){
//
//                if(inorder[headPosition] == preorder[limitPreLeft]) {
//
//                    break;
//                }
//            }

            int leftTreeSize = headPosition - limitInoLeft;//headPosition -1 - limitInoLeft + 1
            //找到头节点是中序遍历的哪里
            root.left = dfs(preorder, inorder,
                    limitPreLeft + 1, limitPreLeft + leftTreeSize,//+1 -1
                    limitInoLeft, headPosition - 1);//构建左子树

            root.right = dfs(preorder, inorder,
                            limitPreLeft + leftTreeSize + 1, limitPreRight,
                            headPosition + 1, limitInoRight);//构建右子树


            return root;
        }
    }
}
