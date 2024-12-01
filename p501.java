package frim_500_to_600;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p501 {

    /**
     * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
     *
     * 如果树中有不止一个众数，可以按 任意顺序 返回。
     *
     * 假定 BST 满足如下定义：
     *
     * 结点左子树中所含节点的值 小于等于 当前节点的值
     * 结点右子树中所含节点的值 大于等于 当前节点的值
     * 左子树和右子树都是二叉搜索树
     */


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);

        System.out.println(Arrays.toString(new Solution().findMode(node)));
    }

    static
    class Solution {
        int base, count, maxCount;
        List<Integer> answer = new ArrayList<>();

        public int[] findMode(TreeNode root) {
            TreeNode cur = root, pre = null;
            while (cur != null) {
                if (cur.left == null) {
                    update(cur.val);
                    cur = cur.right;
                    continue;
                }
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    update(cur.val);
                    cur = cur.right;
                }
            }
            int[] mode = new int[answer.size()];
            for (int i = 0; i < answer.size(); ++i) {
                mode[i] = answer.get(i);
            }
            return mode;
        }

        public void update(int x) {
            if (x == base) {
                ++count;
            } else {
                count = 1;
                base = x;
            }
            if (count == maxCount) {
                answer.add(base);
            }
            if (count > maxCount) {
                maxCount = count;
                answer.clear();
                answer.add(base);
            }
        }
    }

}
