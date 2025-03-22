package from_400_to_500;

import java.lang.management.MemoryNotificationInfo;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class p424 {

    /**
     * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
     *
     * 在执行上述操作后，返回 包含相同字母的最长子字符串的长度。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().characterReplacement("BAAA", 0));
    }


    static
    class Solution {

        public static int MAXN = 26;
        public static int[] max = new int[MAXN << 2];
        public static void set(int index , int value,
                               int nowLeft , int nowRight , int treeIndex){

            if(nowLeft == nowRight){

                max[treeIndex] += value;
                return;
            }

            int mid = (nowLeft + nowRight) >> 1;
            if(index <= mid)
                set(index , value ,
                        nowLeft , mid , treeIndex << 1);
            if(mid < index)
                set(index , value ,
                        mid + 1 , nowRight , treeIndex << 1 | 1);

            max[treeIndex] = Math.max(max[treeIndex << 1] , max[treeIndex << 1 | 1]);
        }

        public static int get(){

            return max[1];
        }

        public static void init(){

            Arrays.fill(max , 0);
        }

        public boolean isSuitable(int l , int r , int k){

            int num = r - l + 1;
            return num - get() <= k;
        }

        public int characterReplacement(String s, int k) {

            if(s == null || s.isEmpty())
                return 0;
            if(s.length() == 1)
                return 1;

            init();
            int left = 0;
            int right = 0;//维护更改k个字符能够变成全部一样的字符串的左右边界

            char[] str = s.toCharArray();
            set(str[0] - 'A', 1 ,
                    0, 25 , 1);


            int res = 0;

            while (right < s.length()){

                while(isSuitable(left , right, k)){

                    right ++;
                    set(str[right] - 'A' , 1 ,
                            0 , 25 , 1);

                    if(right == s.length() - 1){

                        if(!isSuitable(left , right , k)){

                            right --;
                        }

                        res = Math.max(res, right - left + 1);
                        return res;
                    }
                }

                //跳出循环说明要么超过了length，要么超过了k
                if(right != s.length() - 1){

                    set(str[right] - 'A' , -1 ,
                            0 , 25 , 1);
                    right --;
                }
                //统计
                res = Math.max(res , right - left + 1);

                set(str[left] - 'A' , -1 ,
                        0 , 25 , 1);
                left++;
            }


            return res;
        }


    }
}
