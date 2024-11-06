import java.util.HashMap;
import java.util.HashSet;

public class p380 {

    /**
     * 实现RandomizedSet 类：
     *
     * RandomizedSet() 初始化 RandomizedSet 对象
     * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
     * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
     * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
     * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
     */

    public static void main(String[] args) {

        RandomizedSet set = new RandomizedSet();

//        System.out.println(set.remove(0));
//        System.out.println(set.remove(0));
        System.out.println(set.insert(0));
        System.out.println(set.remove(0));
        System.out.println(set.insert(0));
    }

    static
    class RandomizedSet {

        public static int MAXN = 200001;
        public static int[] arr = new int[MAXN];
        public static int length;
        public static HashMap<Integer , Integer> valueIndexMap = new HashMap<>();

        public RandomizedSet() {

            length = 0;
            valueIndexMap.clear();
        }

        public boolean insert(int val) {

            if(valueIndexMap.containsKey(val)){

                return false;
            }

            arr[length] = val;
            valueIndexMap.put(val , length);
            length ++;
            return true;
        }

        public boolean remove(int val) {

            if(!valueIndexMap.containsKey(val)){

                return false;
            }

            int index = valueIndexMap.get(val);
            valueIndexMap.remove(val);

            length--;
            if(index == length){

                return true;
            }

            arr[index] = arr[length];
            valueIndexMap.put(arr[index] , index);

            return true;
        }

        public int getRandom() {

            int index = (int)(Math.random() * length);
            return arr[index];
        }
    }

}
