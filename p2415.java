package from_2400_to_2500;

import java.util.LinkedList;
import java.util.Queue;

public class p2415 {

    /**
     * 给你一棵 完美 二叉树的根节点 root ，请你反转这棵树中每个 奇数 层的节点值。
     *
     * 例如，假设第 3 层的节点值是 [2,1,3,4,7,11,29,18] ，那么反转后它应该变成 [18,29,11,7,4,3,1,2] 。
     * 反转后，返回树的根节点。
     *
     * 完美 二叉树需满足：二叉树的所有父节点都有两个子节点，且所有叶子节点都在同一层。
     *
     * 节点的 层数 等于该节点到根节点之间的边数。
     *
     *
     */

    public static void main(String[] args) {


        TreeNode r = new TreeNode(2);
//        r.left = new TreeNode(3);
//        r.right = new TreeNode(5);
//        r.left.left = new TreeNode(8);
//        r.left.right = new TreeNode(13);
//        r.right.left = new TreeNode(21);
//        r.right.right = new TreeNode(34);
        System.out.println(new Solution().reverseOddLevels(r));
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

        public static int MAXN = (1 << 14) | 1;
        public static int[] record = new int[MAXN];

        public TreeNode reverseOddLevels(TreeNode root) {

            Queue<TreeNode> q = new LinkedList<>();

            int index = 1;
            q.add(root);
            while(!q.isEmpty()){

                TreeNode now = q.poll();
                record[index++] = now.val;

                if(now.left != null)
                    q.add(now.left);
                if(now.right != null)
                    q.add(now.right);
            }


            TreeNode node = new TreeNode(root.val);
            q.add(node);
            for(int level = 1 ; ; level ++){
                int begin = (1 << level);
                int end = (1 << (level + 1)) - 1;

                if(begin >= index)
                    break;

                int i;
                //倒着
                if((level & 1) == 1){

                    for(i = end ; i >= begin ;){

                        TreeNode now = q.poll();
                        now.left = new TreeNode(record[i--]);
                        q.add(now.left);
                        now.right = new TreeNode(record[i--]);
                        q.add(now.right);
                    }
                    i = end + 1;

                }else{

                    for(i = begin ; i <= end;){

                        TreeNode now = q.poll();
                        now.left = new TreeNode(record[i++]);
                        q.add(now.left);
                        now.right = new TreeNode(record[i++]);
                        q.add(now.right);
                    }
                }

            }

            return node;
        }
    }
}
