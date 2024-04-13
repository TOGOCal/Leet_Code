public class p19 {
    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);

        //ListNode node = Solution.removeNthFromEnd(node1, 1);

    }

    public static class ListNode {
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

    public static class Solution {
        public static ListNode removeNthFromEnd(ListNode head, int n) {

            ListNode node = head;
            int num = 0;
            while (node != null) {

                node = node.next;
                num++;
            }

            node = head;

            n = num - n;

            if (n == 0) {

                return head.next;
            }

            for (int i = 0; i < n - 1; i++) {

                node = node.next;
            }

            ListNode node_ = node;
            node = node.next;
            node_.next = node.next;

            return head;
        }
    }
}
