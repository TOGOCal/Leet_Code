package from_200_to_300;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class p225 {

    /**
     * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
     *
     * 实现 MyStack 类：
     *
     * void push(int x) 将元素 x 压入栈顶。
     * int pop() 移除并返回栈顶元素。
     * int top() 返回栈顶元素。
     * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
     */

    public static void main(String[] args) {

        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
    }


    static
    class MyStack {

        Queue<Integer>[] q;
        int which;

        public MyStack() {

            q = new Queue[2];
            q[0] = new LinkedList<>();
            q[1] = new LinkedList<>();
            which = 0;
        }

        public void push(int x) {

            int other = (which == 0) ? 1 : 0;
            q[which].add(x);
            while(!q[other].isEmpty())
                q[which].add(q[other].poll());

            which = other;
        }


        public int pop() {

            return q[which == 0 ? 1 : 0].poll();
        }

        public int top() {

            return q[which == 0 ? 1 : 0].peek();
        }

        public boolean empty() {

            return q[0].isEmpty() && q[1].isEmpty();
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
}
