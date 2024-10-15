public class p3200 {

    /**
     * 给你两个整数 red 和 blue，分别表示红色球和蓝色球的数量。
     * 你需要使用这些球来组成一个三角形，满足第 1 行有 1 个球，第 2 行有 2 个球，第 3 行有 3 个球，依此类推。
     *
     * 每一行的球必须是 相同 颜色，且相邻行的颜色必须 不同。
     *
     * 返回可以实现的三角形的 最大 高度。
     */


    public static void main(String[] args) {

        Solution s = new Solution();

        System.out.println(s.maxHeightOfTriangle(2,4));
    }


    //接下来尝试使用二分答案法
    static
    class Solution {
        public int maxHeightOfTriangle(int red, int blue) {

            return Math.max(binaryAnswer(red , blue) , binaryAnswer(blue , red));
        }

        private int binaryAnswer(int color1 , int color2){

            int left = 0;
            int right = 100;

            int res = 0;

            while(left <= right){

                int mid = (left + right) >> 1;

                int sum = allSum(mid);
                int odd = getOdd(mid);

                if(odd <= color1 && (sum - odd) <= color2){
                    res = mid;
                    left = mid + 1;
                }else{

                    right = mid - 1;
                }
            }

            return res;
        }

        private int getOdd(int n){

            int level = (n + 1) >> 1;

            int begin = 1;
            int end = 1 + ((level - 1) << 1);

            return ((begin + end) * level) >> 1;
        }

        private int allSum(int n){

            return ((1 + n) * n) >> 1;
        }

    }


    //其实也就两种情况，先选红还是先选蓝色
//    class Solution {
//        public int maxHeightOfTriangle(int red, int blue) {
//
//            int redRecord = red;
//            int blueRecord = blue;
//
//            int temp = 1;
//
//            //先选红
//            while(true){
//                if(red < temp){
//
//                    break;
//                }
//
//                red -= temp;
//                temp ++;
//
//                if(blue < temp){
//
//                    break;
//                }
//
//                blue -= temp;
//                temp ++;
//            }
//
//            int max = temp;
//            temp = 1;
//            red = redRecord;
//            blue = blueRecord;
//
//            while(true){
//                if(blue < temp){
//
//                    break;
//                }
//
//                blue -= temp;
//                temp ++;
//
//
//                if(red < temp){
//
//                    break;
//                }
//
//                red -= temp;
//                temp ++;
//            }
//
//            max = Math.max(temp , max);
//
//            return max - 1;
//        }
//    }
}
