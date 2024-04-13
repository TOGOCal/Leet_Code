/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * 
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 * 
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 */

public class P2 {
    public static void main(String[] args) {

    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution2 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            ListNode l = new ListNode(0);
            ListNode top = l;

            int Cin = 0;

            int i = 0;
            while (l1 != null && l2 != null) {

                int a = l1.val + l2.val + Cin;
                Cin = 0;

                if (a >= 10) {

                    a -= 10;
                    Cin++;
                }

                // l.val = a;
                l.next = new ListNode(a);
                if (i == 0) {

                    top = top.next;
                    i++;
                }

                l = l.next;

                l1 = l1.next;
                l2 = l2.next;
            }

            while (l1 != null) {

                int a = l1.val + Cin;
                Cin = 0;

                if (a >= 10) {

                    a -= 10;
                    Cin++;
                }

                // l.val = a;
                l1 = l1.next;
                l.next = new ListNode(a);
                l = l.next;
            }

            while (l2 != null) {

                int a = l2.val + Cin;
                Cin = 0;

                if (a >= 10) {

                    a -= 10;
                    Cin++;
                }
                // l.val = a;
                l2 = l2.next;
                l.next = new ListNode(a);
                l = l.next;
            }

            if (Cin != 0) {

                l.next = new ListNode(Cin);
                Cin = 0;
            }

            return top;

        }

    }
}

// Definition for singly-linked list.
