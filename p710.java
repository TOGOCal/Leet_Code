import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class p710 {

    /**
     * 给定一个整数 n 和一个 无重复 黑名单整数数组 blacklist 。设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个 未加入 黑名单 blacklist 的整数。任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 同等的可能性 被返回。
     *
     * 优化你的算法，使它最小化调用语言 内置 随机函数的次数。
     *
     * 实现 Solution 类:
     *
     * Solution(int n, int[] blacklist) 初始化整数 n 和被加入黑名单 blacklist 的整数
     * int pick() 返回一个范围为 [0, n - 1] 且不在黑名单 blacklist 中的随机整数
     */

    public static void main(String[] args) {

        new Solution(4 , new int[]{0 , 1});
    }

    static
    class Solution {

        public static Random random = new Random();
        public HashMap<Integer , Integer> get = new HashMap<>();
        public HashSet<Integer> set = new HashSet<>();
        public static int real;

        public Solution(int n, int[] blacklist) {

            real = n - blacklist.length;

            for(int b  :blacklist){

                if(b >= real)
                    set.add(b);
            }

            int w = real;
            for(int b : blacklist){
                if(b < real){

                    while(set.contains(w))
                        w++;
                    get.put(b , w++);
                }
            }
        }

        public int pick() {

            int n = random.nextInt(real);
            return get.getOrDefault(n , n);
        }
    }


    //超时
    class Solution2 {

        public static int[] arr;
        public static int real;
        public Solution2(int n, int[] blacklist) {

            arr = blacklist;
            Arrays.sort(arr);
            real = n - arr.length;
        }

        public int pick() {

            int which = (int)(Math.random() * real);
            int pre = binaryFound(which);
            which += pre;
            while(binaryFound(which) - pre != 0){

                int t = binaryFound(which);//现在所有
                which += t - pre;//增加过程中加入的所有
                pre = t;
            }

            return which;
        }

        //在这个范围内的黑名单有多少个数
        private int binaryFound(int n){

            int left = 0;
            int right = arr.length - 1;
            int res = -1;
            while(left <= right){

                int mid = (left + right) >> 1;
                if(arr[mid] < n){

                    res = mid;
                    left = mid + 1;
                }else if(n < arr[mid]){

                    right = mid -1;
                }else{

                    return mid + 1;
                }
            }

            return res + 1;
        }
    }
}
