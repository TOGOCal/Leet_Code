import java.util.HashSet;
import java.util.Set;

public class p421 {

    /**
     * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
     */

    public static void main(String[] args) {

        System.out.println(new Solution2().findMaximumXOR(new int[]{3,10,5,25,2,8}));
    }


    static
    class Solution2 {
        public int findMaximumXOR(int[] nums) {
            //1001
            //1110
            //0111
            int max = 0;
            for(int n: nums)
                max = Math.max(n, max);
            //得到最大值


            Set<Integer> set = new HashSet<>();
            int bitmask = 0; //store previous values
            int highbit = 31 - Integer.numberOfLeadingZeros(max);//获得最高位的1在哪一位


            int res = 0; //final anser
            for(int i = highbit;i >= 0;i--){
                set.clear();
                bitmask |= 1 << i;
                int newres = res | (1 << i); //如果这一位能是1，
                for(int n: nums){
                    int x = n & bitmask;//得到这一位是什么
                    //a^b == newans
                    //b = newans^a;
                    //我希望这一位是1，看有没有数能够满足这个条件
                    if(set.contains(newres ^ x)){
                        res = newres;//能够取得这个答案
                        break;
                    }
                    set.add(x);
                }
            }
            return res;
        }
    }




    static
    class Solution {
        public int findMaximumXOR(int[] nums) {

            root = new Node();
            for(int i : nums)
                buildTree(i);

            int max = Integer.MIN_VALUE;
            for(int i : nums)
                max = Math.max(getMax(i) , max);

            return max;
        }

        //i在树中能够找到的最大值
        public int getMax(int i){

            //希望尽量与i不同
            Node cur = root;
            int res = 0;
            for(int j = 31 ; j >= 0 ; j --){

                int a = ((i & (1 << j)) == 0) ? 0 : 1;
                if(a == 0){//希望能向右走

                    if(cur.right != null){

                        cur = cur.right;//可以向右走
                        res = setOne(res , j);
                    }else{
                        //只能向左走
                        cur = cur.left;
                        res = setZero(res , j);
                    }
                }else{//希望能向左走

                    if(cur.left != null){

                        cur = cur.left;//可以向左走
                        res = setOne(res , j);
                    }else{
                        //只能向右走
                        cur = cur.right;
                        res = setZero(res , j);
                    }
                }
            }

            return res;
        }

        public int setOne(int n ,int p){

            return n | (1 << p);
        }

        public int setZero(int n , int p){

            return n & (~(1 << p));
        }

        public void buildTree(int i){

            Node cur = root;
            for(int j = 31 ; j >= 0 ; j--){

                //1向右，0向左
                if((i & (1 << j)) != 0){

                    if(cur.right == null)
                        cur.right = new Node();
                    cur = cur.right;
                }else{

                    if(cur.left == null)
                        cur.left = new Node();
                    cur = cur.left;
                }
            }
        }

        public Node root;

        public class Node{

            Node left;
            Node right;
        }
    }
}
