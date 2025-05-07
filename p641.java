public class p641 {

    /**
     * 设计实现双端队列。
     *
     * 实现 MyCircularDeque 类:
     *
     * MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
     * boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
     * boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
     * boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
     * boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
     * int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
     * int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
     * boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false  。
     * boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。
     */

    class MyCircularDeque {

        //最开始从2000位置开始放入数据，
        public static int MAXN = 2000 + 1000 + 2000 + 1;
        public static int[] arr = new int[MAXN];
        public static int maxSize;
        public static int top,end;


        public MyCircularDeque(int k) {

            maxSize = k;
            top = end = 2000;
        }

        private int nowSize(){

            return end - top;
        }

        public boolean insertFront(int value) {

            if(nowSize() >= maxSize)
                return false;

            arr[--top] = value;

            return true;
        }

        public boolean insertLast(int value) {

            if(nowSize() >= maxSize)
                return false;

            arr[end++] = value;//end不放数，top放数
            return true;
        }

        public boolean deleteFront() {

            if(nowSize() <= 0)
                return false;

            top++;
            return true;
        }

        public boolean deleteLast() {


            if(nowSize() <= 0)
                return false;

            end--;
            return true;
        }

        public int getFront() {

            if(nowSize() == 0)
                return -1;
            return arr[top];
        }

        public int getRear() {

            if(nowSize() == 0)
                return -1;
            return arr[end - 1];
        }

        public boolean isEmpty() {

            return nowSize() == 0;
        }

        public boolean isFull() {

            return nowSize() == maxSize;
        }
    }
}
