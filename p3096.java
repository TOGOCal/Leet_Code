import java.util.Scanner;

public class p3096 {

    /**
     * 给你一个长度为 n 的二进制数组 possible 。
     * Alice 和 Bob 正在玩一个有 n 个关卡的游戏，游戏中有一些关卡是 困难 模式，其他的关卡是 简单 模式。
     * 如果 possible[i] == 0 ，那么第 i 个关卡是 困难 模式。
     * 一个玩家通过一个简单模式的关卡可以获得 1 分，通过困难模式的关卡将失去 1 分。
     * 游戏的一开始，Alice 将从第 0 级开始 按顺序 完成一些关卡，然后 Bob 会完成剩下的所有关卡。
     * 假设两名玩家都采取最优策略，目的是 最大化 自己的得分，Alice 想知道自己 最少 需要完成多少个关卡，才能获得比 Bob 更多的分数。
     * 请你返回 Alice 获得比 Bob 更多的分数所需要完成的 最少 关卡数目，如果 无法 达成，那么返回 -1 。
     * 注意，每个玩家都至少需要完成 1 个关卡。
     */

    public static void main(String[] args) {

        Scanner s= new Scanner(System.in);

        int[] arr = new int[s.nextInt()];

        for( int i = 0; i< arr.length; i++){

                arr[i] = s.nextInt();
        }

        Solution solution = new Solution();

        System.out.println(solution.minimumLevels(arr));

        s.close();
    }

    static class Solution {
        public int minimumLevels(int[] possible) {

            int l = possible.length;

            int allNum = 0;

            for(int p : possible){

                allNum+=p;
            }

            //数组中不是0就是1，
            //如果全是1，加上去就是n，但由于有些是0，导致最终得到了allNum
            //实际上的0还要减一 allNum = allNum - (n - allNum) = allNum*2 - n
            //                         这个变量是0的数量

            allNum = (allNum<<1) - l;

            int AliceScore = 0;
            if(possible[0] == 0){

                AliceScore--;
            }else{

                AliceScore++;
            }

            int i;
            for(i = 1; i< l-1; i++){

                if((AliceScore<<1) > allNum ){

                    break;
                }


                AliceScore+= (possible[i]<<1)-1;
            }

            if((AliceScore<<1) > allNum ){

                return i;
            }


            return -1;

        }
    }

//    static class Solution {
//        public int minimumLevels(int[] possible) {
//
//            if(possible == null || possible.length == 0 || possible.length == 1){
//
//                return 0;
//            }
//
//
//            int allNum = 0;
//
//            for(int i : possible){
//
//                if( i == 0){
//
//                    allNum--;
//                }else{
//
//                    allNum++;
//                }
//            }
//
//
//            //由于至少完成一关，所以做认为限定
//            int AliceScore = 0;
//            if(possible[0] == 0){
//
//                AliceScore--;
//            }else{
//
//                AliceScore++;
//            }
//
//            //为社么减一：最后一道题至少由bob完成
//            int i;
//            for(i = 1; i< possible.length; i++){
//
//                if(AliceScore > allNum - AliceScore ){
//
//                    break;
//                }
//
//                if(possible[i] == 0){
//
//                    AliceScore--;
//                }else{
//
//                    AliceScore++;
//                }
//            }
//
//            if(i == possible.length){
//
//                return -1;
//            }
//
//            return i;
//        }
//    }
}
