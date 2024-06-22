import java.util.List;

public class p92 {

    public static void main(String[] args) {

    }

    //给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。

     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {

            if(head == null){

                return null;
            }

            ListNode pre = null;

            ListNode cur = head;

            for(int i = 1; i < left; i++){

                pre = cur;
                cur = cur.next;
            }

            //找到了要反转的第一个节点以及反转前的节点

            //执行反转操作
            ListNode preHelp = null;
            ListNode recordBegin = cur;
            for(int i =0; i< right-left+1; i++){

                ListNode next = cur.next;
                cur.next = preHelp;
                preHelp = cur;
                cur = next;
            }

            //出循环后cur指向被旋转的最后一个节点的后一个
            //此时  pre ->   null <-recordBegin <- <- preHelp   cur ->
            recordBegin.next = cur;

            if(pre == null){

                head = preHelp;
            }else{

                pre.next = preHelp;
            }


            return head;
        }
    }
}
