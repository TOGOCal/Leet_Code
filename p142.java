public class p142 {

    /**
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     */


    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public class Solution {


        public ListNode detectCycle(ListNode head) {

            ListNode fast = head;
            ListNode slow = head;

            while(fast != null) {

                fast = fast.next;
                if(fast!=null){

                    fast = fast.next;
                }else{

                    break;
                }

                slow = slow.next;


                if(fast == slow) break;

            }


            if(fast == null) return null;


            //这时刻两个相遇了
            fast = head;

            while(fast!=slow) {

                fast = fast.next;
                slow = slow.next;
            }

            return fast;

        }
    }


}
