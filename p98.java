public class p98 {


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
        public boolean isValidBST(TreeNode root) {


            return dfs(root).res;
        }

        public Information dfs(TreeNode node) {

            boolean res = true;
            int max = node.val;
            int min = node.val;

            if(node.left != null){

                res = dfs(node.left).res && res;

                if(!res){

                    return new Information(res);
                }


                res = node.val > dfs(node.left).max;



                min = Math.min(min, dfs(node.left).min);
                max = Math.max(max, dfs(node.left).max);
            }

            if(node.right != null){

                res = dfs(node.right).res && res;

                if(!res){
                    return new Information(res);
                }

                res = node.val < dfs(node.right).min;
                min = Math.min(min, dfs(node.right).min);
                max = Math.max(max, dfs(node.right).max);
            }

            return new Information(res, max, min);
        }

        class Information{

            boolean res;
            int max;
            int min;

            Information(boolean res){
                this.res = res;
            }

            Information(boolean res, int max, int min){
                this.res = res;
                this.max = max;
                this.min = min;
            }
        }
    }
}
