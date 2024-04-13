public class p11 {

    public static void main(String[] args) {

        // 1, 8, 6, 2, 5, 4, 8, 3, 7]
        int arr[] = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        System.out.println(Solution1.maxArea(arr));
    }

    public static class Solution1 {
        public static int maxArea(int[] height) {

            int length = height.length;

            Node nodes[] = new Node[length];

            for (int i = 0; i < height.length; i++) {

                nodes[i] = new Node(i + 1, height[i]);
            }

            quickSortHeight(nodes, 0, nodes.length - 1);// 高度从大到小排序

            int max = 0;

            for (int i = 0; i < nodes.length; i++) {

                max = Math.max(max, nodes[i].height * findMaxLength(nodes, i, nodes[i].x));
            }

            return max;

        }

        public static int findMaxLength(Node nodes[], int i, int x) {// 传入数组，终止条件，相对远点x坐标

            int max = 0;

            for (int ii = 0; ii < i; ii++) {

                max = Math.max(Math.abs(nodes[ii].x - x), max);
            }

            return max;
        }

        public static void quickSortHeight(Node nodes[], int L, int R) {

            if (L >= R) {

                return;
            }

            int p1 = L - 1;
            int p2 = R + 1;

            int valueHeight = nodes[(int) (Math.random() * (R - L + 1) + L)].height;

            for (int i = L; i < p2; i++) {

                if (nodes[i].height > valueHeight) {

                    Node node = nodes[i];
                    nodes[i] = nodes[p1 + 1];
                    nodes[p1 + 1] = node;
                    p1++;
                } else if (nodes[i].height < valueHeight) {

                    Node node = nodes[i];
                    nodes[i] = nodes[p2 - 1];
                    nodes[p2 - 1] = node;
                    p2--;
                    i--;
                }

            }

            quickSortHeight(nodes, L, p1);
            quickSortHeight(nodes, p2, R);
        }

        public static class Node {

            int x;
            int height;

            Node(int x, int height) {

                this.x = x;
                this.height = height;
            }
        }
    }

    public static class Solution {
        public static int maxArea(int[] height) {

            int pl = 0;
            int pr = height.length - 1;

            int max = 0;
            while (pl != pr) {

                if (height[pl] > height[pr]) {

                    // 长版向内移动则面积一定会变小，
                    // 所以每次移动短板
                    max = Math.max(max, (pr - pl) * (height[pr]));
                    pr--;
                } else {

                    max = Math.max(max, (pr - pl) * (height[pl]));
                    pl++;
                }
            }

            return max;
        }
    }
}