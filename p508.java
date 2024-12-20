import java.util.*;

public class p508 {

    /**
     * 给你一个二叉树的根结点 root ，请返回出现次数最多的子树元素和。
     * 如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
     * 一个结点的 「子树元素和」 定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
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

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-3);
        System.out.println(Arrays.toString(new Solution().findFrequentTreeSum(root)));
    }


    static
    //离散化
    class Solution {
        public static int MAXN = 10001;
        public static int[] record = new int[MAXN];
        public static int realLength = 0;

        public int getSum(TreeNode node){

            int sum = node.val;
            if(node.left != null)
                sum += getSum(node.left);


            if(node.right != null)
                sum += getSum(node.right);


            record[realLength++] = sum;
            return sum;
        }

        public int[] findFrequentTreeSum(TreeNode root) {

            realLength = 0;

            getSum(root);

            Arrays.sort(record , 0 , realLength);

            int maxSize = 0;
            List<Integer> res = new ArrayList<>();

            for(int i = 0 ; i < realLength ; i++){

                int num = record[i];
                i++;
                int size = 1;
                while(i < realLength && record[i] == num){
                    size++;
                    i++;
                }
                if(size > maxSize){

                    maxSize = size;
                    res.clear();
                    res.add(num);
                }else if(size == maxSize)
                    res.add(num);

                i--;
            }

            int[] r = new int[res.size()];
            for(int i = 0 ; i < r.length;i++)
                r[i] = res.get(i);
            return r;
        }



    }
}
