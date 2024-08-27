import java.util.HashMap;

public class p138 {

    /**
     * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
     *
     * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，
     * 其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，
     * 并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
     *
     * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
     *
     * 返回复制链表的头节点。
     *
     * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
     *
     * val：一个表示 Node.val 的整数。
     * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
     * 你的代码 只 接受原链表的头节点 head 作为传入参数。
     */


// Definition for a Node.
static class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

    public static void main(String[] args) {

        Node head = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        head.random = null;
        node1.random = head;
        node2.random = node4;
        node3.random = node2;
        node4.random = head;

        Solution solution = new Solution();
        Node node = solution.copyRandomList(head);



    }


    static
    class Solution {

        HashMap<Node , Node> oldNewMap;

        public Node copyRandomList(Node head) {

            if(head == null){

                return null;
            }

            oldNewMap = new HashMap<>();

            Node cur = head;

            Node newHead;

            newHead = new Node(head.val);
            oldNewMap.put(head , newHead);

            if(head.random != null){

                if(oldNewMap.containsKey(head.random)){

                    newHead.random = oldNewMap.get(head.random);
                }else{

                    newHead.random = new Node(head.random.val);
                    oldNewMap.put(head.random , newHead.random);
                }
            }

            Node newCur;
            newCur = newHead;

            //完成头节点构建
            while(cur.next != null){
                cur = cur.next;

                //之前创建过这个点了
                Node newNode;
                if(oldNewMap.containsKey(cur)){

                    newNode = oldNewMap.get(cur);
                }else{

                    newNode = new Node(cur.val);
                    oldNewMap.put(cur , newNode);
                }

                newCur.next = newNode;
                newCur = newNode;

                Node random = cur.random;
                if(random!=null){

                    if(oldNewMap.containsKey(random)){

                        newCur.random = oldNewMap.get(random);
                    }else{

                        newCur.random = new Node(random.val);
                        oldNewMap.put(random , newCur.random);
                    }
                }
            }


            return newHead;

        }
    }
}
