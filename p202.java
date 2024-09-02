public class p202 {

    /**
     * 编写一个算法来判断一个数 n 是不是快乐数。
     *
     * 「快乐数」 定义为：
     *
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果这个过程 结果为 1，那么这个数就是快乐数。
     * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
     */


    //快慢指针，如果快指针指到了1或者就是对的，如果两个指针相撞那就是循环
    class Solution {
        public boolean isHappy(int n) {

            int slow = getNext(n);
            int fast = getNext(getNext(n));

            while(slow != fast){

                if(fast == 1)
                    return true;

                slow = getNext(slow);
                fast = getNext(getNext(fast));
            }

            return fast == 1;
        }

        public int getNext(int n){

            int sum =0;

            while(n>0){
                int digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }

            return sum;
        }


    }
}
