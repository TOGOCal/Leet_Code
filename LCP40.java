import java.util.Arrays;
import java.util.Comparator;

public class LCP40 {

    /**
     * 「力扣挑战赛」心算项目的挑战比赛中，
     * 要求选手从 N 张卡牌中选出 cnt 张卡牌，
     * 若这 cnt 张卡牌数字总和为偶数，
     * 则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
     * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。
     * 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
     */

    public static void main(String[] args) {

        Solution solution = new Solution();

        int[] cards = {7 ,6, 4,6};
        int cnt = 1;
        System.out.println(solution.maxmiumScore(cards, cnt));
    }


    static
    class Solution {

        public int maxmiumScore(int[] cards, int cnt) {

            quickSort(cards);

            int minOdd = Integer.MAX_VALUE;
            int minEven = Integer.MIN_VALUE;

            int sum = 0;

            for(int i = 0;i<cnt;i++){

                sum+= cards[i];

                if(cards[i] % 2 == 0) {

                    minEven = cards[i];
                }else{

                    minOdd = cards[i];
                }

            }

            if(sum % 2 == 0) {
                return sum;
            }

            int possible1 = Integer.MIN_VALUE;
            if(minOdd != Integer.MIN_VALUE) {

                for(int i = cnt;i<cards.length;i++) {

                    if(cards[i] % 2 == 0) {

                        possible1 =  sum - minOdd + cards[i];
                        break;
                    }
                }
            }

            int possible2 = Integer.MIN_VALUE;
            if(minEven != Integer.MIN_VALUE) {

                for(int i = cnt;i<cards.length;i++) {

                    if(cards[i] % 2 != 0) {

                        possible2 =  sum - minEven + cards[i];
                        break;
                    }
                }
            }

            if(possible1 != Integer.MIN_VALUE && possible2 != Integer.MIN_VALUE) {

                return Math.max(possible1, possible2);
            }else if(possible1 != Integer.MIN_VALUE) {

                return possible1;
            }else if(possible2 != Integer.MIN_VALUE) {

                return possible2;
            }else{
                return 0;
            }
        }


        public void quickSort(int[] cards) {

            int[] arr = new int[1000];

            for(int i = 0;i<cards.length;i++) {

                arr[cards[i]-1]++;
            }

            int index = 0;
            for(int i = 999;i>=0;i--) {

                for(int j = 0;j<arr[i];j++) {

                    cards[index++] = i+1;
                }
            }

        }

//        public void quickSort(int[] cards, int left, int right) {
//
//            if( left >= right) {
//                return;
//            }
//
//            int random = cards[left + (int)(Math.random() * (right - left + 1))];
//
//            int p1 = left-1;
//            int p2 = right+1;
//
//            for(int i = left; i < p2; i++) {
//
//                if(cards[i] > random){
//
//                    p1++;
//                    swap(cards, i, p1);
//                }else if( cards[i] < random) {
//
//                    p2--;
//                    swap(cards, i, p2);
//                    i--;
//                }
//
//            }
//
//            quickSort(cards, left, p1);
//            quickSort(cards, p2, right);
//        }
//
//        public void swap(int[] cards, int i, int j) {
//
//            int temp = cards[i];
//            cards[i] = cards[j];
//            cards[j] = temp;
//        }

    }
}
