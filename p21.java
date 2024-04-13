public class p21 {
    public static void main(String[] args) {

        ListNode node1 = new ListNode(-9);
        ListNode node2 = new ListNode(3);
        node1.next = node2;

        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(7);
        node3.next = node4;

        System.out.println(Solution.mergeTwoLists(node1, node3));

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
        public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

            ListNode head = new ListNode();

            if (list1 == null) {

                return list2;
            } else if (list2 == null) {

                return list1;
            }

            ListNode node = head;
            while (list1 != null && list2 != null) {

                if (list1.val < list2.val) {

                    node.next = new ListNode(list1.val);
                    list1 = list1.next;
                    node = node.next;
                } else {

                    node.next = new ListNode(list2.val);
                    list2 = list2.next;
                    node = node.next;
                }
            }

            while (list1 == null && list2 != null) {

                node.next = new ListNode(list2.val);
                list2 = list2.next;
                node = node.next;
            }

            while (list2 == null && list1 != null) {

                node.next = new ListNode(list1.val);
                list1 = list1.next;
                node = node.next;
            }

            return head.next;
        }

        public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {

            if (list1 == null) {

                return list2;
            } else if (list2 == null) {

                return list1;
            } else if (list1.val < list2.val) {

                list1.next = mergeTwoLists2(list1.next, list2);
                return list1;
            } else {

                list2.next = mergeTwoLists2(list1, list2.next);
                return list2;
            }

        }
    }
}
