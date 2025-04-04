import com.sun.source.tree.Tree;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class p1123 {

    /**
     * 给你一个有根节点 root 的二叉树，返回它 最深的叶节点的最近公共祖先 。
     *
     * 回想一下：
     *
     * 叶节点 是二叉树中没有子节点的节点
     * 树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
     * 如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
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
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(8);
        root.left.left.left = new TreeNode(15);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(16);
        root.left.right.right = new TreeNode(13);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(12);
        System.out.println(new Solution().lcaDeepestLeaves(root));
    }


    class Solution2{

        public TreeNode lcaDeepestLeaves(TreeNode root) {

            return recursion(root).getKey();
        }

        public Map.Entry<TreeNode , Integer> recursion(TreeNode cur){

            if(cur == null)
                return new AbstractMap.SimpleEntry<>(cur , 0);

            Map.Entry<TreeNode , Integer> left = recursion(cur.left);
            Map.Entry<TreeNode , Integer> right = recursion(cur.right);

            if(left.getValue() > right.getValue()){

                left.setValue(left.getValue() + 1);
                return left;
            }

            if(right.getValue() > left.getValue()){

                right.setValue(right.getValue() + 1);
                return right;
            }

            return new AbstractMap.SimpleEntry<>(cur, left.getValue() + 1);
        }
    }

    static
    class Solution {

        public static int MAXN = 1001;
        public static int minBinary(int n){

            int i = 0;
            while ((1 << i) <= (n >> 1))
                i++;
            return i;
        }
        public static int[][] ST = new int[MAXN][minBinary(MAXN) + 1];
        public static int[] depth = new int[MAXN];

        private void set(TreeNode node , int father){

            int val = node.val;
            ST[val][0] = father;//第一个向上
            if(father != -1)
                depth[val] = depth[father] + 1;

            if(node.left != null)
                set(node.left , val);
            if(node.right != null)
                set(node.right , val);
        }

        private void buildST(){

            for(int i = 1 ; i < ST[0].length ; i ++){

                for(int j = 0 ; j < MAXN ; j ++){

                    ST[j][i] = ST[j][i - 1] == -1 ? -1 :
                            ST[
                                ST[j][i - 1]
                            ][i - 1];
                }
            }

//            for(int j = 0 ; j < MAXN ; j ++){
//
//                int[] s = ST[j];
//                for(int i = 1 ; i < ST[0].length ; i ++){
//
//                    s[i] = ST[s[i - 1]][i - 1];
//                }
//            }
        }

        public static int[] stack = new int[MAXN];
        public static int top;
        public static void stackInit(){

            top = 0;
        }
        public static int pop(){

            return stack[--top];
        }

        public static void push(int v){

            stack[top++] = v;
        }

        public static boolean isEmpty(){
            return top == 0;
        }


        public TreeNode lcaDeepestLeaves(TreeNode root) {

            Arrays.fill(depth , -1);
            depth[root.val] = 0;
            set(root , -1);
            buildST();

            int maxDepth = -1;
            stackInit();

            for(int i = 0 ; i < MAXN ; i ++){

                if(depth[i] > maxDepth){

                    stackInit();
                    push(i);
                    maxDepth = depth[i];
                }else if(depth[i] == maxDepth){

                    push(i);
                }
            }//得到所有在最后一行的点

            //跳出条件：top = 1
            while (top > 1)
                push(found(pop() , pop()));

            //（根据值）找到这个点
            int value = pop();
            TreeNode cur = root;
            TreeNode res = null;
            while (cur != null){

                if(cur.left == null){

                    if(cur.val == value)
                        res = cur;
                    cur = cur.right;
                }else{

                    TreeNode mostRight = cur.left;
                    while (mostRight.right != null && mostRight.right != cur)
                        mostRight = mostRight.right;

                    if(mostRight.right == null){

                        if(cur.val == value)
                            res = cur;
                        mostRight.right = cur;
                        cur = cur.left;
                    }else {

                        mostRight.right = null;
                        cur = cur.right;
                    }
                }
            }

            return res;
        }


        public int found(int a , int b){

            if(depth[a] < depth[b]){
                int temp = a;
                a = b;
                b = temp;
            }//保证a比b深

            int diff = depth[a] - depth[b];
            int i = 0;
            while (diff > 0){

                if((diff & 1) == 1)
                    a = ST[a][i];

                diff >>= 1;
                i++;
            }//保证a，b到达同一个深度

            if(a == b)
                return b;

            int d = depth[b];

            int min = minBinary(d);
            for(int j = min ; j >= 0 ; j --){

                if (ST[a][j] != ST[b][j]) {
                    a = ST[a][j];
                    b = ST[b][j];
                }
            }

            //再往上走一格
            return ST[a][0];
        }
    }
}
