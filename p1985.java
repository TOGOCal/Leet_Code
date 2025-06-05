import java.util.Comparator;

public class p1985 {
    /**
     * 给你一个字符串数组 nums 和一个整数 k 。nums 中的每个字符串都表示一个不含前导零的整数。
     *
     * 返回 nums 中表示第 k 大整数的字符串。
     *
     * 注意：重复的数字在统计时会视为不同元素考虑。
     * 例如，如果 nums 是 ["1","2","2"]，那么 "2" 是最大的整数，"2" 是第二大的整数，"1" 是第三大的整数。
     */

    public static void main(String[] args) {

        System.out.println(new Solution().kthLargestNumber(new String[]{"2","21","12","1"}, 3));
    }

    static
    class Solution {
        public String kthLargestNumber(String[] nums, int k) {
            //k--;
            k = nums.length - k;


            int left = 0,right = nums.length - 1;
            while (true) {
                if(left == right)
                    return nums[left];

                int p1 =  left - 1;
                int p2 = right + 1;
                String random = nums[(int)(Math.random() * (right - left + 1) + left)];
                for(int i = left ; i < p2 ; i ++){

                    int compare = compare(nums[i] , random);
                    if(compare < 0){

                        p1++;
                        swap(nums , i , p1);
                    }else if(compare > 0){

                        p2--;
                        swap(nums , i , p2);
                        i--;
                    }
                }

                //都是相等的部分
                //if((p1 + 1) <= k && k <= (p2 - 1))
                if(p1 < k && k < p2)
                    return nums[k];

                if(k <= p1)
                    right = p1;
                else
                    left = p2;
            }
        }

        private int compare(String a , String b){

            int l1= a.length();
            int l2 = b.length();
            if(l2 != l1)
                return l1 > l2 ? 1 : -1;

            for(int i = 0 ; i < l1  ; i ++){

                char ca = a.charAt(i);
                char cb = b.charAt(i);
                if(ca > cb)
                    return 1;
                if(ca < cb)
                    return -1;
            }
            return 0;
        }

        private void swap(String[] nums , int i , int j){

            String s = nums[i];
            nums[i] = nums[j];
            nums[j] = s;
        }
    }
}