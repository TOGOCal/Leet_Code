
/**
 * 给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
 * 
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * 
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * 
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 */

import java.util.Scanner;
import java.util.HashMap;

public class P1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String input = s.nextLine();// 输入1
        int num = s.nextInt();// 输入2

        StringBuilder sb = new StringBuilder();
        int index = 0;

        HashMap<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) <= '9' && input.charAt(i) >= '0') {

                sb.append(input.charAt(i));
            }

            else if (input.charAt(i) == ',' || input.charAt(i) == ']') {

                int a = Integer.valueOf(sb.toString());
                if (indexMap.containsKey(a)) {

                    if (a == num / 2) {

                        System.out.println("[" + indexMap.get(a) + "," + index + "]");
                    }
                } // 相同的情况

                if (indexMap.containsKey(num - a)) {

                    System.out.println("[" + indexMap.get(num - a) + "," + index + "]");
                }

                indexMap.put(a, index);// 找不到则存入并等待下一个机会
                index++;
                sb.delete(0, sb.length());
            }
        } // 完成存储

        s.close();
    }

}

class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> indexMap = new HashMap<>();

        int arr[] = new int[2];

        for (int i = 0; i < nums.length; i++) {

            if (indexMap.containsKey(target - nums[i])) {

                arr[0] = indexMap.get(target - nums[i]);
                arr[1] = i;
                return arr;
            } else {

                indexMap.put(arr[i], i);
            }
        }

        return null;
    }
}
