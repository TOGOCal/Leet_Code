import java.util.Iterator;

public class p284 {

    /**
     * 请你在设计一个迭代器，在集成现有迭代器拥有的 hasNext 和 next 操作的基础上，还额外支持 peek 操作。
     *
     * 实现 PeekingIterator 类：
     *
     * PeekingIterator(Iterator<int> nums) 使用指定整数迭代器 nums 初始化迭代器。
     * int next() 返回数组中的下一个元素，并将指针移动到下个元素处。
     * bool hasNext() 如果数组中存在下一个元素，返回 true ；否则，返回 false 。
     * int peek() 返回数组中的下一个元素，但 不 移动指针。
     * 注意：每种语言可能有不同的构造函数和迭代器 Iterator，但均支持 int next() 和 boolean hasNext() 函数。
     */

    // Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

    class PeekingIterator implements Iterator<Integer> {

        Integer now;
        Iterator<Integer> iterator;

        public PeekingIterator(Iterator<Integer> iterator) {

            this.iterator = iterator;

            // initialize any member here.
            now = iterator.hasNext() ? iterator.next() : null;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {

            return now;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {

            Integer ret = now;

            now = iterator.hasNext() ? iterator.next() : null;
            return ret;
        }

        @Override
        public boolean hasNext() {

            return now != null;
        }
    }
}
