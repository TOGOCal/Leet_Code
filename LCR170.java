package LCR;

public class LCR170 {

    /**
     * 在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
     */


    //归并排序，相当于分析每个位置前面有多少个大于这个的数
    class Solution {

        public static int MAXN = 50001;
        public static int[] temp = new int[MAXN];


        public int reversePairs(int[] record) {

            return get(0 , record.length - 1 , record);
        }

        public int get(int left , int right , int[] record){

            if(left >= right)
                return 0;//这个区间内没有大小关系，故没有数量贡献

            int mid = (left + right) >> 1;
            int res = 0;
            res += get(left , mid , record);
            res += get(mid + 1 , right , record);


            int p1 = left;
            int p2 = mid + 1;
            int p = left;

            //逆序进行排序，也就是谁大谁在前面
            while (p1 <= mid && p2 <= right){

                //当需要把当前p2的数放进集合时，说明前面部分已经没有比p2大的了，直接加上前面的大小即可
                if(record[p1] <= record[p2]){

                    res += (p1 - left);
                    temp[p++] = record[p2++];
                }else{

                    temp[p++] = record[p1++];
                }
            }

            while(p1 <= mid){

                temp[p++] = record[p1++];
            }

            while (p2 <= right){

                res += (p1 - left);
                temp[p++] = record[p2++];
            }

            for(int i = left ; i <= right; i ++){

                record[i] = temp[i];
            }

            return res;
        }
    }
}
