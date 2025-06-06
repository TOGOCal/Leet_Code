import java.util.Arrays;

public class p1361 {

    /**
     * 二叉树上有 n 个节点，按从 0 到 n - 1 编号，其中节点 i 的两个子节点分别是 leftChild[i] 和 rightChild[i]。
     *
     * 只有 所有 节点能够形成且 只 形成 一颗 有效的二叉树时，返回 true；否则返回 false。
     *
     * 如果节点 i 没有左子节点，那么 leftChild[i] 就等于 -1。右子节点也符合该规则。
     *
     * 注意：节点没有值，本问题中仅仅使用节点编号。
     */

    public static void main(String[] args) {

        int[] arr = new int[8000];
        for(int i = 0 ; i < 8000 ; i ++)
            arr[i] = i - 1;
        int[] brr = new int[8000];
        Arrays.fill(brr , -1);
        System.out.println(new Solution().validateBinaryTreeNodes(8000 ,arr, brr));
    }

    static
    class Solution {

        /**
         * 此方法使用并查集
         * 还可以通过找到入度为0的点作为根节点进行遍历
         */

        public static int MAXN = 1_00_00 + 1;
        public static int[] size = new int[MAXN];
        public static int[] symbol = new int[MAXN];
        public static int[] inGre = new int[MAXN];

        public static void init(){

            Arrays.fill(size , 0);
            Arrays.fill(inGre , 0);
            for(int i = 0 ; i < MAXN ; i ++)
                symbol[i] = i;
        }

        public static int getSymbol(int a){

            int cur = a;
            while (symbol[cur] != cur)
                cur = symbol[cur];

            while (a != cur){

                int temp = symbol[a];
                symbol[a] = cur;
                a = temp;
            }

            return cur;
        }

        public static boolean isSameSet(int a , int b){

            return getSymbol(a) == getSymbol(b);
        }

        public static boolean setSameSet(int a , int b){

            a = getSymbol(a);
            b = getSymbol(b);
            if(a == b)
                return false;

            if(size[a] < size[b]){

                int temp = a;
                a = b;
                b = temp;
            }

            //将b挂在a下面
            symbol[b] = a;
            size[a] += size[b];
            return true;
        }

        public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {

            init();
            for(int i = 0 ; i < n ; i ++){

                int left = leftChild[i];
                int right = rightChild[i];
                if(left != -1){

                    //之前被放在一起了，说明此时图中有环
                    if(isSameSet(i , left) || inGre[left] == 1)
                        return false;
                    setSameSet(i , left);
                    inGre[left] = 1;
                }

                if(right != -1){

                    if(isSameSet(i , right) || inGre[right] == 1)
                        return false;
                    setSameSet(i , right);
                    inGre[right] = 1;
                }
            }

            int s = getSymbol(0);
            for(int i = 1 ; i < n ; i ++)
                if(getSymbol(i) != s)
                    return false;//说明有两棵树
            return true;
        }
    }
}
