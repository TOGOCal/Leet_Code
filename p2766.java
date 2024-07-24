import java.util.*;

public class p2766 {

    /**
     * 给你一个下标从 0 开始的整数数组 nums ，表示一些石块的初始位置。再给你两个长度 相等 下标从 0 开始的整数数组 moveFrom 和 moveTo 。
     *
     * 在 moveFrom.length 次操作内，你可以改变石块的位置。在第 i 次操作中，你将位置在 moveFrom[i] 的所有石块移到位置 moveTo[i] 。
     *
     * 完成这些操作后，请你按升序返回所有 有 石块的位置。
     *
     * 注意：
     *
     * 如果一个位置至少有一个石块，我们称这个位置 有 石块。
     * 一个位置可能会有多个石块。
     */

    class Solution {
        public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {

            HashSet<Integer> haveStoneIndex = new HashSet<>();

            for( int i : nums){

                haveStoneIndex.add(i);
            }

            for( int i = 0; i < moveFrom.length; i++){

                haveStoneIndex.remove(moveFrom[i]);
                haveStoneIndex.add(moveTo[i]);
            }

            int[] res = new int[haveStoneIndex.size()];

            int i = 0;

            for( int j : haveStoneIndex){

                res[i++] = j;
            }

            Arrays.sort(res);


            List<Integer> resList = new ArrayList<>();
            for( int j : res){

                resList.add(j);
            }


            return resList;
        }
    }


//    class Solution {
//        public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
//
//            TreeSet<Integer> haveStoneIndex = new TreeSet<>();
//
//            for( int i : nums){
//
//                haveStoneIndex.add(i);
//            }
//
//            for( int i = 0; i < moveFrom.length; i++){
//
//                haveStoneIndex.remove(moveFrom[i]);
//                haveStoneIndex.add(moveTo[i]);
//            }
//
//
//            return new ArrayList<>(haveStoneIndex);
//        }
//
//    }
}
