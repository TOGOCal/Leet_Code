import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class p987 {

    /**
     * 给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
     *
     * 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。
     *
     * 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。
     *
     * 返回二叉树的 垂序遍历 序列。
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

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(new Solution().verticalTraversal(root));
    }


    class Solution1 {

        public static int MAXN = 1000 + 1;
        public static long[] forSort = new long[MAXN];
        public static int index;

        private long func(int col ,int row , int num){

            return ((((long)(col + MAXN) << 11) + row) << 11) + num;
        }

        private int getCol(long val){

            return (((int)(val >> 22)) & 0b111_111_111_1) - MAXN;
        }

        private int getNum(long val){

            return ((int)val & 0b111_111_111_1);
        }

        public List<List<Integer>> verticalTraversal(TreeNode root) {

            index = 0;
            dfs(root , 0 , 0);
            Arrays.sort(forSort , 0 , index);

            List<List<Integer>> res = new LinkedList<>();
            List<Integer> r = new LinkedList<>();
            r.add(getNum(forSort[0]));
            int nowCol = getCol(forSort[0]);
            for(int i = 1 ; i < index ; i ++){

                int thisCol = getCol(forSort[i]);
                if(thisCol != nowCol){

                    res.add(r);
                    r = new LinkedList<>();

                    nowCol = thisCol;
                    r.add(getNum(forSort[i]));
                }else {

                    r.add(getNum(forSort[i]));
                }
            }

            res.add(r);
            return res;
        }

        public void dfs(TreeNode node , int row , int col){

            if(node == null)
                return;

            forSort[index ++] = func(col , row , node.val);

            dfs(node.left , row + 1 , col - 1);
            dfs(node.right , row + 1 , col + 1);
        }

    }



    static
    class Solution {

        public static int MAXN = 1000 + 1;
        public static int[] nodeFirstEdge = new int[MAXN << 1 | 1];
        public static int[] edgeNextEdge = new int[MAXN];//最多创建一千个点
        public static int[] edgeTo = new int[MAXN];//包含的节点值

        public static int[] forSort = new int[MAXN];

        public static int cnt;
        public List<List<Integer>> verticalTraversal(TreeNode root) {

            cnt = 1;
            Arrays.fill(nodeFirstEdge , 0);
            dfs(root  , 0 , MAXN);

            List<List<Integer>> res = new LinkedList<>();
            for(int i = 0; i < nodeFirstEdge.length ; i ++){

                if(nodeFirstEdge[i] == 0)
                    continue;

                int index = 0;
                for(int edgeId = nodeFirstEdge[i]; edgeId != 0 ; edgeId = edgeNextEdge[edgeId])
                    forSort[index++] = edgeTo[edgeId];


                Arrays.sort(forSort , 0 , index);

                List<Integer> r = new LinkedList<>();
                for(int j = 0 ; j < index ; j ++)
                    r.add(getNum(forSort[j]));

                res.add(r);
            }

            return res;
        }

        //当前所在行，这个节点的值
        //由于排序需要是先计算行，所以行位于更高位
        //由于值的量在1000以内，因此可以使用1024来表示（后10位
        public int func(int row , int num){

            return (row << 11) + num;
        }

        public int getNum(int record){

            //取得后十为
            return record & 0b111_111_111_1;
        }


        public void dfs(TreeNode node , int row , int col){

            if(node == null)
                return;

            int pre = nodeFirstEdge[col];//之前的上一条边
            nodeFirstEdge[col] = cnt;
            edgeNextEdge[cnt] = pre;
            edgeTo[cnt] = func(row , node.val);
            cnt ++;

            dfs(node.left , row + 1 , col - 1);
            dfs(node.right , row + 1 , col + 1);
        }
    }
}
