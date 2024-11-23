public class p328 {

    /**
     * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
     *
     * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
     *
     * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
     *
     * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     */


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        new Solution().oddEvenList(node);
    }

    static
    class Solution {
        public ListNode oddEvenList(ListNode head) {

            if(head == null)
                return null;

            ListNode[] nodes = new ListNode[2];
            nodes[0] = new ListNode();
            nodes[1] = new ListNode();

            ListNode oddRecord = nodes[0];
            ListNode ovenRecord = nodes[1];

            int index = 0;
            ListNode cur = head;
            while(cur != null){

                ListNode n = nodes[index % 2];
                n.next = cur;
                nodes[index % 2] = cur;
                index++;
                ListNode node = cur.next;
                cur.next = null;
                cur = node;
            }

            nodes[0].next = ovenRecord.next;

            return oddRecord.next;
        }
    }
}
