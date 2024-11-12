import java.util.ArrayList;
import java.util.List;

public class p118 {

    /**
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     *
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     */



    class Solution {
        public List<List<Integer>> generate(int numRows) {

            if( numRows == 0){

                return new ArrayList<>();
            }

            List<List<Integer>> res = new ArrayList<>();
            List<Integer> pre = new ArrayList<>();

            pre.add(1);
            res.add(pre);
            for(int i = 1; i < numRows; i++){


                List<Integer> list = new ArrayList<>();
                list.add(1);
                for(int j = 0; j < i-1; j++){

                    list.add(pre.get(j) + pre.get(j + 1));
                }

                list.add(1);
                res.add(list);
                pre = list;
            }

            return res;
        }
    }
}
