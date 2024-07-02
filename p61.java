import java.util.List;

public class p61 {

    /**
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     */

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);

        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;

        Solution solution = new Solution();
        solution.rotateRight(node1, 2);
    }


     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

        static class Solution {
            public ListNode rotateRight(ListNode head, int k) {

                if(head == null || head.next == null || k == 0){

                    return null;
                }

                ListNode node = head;
                int n = 1;
                while(node.next != null){

                    node = node.next;
                    n++;
                }

                ListNode tail = node;

                int add = n - k % n;

                if(add == n){

                    return head;
                }

                tail.next = head;

                while(add-- > 0){

                    node = node.next;
                }

                ListNode res = node.next;

                node.next = null;

                return res;


            }

        }

//    static class Solution {
//        public ListNode rotateRight(ListNode head, int k) {
//
//            if(head == null || head.next == null || k == 0){
//
//                return head;
//            }
//
//
//            //反转链表
//            ListNode node = head;
//            ListNode pre = null;
//            ListNode next = head.next;
//            while(next != null){
//
//                node.next = pre;
//
//                pre = node;
//                node = next;
//                next = node.next;
//            }
//
//            node.next = pre;
//
//
//            ListNode newHead = node;//找到新头节点的位置
//
//            ListNode headRecord = node;//记录头节点
//            ListNode tailRecord = head;
//            //由于反转了链表
//
//
//            for(int i = 0; i < k; i++){
//
//                pre = newHead;
//                if(newHead.next != null){
//
//                    newHead = newHead.next;
//                }else{
//
//                    tailRecord = newHead;
//                    newHead = headRecord;
//                }
//            }
//
//
//            tailRecord.next = headRecord;
//            pre.next = null;
//
//            ListNode preRecord = pre;
//
//            node = newHead;
//            pre = null;
//            next = newHead.next;
//            while(next != null){
//
//                node.next = pre;
//
//                pre = node;
//                node = next;
//                next = node.next;
//            }
//
//
//            node.next = pre;
//            //反转回来
//
//            return preRecord;
//        }
//    }
}
