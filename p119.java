import java.util.ArrayList;
import java.util.List;

public class p119 {

    /**
     * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
     *
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     */


    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.getRow(2));
    }


    static
    class Solution {
        public List<Integer> getRow(int rowIndex) {

            List<Integer> list = new ArrayList<>();
            list.add(1);

            int pre = 1;

            for(int i = 1; i <= rowIndex; i++){


                for(int j = 0; j < i-1; j++){

                    int temp = list.get(j+1);

                    list.set(j + 1 ,pre + list.get(j + 1));

                    pre = temp;
                }

                list.add(1);
            }

            return list;
        }
    }

}
