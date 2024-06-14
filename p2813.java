import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class p2813 {

    /**
     * 给你一个长度为 n 的二维整数数组 items 和一个整数 k 。
     *
     * items[i] = [profiti, categoryi]，其中 profiti 和 categoryi 分别表示第 i 个项目的利润和类别。
     *
     * 现定义 items 的 子序列 的 优雅度 可以用 total_profit + distinct_categories2 计算，其中 total_profit 是子序列中所有项目的利润总和，distinct_categories 是所选子序列所含的所有类别中不同类别的数量。
     *
     * 你的任务是从 items 所有长度为 k 的子序列中，找出 最大优雅度 。
     *
     * 用整数形式表示并返回 items 中所有长度恰好为 k 的子序列的最大优雅度。
     *
     * 注意：数组的子序列是经由原数组删除一些元素（可能不删除）而产生的新数组，且删除不改变其余元素相对顺序。
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int[][] nums = new int[s.nextInt()][2];

        for(int i = 0; i < nums.length; i++){

            nums[i][0] = s.nextInt();
            nums[i][1] = s.nextInt();
        }

        int k = s.nextInt();


        System.out.println(new Solution().findMaximumElegance(nums, k));

        s.close();
    }

    static class Solution {

        public long findMaximumElegance(int[][] items, int k) {

            //线选择前k个元素
            HashMap<Integer, Integer> categoryAmount = new HashMap<>();
            long sum = 0;

            int i;
            int index = -1;//储存最后一个种类重复的位置

            quickSort(items, 0, items.length - 1);

            for(i = 0; i < k; i++){

                sum += items[i][0];
                if(categoryAmount.containsKey(items[i][1])){

                    categoryAmount.put(items[i][1] , categoryAmount.get(items[i][1]) + 1);
                    index = i;
                }else{

                    categoryAmount.put(items[i][1] , 1);
                }
            }


            int h = items.length;
            int size = categoryAmount.size();

            sum += (long) size *size;

            if(index == -1){
                //说明其中所有都只出现了一次
                return sum;
            }

            List<Long> sumList = new ArrayList<>();//储存所有有可能的情况
            sumList.add(sum);

            while(i < h){//向后寻找可能使得变大的情况
                /**
                 * 情况：如果后面的数字的类别在前面出现过，则不选择这个点，因为sum的值不可能变大（类型不变，数量变小
                 * 情况：如果这个数字的类别在前面没有出现过，
                 * 目的：只能使得类型的数量增加,
                 * 所以k中最小且类型重复的那个
                 *
                 */

                if(!categoryAmount.containsKey(items[i][1])){

                    int[] temp = items[index];//最小的重复项
                    categoryAmount.put(items[index][1] , categoryAmount.get(items[index][1]) - 1);
                    categoryAmount.put(items[i][1] , 1);

                    sum += (items[i][0] - temp[0]);
                    sum += (categoryAmount.size()* 2L - 1);
                    sumList.add(sum);

                    items[index] = items[i];//进行交换
                    items[i] = temp;


                    index--;
                    for(;index >=0;index--){

                        if(categoryAmount.get(items[index][1]) > 1){
                            break;
                        }
                    }

                    if(index == -1){
                        //直接触发结算
                        return myMax(sumList);
                    }


                }

                i++;

            }

            return myMax(sumList);
        }


        public long myMax(List<Long> list){

            long max= 0;

            for(Long i: list){

                if(i > max){

                    max = i;
                }
            }

            return max;
        }

        public void quickSort(int[][] nums , int l , int r){
            //从大到小排序
            if(l >= r){

                return ;
            }

            int p1 = l-1;
            int p2 = r+1;

            int rand = nums[ (int)(Math.random()*(r-l+1)) + l ][0];

            for(int i = l ; i < p2 ; i++){

                if(nums[i][0] > rand){

                    swap(nums , i , ++p1);
                }else if(nums[i][0] < rand){

                    swap(nums , i , --p2);
                    i--;
                }
            }

            quickSort(nums , l , p1);
            quickSort(nums , p2 , r);

        }


        public void swap(int[][] nums , int i , int j){

            int[] temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }


    }


//    static class Solution {
//
//        int sum = 0;
//        HashMap<Integer, Integer> category = new HashMap<>();
//        public long findMaximumElegance(int[][] items, int k) {
//
//            int h = items.length;
//
//
//
//            q1uckSort(items , 0 , h-1);
//
////            for(int i = 0; i < h; i++){
////
////                sum += items[i][0];
////
////                category.put(items[i][1], category.getOrDefault(items[i][1], 0) + 1);
////            }
//            //为了加速，将这一步进行的操作放在构建链表的时候操作
//
//            MyLinkedList linkedList = new MyLinkedList(items);
//
//            int s = category.size();
//            sum += s*s; //算出total_profit + distinct_categories2
//
//            //贪心：每次删除代价最小的组合（但是我需要遍历才能决定？
//            /**
//             * 规则构建：
//             * 1.每次优先删除profit较小的（已排序，直接遍历即可
//             * 2.当种类数不发生变化的时候，优先删除profit较小的
//             * 3.当遍历时前几个发现一定会出现种类数变化，
//             *      则一直遍历直到找到种类不变的位置
//             *          情况一：找不到这样的点
//             *              则可以直到，所有的点都只有一个种类了，则再删除时直接进行删除就可以了
//             *              达成这一目的可以使用boolean类型变量做锁  记录值： key
//             *          情况二：找到了这样的点
//             *              则比较删除这个点的代价和删除第一个点的代价的区别
//             *              可以每次记录这样的点下次直接从这个点开始向后找，加速  记录点：notOnePosition
//             * 考虑用链表（好像要方便一点
//             */
//
//            boolean key = false;
//            Node notOnePosition = null;
//
//            while(h > k){
//
//                Node cur = linkedList.head;
//
//                int amount = category.get(cur.category);
//                if(key || amount >1 ){//如果所有点都只是1了，那也直接删除就行了
//                    //直接删除这个点就可以了，这就是最小代价
//                    linkedList.delete(cur);
//                    sum -= cur.profit;
//
//                    if(key){
//
//                        sum -= (2 * category.size() -1);
//                        category.remove(cur.category);
//                    }else{
//
//                        category.put(cur.category, amount-1);
//                    }
//
//                }else{
//
//                    if(notOnePosition == null){
//
//                        notOnePosition = cur;
//                    }//这个不出意外也只会进一次
//
//                    Node foundNode = notOnePosition;//从这个点开始遍历
//                    while(foundNode !=null ){
//                        //当cur没有遍历完所有点
//
//                        if(category.get(foundNode.category) > 1){
//
//                            notOnePosition = foundNode;//记录这个点的信息，方便下次调用
//                            break;//发现第一份不是唯一类型的点，就跳出
//                        }
//
//                        foundNode = foundNode.next;
//                    }
//
//                    if(foundNode == null){
//                        //说明所有点都只有一个类型了，则直接从小到达删除就行了
//                        key = true;
//                        //则需要进行重新对链表进行考虑
//                        //continue掉就行了
//                        continue;
//                    }
//
//                    //找到了第一个类型不是1的点
//                    int size = category.size();
//                    int cost1 = cur.profit + (2 * size -1);
//                    int cost2 = foundNode.profit;
//
//                    if(cost1 < cost2){
//                        //代表删除第一个点代价小
//                        linkedList.delete(cur);
//                        //凡是进入这里就代表第一个人点肯定只出现了一次，所以直接从表里面删除就行
//                        category.remove(cur.category);
//                        sum -= cost1;
//                    }else{
//
//                        linkedList.delete(foundNode);
//                        category.put(foundNode.category, category.get(foundNode.category) - 1);
//                        sum -= cost2;
//                        //如果删除这个点的话，说明notOnePosition的位置无效了，需要向下移动一个
//                        notOnePosition = notOnePosition.next;
//                    }
//
//
//                }
//
//                h--;
//            }
//
//            return sum;
//        }
//
//        public class MyLinkedList{
//            Node head;
//
//            MyLinkedList(int[][] nums) {
//
//                head = new Node(nums[0][0] , nums[0][1]);
//
//                Node cur = head;
//
//                sum += nums[0][0];
//
//                category.put(nums[0][1], 1);
//
//                for(int i = 1; i < nums.length; i++){
//
//                    sum += nums[i][0];
//
//                    category.put(nums[i][1], category.getOrDefault(nums[i][1], 0) + 1);
//
//                    Node newNode = new Node(nums[i][0] , nums[i][1]);
//                    cur.next = newNode;
//                    newNode.pre = cur;
//                    cur = cur.next;
//                }
//            }
//
//            public void delete(Node node){
//
//                if(node.pre == null){
//                    //说明这个点是头节点
//                    head = node.next;
//                    head.pre = null;
//                }else {
//
//                    Node pre = node.pre;
//                    Node next = node.next;
//
//                    pre.next = next;
//
//                    if(next != null){
//
//                        next.pre = pre;
//                    }
//                }
//            }
//
//        }
//
//        public static class Node{
//
//            int profit;;
//            int category;
//
//            Node next;
//            Node pre;
//
//            Node(int profit , int category){
//
//                this.profit = profit;
//                this.category = category;
//            }
//        }
//
//
//        public void q1uckSort(int[][] nums , int l , int r){
//
//            if(l >= r){
//
//                return ;
//            }
//
//            int p1 = l-1;
//            int p2 = r+1;
//
//            int rand = nums[ (int)(Math.random()*(r-l+1)) + l ][0];
//
//            for(int i = l ; i < p2 ; i++){
//
//                if(nums[i][0] < rand){
//
//                    swap(nums , i , ++p1);
//                }else if(nums[i][0] > rand){
//
//                    swap(nums , i , --p2);
//                    i--;
//                }
//            }
//
//            q1uckSort(nums , l , p1);
//            q1uckSort(nums , p2 , r);
//
//        }
//
//
//        public void swap(int[][] nums , int i , int j){
//
//            int[] temp = nums[i];
//            nums[i] = nums[j];
//            nums[j] = temp;
//        }
//
//
//    }



}
