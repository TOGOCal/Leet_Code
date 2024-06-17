import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p89 {

    /**
     * n 位格雷码序列 是一个由 2n 个整数组成的序列，其中：
     * 每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
     * 第一个整数是 0
     * 一个整数在序列中出现 不超过一次
     * 每对 相邻 整数的二进制表示 恰好一位不同 ，且
     * 第一个 和 最后一个 整数的二进制表示 恰好一位不同
     * 给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
     */

    /**
     * 每两位的变化规律是
     * 0 1 3 2 *2^0
     * 0 1 3 2 *2^2
     * 0 1 3 2 *2^4
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        Solution sol = new Solution();
        List<Integer> list =  sol.grayCode(s.nextInt());

        for (Integer i : list) {

            System.out.print(i + " ");
        }

        s.close();
    }

    static class Solution {

        public List<Integer> grayCode(int n) {
            List<Integer> res = new ArrayList<>();

            int endTime = 1<<n;
            for(int i =0; i < endTime;i++){
                res.add((i>>1)^i);
            }

            return res;
        }
    }


}

//static class Solution {
//
//        public List<Integer> grayCode(int n) {
//            List<Integer> res = new ArrayList<>();
//
//            res.add(0);
//
//            int s = 1;
//
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < s; j++) {
//
//                    res.add(s + res.get(s - 1 - j));
//                }
//                s <<= 1;
//            }
//
//            return res;
//        }
//    }
//

//    static class Solution {
//
//        List<Integer> res = new ArrayList<>();
//        public List<Integer> grayCode(int n) {
//
//            int[] foundMap = new int[]{0,1,3,2};
//
//            if(n == 1){
//
//                res.add(0);
//                res.add(1);
//                return res;
//            }
//
//            for(int i =0 ;i< 4;i++){
//
//                res.add(foundMap[i]);
//            }
//
//            if(n == 2){
//
//                return res;
//            }
//
//            boolean positiveOrder = false;
//
//
//            int endTime = ((n>>1)<<1);//仅处理
//            int count =2;
//            while(count  < endTime){
//
//                positiveOrder = false;//越维变成倒着读取
//                int size = res.size();
//                for(int i =1 ;i< 4;i++){
//
//                    int addNum = (foundMap[i] << count);
//
//                    if(positiveOrder){
//
//
//                        for(int j = 0 ;j < size;j++){
//
//                            res.add(addNum + res.get(j));
//                        }
//
//                        positiveOrder = false;
//                    }else{
//
//                        for(int j = size -1;j>=0;j--){
//
//                            res.add(addNum + res.get(j));
//                        }
//
//                        positiveOrder = true;
//                    }
//
//                }
//
//                count+=2;
//
//            }
//
//            if( n%2 == 1){
//
//                int addNum = (1 << (n-1));
//
//                int size = res.size();
//                positiveOrder = false;//执行一次越维
//                if(positiveOrder){
//
//
//                    for(int j = 0 ;j<size;j++){
//
//                        res.add(addNum + res.get(j));
//                    }
//
//                }else{
//
//
//                    for(int j = size-1 ;j>=0;j--){
//
//                        res.add(addNum + res.get(j));
//                    }
//
//                }
//            }
//
//            return res;
//        }
//
//        //保证进这个函数的都是偶数情况
////        public void method(int[] foundMap , int n){
////
////            if(n == 2){
////
////                for(int i =0 ;i< 4;i++){
////
////                    res.add(foundMap[i]);
////                }
////                return;
////            }
////
////            method(foundMap,n-2);
////
////            int size = res.size();//防止后加进去的数
////
////            for(int i =0 ;i< 4;i++){
////
////                int addNum = (foundMap[i] << (n>>1));
////                for(int j = 0 ;j<size;j++){
////
////                    res.add(addNum + res.get(j));
////                }
////            }
////        }
//
//
//    }

