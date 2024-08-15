public class p572 {

    /**
     * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
     *
     * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
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

        Solution solution = new Solution();

//        TreeNode root = new TreeNode(3,
//                new TreeNode(4, new TreeNode(1), new TreeNode(2))
//                , new TreeNode(5));
//
//        TreeNode subRoot = new TreeNode(4, new TreeNode(1), new TreeNode(2));

        TreeNode root = new TreeNode(12);
        TreeNode subRoot = new TreeNode(2);

        System.out.println(solution.isSubtree(root, subRoot));
    }

    static
//    class Solution {
//        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
//
//            if (subRoot == null && root == null) return true;
//            if (root == null || subRoot == null) return false;
//
//            if(root.val == subRoot.val && isSubtree(root.left,subRoot.right) && isSubtree(root.right,subRoot.left)){
//
//                return true;
//            }
//
//            return isSubtree(root.left,subRoot) || isSubtree(root.right,subRoot);
//
//        }
//    }


    class Solution {

        StringBuilder sb1;
        StringBuilder sb2;

        public boolean isSubtree(TreeNode root, TreeNode subRoot) {

            sb1 = new StringBuilder();
            sb2 = new StringBuilder();

            treeToString(root,sb1);
            treeToString(subRoot,sb2);

            return sb1.toString().contains(sb2.toString());

        }

        public void treeToString(TreeNode node, StringBuilder sb){

            if(node == null){
                sb.append("#");
                return;
            }

            sb.append((char)(node.val));
            treeToString(node.left,sb);
            treeToString(node.right,sb);
        }

    }
}

//public String treeToString(TreeNode root){
//
//    StringBuilder sb = new StringBuilder();
//
//    if(root == null){
//
//        return sb.toString();
//    }
//
//    TreeNode node = root;
//
//    while(node != null){
//
//        if(node.left == null){
//
//            sb.append(node.val).append("|");
//            sb.append("#|");
//
//            node = node.right;
//        }else{
//
//            TreeNode leftTreeMostRight = node.left;
//
//            while(leftTreeMostRight.right != null && leftTreeMostRight.right != node){
//
//                leftTreeMostRight = leftTreeMostRight.right;
//            }
//
//
//            //第一次到某个点
//            if(leftTreeMostRight.right == null){
//
//                leftTreeMostRight.right = node;
//
//                sb.append(node.val).append("|");
//
//                node = node.left;
//            }else{
//
//                leftTreeMostRight.right = null;
//
//                node = node.right;
//            }
//        }
//    }
//
//    sb.append("#");
//    return sb.toString();
//}