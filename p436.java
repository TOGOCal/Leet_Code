package from_400_to_500;

import com.sun.source.tree.Tree;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class p436 {

    /**
     * 给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。
     *
     * 区间 i 的 右侧区间 是满足 startj >= endi，且 startj 最小 的区间 j。注意 i 可能等于 j 。
     *
     * 返回一个由每个区间 i 对应的 右侧区间 下标组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
     */

    class Solution {

        public int[] findRightInterval(int[][] intervals) {

            TreeMap<Integer , Integer> map = new TreeMap<>();
            for(int i = 0 ; i < intervals.length ; i++)
                map.put(intervals[i][0] , i);


            int[] res = new int[intervals.length];
            for(int i = 0 ; i < intervals.length; i++) {
                Map.Entry<Integer, Integer> integerIntegerEntry = map.ceilingEntry(intervals[i][1]);
                res[i] = integerIntegerEntry == null ? - 1 : integerIntegerEntry.getValue();
            }

            return res;
        }
    }
}
