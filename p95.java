import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class p95 {

    /**
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     */


    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        new Solution().generateTrees(s.nextInt());

        s.close();
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
            ;
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }



    static class Solution {

        //HashMap<Integer , List<TreeNode>> foundCondition = new HashMap<>();

        public List<TreeNode> generateTrees(int n) {

            if(n == 0){
                return null;
            }

            return buildTree(1 , n);

        }

        //递归调用
        //选择以某个枚举点作为头
        //左右子树再进行头节点枚举

        public List<TreeNode> buildTree(int begin , int last){
            //节点范围

            if(begin > last){

                return null;
            }else if(begin == last){

                List<TreeNode> res = new ArrayList<>();
                res.add(new TreeNode(begin));
                return res;
            }//保证 left和right只有一个可能为空

            List<TreeNode> res = new ArrayList<>();

            for(int i = begin ; i <= last ; i++){

                TreeNode head = new TreeNode(i);

                //List<TreeNode> recordHead = new ArrayList<>();
                List<TreeNode> Left = buildTree(begin , i-1);
                List<TreeNode> Right = buildTree(i+1 , last);//这两个应该进行的是多项搭配

                if(Left == null){

                    head.left = null;

                    for(TreeNode right : Right){

                        head.right = right;
                        res.add(head);
                        //recordHead.add(head);
                        //新建一个新的head
                        head = new TreeNode(i);
                        head.left = null;
                    }


                }else{//左树不为空

                    if(Right == null){//右树为空

                        head.right = null;

                        for(TreeNode left : Left){

                            head.left = left;
                            res.add(head);
                            //recordHead.add(head);

                            head = new TreeNode(i);
                            head.right = null;
                        }
                    }else{//右树不为空

                        for(TreeNode right : Right){

                            head.right = right;
                            for(TreeNode left : Left){

                                head.left = left;
                                res.add(head);
                                //recordHead.add(head);

                                head = new TreeNode(i);
                                head.right = right;
                            }
                        }

                    }


                }



            }

            return res;
        }
    }
}
