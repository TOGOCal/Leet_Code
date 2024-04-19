import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class p2007 {

    public static void main(String[] args) {

        int arr[] = { 1, 3, 4, 2, 6, 8 };
        // Solution solution = new Solution();
        Solution2 solution2 = new Solution2();
        int arrq[] = solution2.findOriginalArray(arr);

        for (int i : arrq) {

            System.out.print(i);
        }
    }

    /**
     * 一个整数数组 original 可以转变成一个 双倍 数组 changed ，
     * 转变方式为将 original 中每个元素 值乘以 2 加入数组中，
     * 然后将所有元素 随机打乱 。
     *
     * 给你一个数组 changed ，
     * 如果 change 是 双倍 数组，那么请你返回 original数组，
     * 否则请返回空数组。original 的元素可以以 任意 顺序返回。
     */

    public static class Solution2 {
        public int[] findOriginalArray(int[] changed) {

            int empty[] = new int[0];// 用于空返回

            if (changed.length % 2 == 1) {

                return empty;
            }

            HashMap<Integer, Integer> map = new HashMap<>();
            ArrayList<Integer> index = new ArrayList<>();
            qiuckSort(changed, 0, changed.length - 1);

            int result[] = new int[changed.length / 2];
            int reslutIndex = 0;

            for (int i = 0; i < changed.length; i++) {

                if (!map.containsKey(changed[i])) {

                    map.put(changed[i], 1);
                    index.add(i);// 将第一个开始不一样的元素索引存下来
                } else {

                    map.put(changed[i], map.get(changed[i]) + 1);
                }
            } // 所有的 value-数量 都保存在了map中，所有的value都保存在了set中

            int size = index.size();
            for (int i = 0; i < size; i++) {

                int idx = changed[index.get(i)];

                if (idx == 0) {

                    if (map.get(0) % 2 == 1) {

                        return empty;
                    } // 零的数量是奇数肯定不对
                    continue;
                }

                if (map.get(idx) == 0) {

                    continue;
                } // 说明这个的数量已经全部被前面的抵消了

                // 以下的情况满足：未抵消数量不等于0
                if (!map.containsKey(idx << 1)) {

                    return empty;
                }

                // 以下情况满足：两倍点存在
                if (map.get(idx) > map.get(idx << 1)) {

                    return empty;
                }

                // 以下点满足，二倍点存在且数量小于等于该点
                int num = map.get(idx);
                for (int ii = 0; ii < num; ii++) {

                    result[reslutIndex++] = idx;
                }

                map.put(idx << 1, map.get(idx << 1) - map.get(idx));

            }

            return result;

        }

        public void qiuckSort(int arr[], int L, int R) {

            if (L >= R) {

                return;
            }

            int rand = (int) (Math.random() * (R - L + 1) + L);
            int sort = arr[rand];

            int p1 = L - 1;
            int p2 = R + 1;

            for (int i = L; i < p2; i++) {

                if (arr[i] < sort) {

                    int a = arr[i];
                    arr[i] = arr[p1 + 1];
                    arr[p1 + 1] = a;
                    p1++;
                } else if (arr[i] > sort) {

                    int a = arr[i];
                    arr[i] = arr[p2 - 1];
                    arr[p2 - 1] = a;
                    p2--;
                    i--;
                }
            }

            qiuckSort(arr, L, p1);
            qiuckSort(arr, p2, R);
        }
    }

    static class Solution {
        public int[] findOriginalArray(int[] changed) {

            if (changed == null || changed.length == 0 || changed.length % 2 == 1) {
                int empty[] = new int[0];
                return empty;
            }

            int arr[] = new int[changed.length / 2];

            for (int i = 0; i < changed.length; i++) {

                if (foundNode.containsKey(changed[i])) {

                    Node nowNode = foundNode.get(changed[i]);
                    nowNode.count++;// 多了一个相同数字
                    continue;
                }

                // 之前从未见过这个数字
                Node nowNode = new Node(changed[i]);// 哈希表中没有说明这个对象之前从未被创建
                foundNode.put(changed[i], nowNode);// 放入哈希表
                // 因为之前从来没有见过这个点
                // 1.有两倍于他的点

                if (foundNode.containsKey(changed[i] * 2)) {

                    nowNode.next = foundNode.get(changed[i] * 2);
                }

                // 2.有半倍于它的点
                if (foundNode.containsKey(changed[i] / 2) && (changed[i] / 2) * 2 == changed[i]) {

                    foundNode.get(changed[i] / 2).next = nowNode;
                }

            } // 完成图的创建

            if (foundNode.size() == 1 && changed[0] != 0) {

                int empty[] = new int[0];
                return empty;
            }

            qiuckSort(changed, 0, changed.length - 1);
            HashSet<Integer> set = new HashSet<>();
            // 准别开始复制到数组中
            int index = 0;
            for (int i = 0; i < changed.length; i++) {

                if (set.contains(changed[i])) {

                    continue;
                }

                set.add(changed[i]);

                if (changed[i] == 0) {

                    if (foundNode.get(changed[i]).count % 2 == 1) {
                        int empty[] = new int[0];
                        return empty;
                        // 0的两倍还是0，所以0应该是偶数个，如果是奇数个直接返回
                    }
                    continue;// 不用进行系欸下来的操作了
                }

                Node nowNode = foundNode.get(changed[i]);
                if (nowNode.count > 0) {

                    if ((nowNode.next == null && i != changed.length - 1) || nowNode.count > nowNode.next.count) {

                        int empty[] = new int[0];
                        return empty;
                        // 举例：两倍数组中不应该有多于2数量的1
                        // 因为每个1没有小节点了
                    } else {

                        if (!foundNode.containsKey(changed[i] * 2)) {

                            int empty[] = new int[0];
                            return empty;
                        }
                        foundNode.get(changed[i] * 2).count -= foundNode.get(changed[i]).count;// 抵消掉相应个数

                        for (int ii = 0; ii < foundNode.get(changed[i]).count; ii++) {

                            arr[index++] = changed[i];
                        }

                    }

                }

            }

            return arr;
        }

        public void qiuckSort(int arr[], int L, int R) {

            if (L >= R) {

                return;
            }

            int rand = (int) (Math.random() * (R - L + 1) + L);
            int sort = arr[rand];

            int p1 = L - 1;
            int p2 = R + 1;

            for (int i = L; i < p2; i++) {

                if (arr[i] < sort) {

                    int a = arr[i];
                    arr[i] = arr[p1 + 1];
                    arr[p1 + 1] = a;
                    p1++;
                } else if (arr[i] > sort) {

                    int a = arr[i];
                    arr[i] = arr[p2 - 1];
                    arr[p2 - 1] = a;
                    p2--;
                    i--;
                }
            }

            qiuckSort(arr, L, p1);
            qiuckSort(arr, p2, R);
        }

        HashMap<Integer, Node> foundNode = new HashMap<>();// 通过数值找节点

        class Node {
            int value;// 数值
            int count;// 有多少个
            Node next;// 值是该点两倍的点

            Node() {
                ;
            }

            public Node(int value) {
                this.value = value;
                count = 1;
                next = null;
            }
        }

    }

}
