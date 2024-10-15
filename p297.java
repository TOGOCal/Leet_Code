public class p297 {

    /**
     * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
     * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
     *
     * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
     * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     *
     * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，
     * 详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
     */

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        Codec c = new Codec();
        System.out.println(c.serialize(root));
    }



    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    static
    public class Codec {

        public static final int NULL = 100000;

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {

            if(root == null){

                return " ";
            }

            StringBuilder sb = new StringBuilder();

            while(root != null){

                if(root.left == null){

                    sb.append(root.val);
                    sb.append(" ");


                    sb.append(NULL);
                    sb.append(" ");

                    root = root.right;

                    continue;
                }

                TreeNode mostRight = root.left;

                while(mostRight.right != null && mostRight.right != root){

                    mostRight = mostRight.right;
                }

                //第一次到达这个节点
                if(mostRight.right == null){

                    sb.append(root.val);
                    sb.append(" ");

                    mostRight.right = root;
                    root = root.left;



                }else{

                    sb.append(NULL + " ");
                    mostRight.right = null;
                    root = root.right;
                }
            }

            sb.append(NULL);
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {

            String[] get = data.split(" ");

            index = 0;

            return buildDFS(get);
        }

        int index;
        private TreeNode buildDFS(String[] arr){

            if(index >= arr.length){

                return null;
            }

            int value = Integer.parseInt(arr[index]);
            index ++;


            if(value == NULL){

                return null;
            }

            TreeNode node = new TreeNode(value);

            node.left = buildDFS(arr);
            node.right = buildDFS(arr);

            return node;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
}
