import java.util.Map;
import java.util.TreeMap;

public class p147 {

    /**
     * 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
     *
     * 插入排序 算法的步骤:
     *
     * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
     * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
     * 重复直到所有输入数据插入完为止。
     * 下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。
     * 每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。
     *
     * 对链表进行插入排序。
     */


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {

        ListNode node = new ListNode(4);
        node.next = new ListNode(2);
        node.next.next = new ListNode(1);
        node.next.next.next = new ListNode(3);

        Solution solution = new Solution();
        ListNode listNode = solution.insertionSortList(node);
    }


    static
    class Solution {

        TreeMap<Integer,ListNode> smallerMaxMap;
        ListNode newHead;
        int max;
        ListNode tail;

        public ListNode insertionSortList(ListNode head) {
            smallerMaxMap = new TreeMap<>();

            if(head == null || head.next == null){

                return head;
            }



            newHead = head;
            tail = head;
            max = head.val;
            smallerMaxMap.put(head.val, head);//放入第一个节点


            ListNode next = head.next;
            head.next = null;
            dfs(next);

            return newHead;
        }

        public void dfs(ListNode node){

            if(node == null){

                return;
            }

            ListNode next = node.next;
            node.next = null;

            Map.Entry<Integer, ListNode> integerListNodeEntry = smallerMaxMap.floorEntry(node.val);

            //最小的
            if(integerListNodeEntry == null){

                node.next = newHead;
                newHead = node;
            }else{

                //插入到位置上
                ListNode pre = integerListNodeEntry.getValue();

                ListNode help = pre.next;

                pre.next = node;
                node.next = help;
            }


            if(node.val > max){

                tail = node;
            }

            smallerMaxMap.put(node.val, node);

            dfs(next);
        }
    }

}
