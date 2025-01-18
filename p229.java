package from_200_to_300;

import java.util.ArrayList;
import java.util.List;

public class p229 {

    /**
     * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     */


    public static void main(String[] args) {

        System.out.println(new Solution().majorityElement(new int[]{2, 1, 1, 3, 1, 4, 5, 6}));
    }


    static
    class Solution {

        public List<Integer> majorityElement(int[] nums) {

            int n1 = -1;
            int n2 = -2;
            int hp1 = 0;
            int hp2 = 0;

            for(int n : nums){

                if(n1 == n)
                    hp1++;
                else if(n2 == n)
                    hp2++;
                else{

                    if(hp1 == 0){

                        hp1 = 1;
                        n1 = n;
                    }else if(hp2 == 0){

                        hp2 = 1;
                        n2 = n;
                    }else {

                        if(hp1 > 0)
                            hp1--;
                        if(hp2 > 0)
                            hp2--;
                    }
                }
            }

            List<Integer> res = new ArrayList<>();
            int count1 = 0;
            int count2 = 0;
            for(int i : nums)
                if(i == n1)
                    count1++;
                else if(i == n2)
                    count2++;

            if(count1 > nums.length /3)
                res.add(n1);

            if(count2 > nums.length /3)
                res.add(n2);

            return res;
        }
    }
}
