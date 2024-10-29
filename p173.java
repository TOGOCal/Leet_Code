public class p173 {

    /**
     * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
     * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
     * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
     * int next()将指针向右移动，然后返回指针处的数字。
     * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
     *
     * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
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

    class BSTIterator {

        public BSTIterator(TreeNode root) {

            cur = root;
        }

        public static TreeNode cur;

        public int next() {

            while(cur != null){

                if(cur.left == null){

                    int res = cur.val;
                    cur = cur.right;

                    return res;
                }else{

                    TreeNode mostRight = cur.left;

                    while(mostRight.right != null && mostRight.right != cur){

                        mostRight = mostRight.right;
                    }

                    //第二次到达
                    if(mostRight.right == cur){

                        int res = cur.val;
                        mostRight.right = null;
                        cur = cur.right;

                        return res;
                    }else{
                        // == null
                        mostRight.right = cur;
                        cur = cur.left;
                    }
                }
            }

            return 0;
        }

        public boolean hasNext() {


            if(cur != null){

                if(cur.left == null){

                    return cur.right != null;
                    //cur = cur.right;
                }else{

                    TreeNode mostRight = cur.left;

                    while(mostRight.right != null && mostRight.right != cur){

                        mostRight = mostRight.right;
                    }

                    //第二次到达
                    if(mostRight.right == cur){

                        return cur.right != null;
                    }else{
                        // == null
                        return true;
                    }
                }
            }

            return false;
        }
    }


//    class BSTIterator {
//
//        public static int MAXN = 100001;
//        public static int[] arr = new int[MAXN];
//        public static int length;
//        static int p;
//
//        public BSTIterator(TreeNode root) {
//
//            p = -1;
//
//            TreeNode cur = root;
//            int index = 0;
//            while(cur != null){
//
//                if(cur.left == null){
//
//                    arr[index++] = cur.val;
//                    cur = cur.right;
//                }else{
//
//                    TreeNode mostRight = cur.left;
//
//                    while(mostRight.right != null && mostRight.right != cur){
//
//                        mostRight = mostRight.right;
//                    }
//
//                    //第二次到达
//                    if(mostRight.right == cur){
//
//                        arr[index++] = cur.val;
//                        mostRight.right = null;
//                        cur = cur.right;
//                    }else{
//                        // == null
//                        mostRight.right = cur;
//                        cur = cur.left;
//                    }
//                }
//            }
//
//            length = index;
//        }
//
//        public int next() {
//
//            p++;
//            return arr[p];
//        }
//
//        public boolean hasNext() {
//
//            //p = length -1 就已经返回false了
//            return p < length -1;
//        }
//    }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
