import java.util.ArrayList;
import java.util.List;

public class p77 {

    /**
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合
     * 你可以按 任何顺序 返回答案。
     */

    class Solution {

        List<List<Integer>> res;
        List<Integer> list;

        public List<List<Integer>> combine(int n, int k) {

            res = new ArrayList<>();
            list = new ArrayList<>();

            addList(n,k,0 , 0);

            return res;
        }

        //index表示当前长度为多少
        public void addList(int n , int k ,int index , int position){

            if(index == k){

                res.add(new ArrayList<>(list));
                return;
            }

            if(position == n) return;

            for(int i = position; i< n;i++){

                list.add(i+1);
                addList(n,k,index+1 , i+1);
                list.removeLast();
            }


        }
    }
}
