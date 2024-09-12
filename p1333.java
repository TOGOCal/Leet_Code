import java.util.*;

public class p1333 {

    /**
     * 给你一个餐馆信息数组 restaurants，其中  restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei]。
     * 你必须使用以下三个过滤器来过滤这些餐馆信息。
     *
     * 其中素食者友好过滤器 veganFriendly 的值可以为 true 或者 false，如果为 true 就意味着你应该只包括 veganFriendlyi 为 true 的餐馆，
     * 为 false 则意味着可以包括任何餐馆。此外，我们还有最大价格 maxPrice 和最大距离 maxDistance 两个过滤器，
     * 它们分别考虑餐厅的价格因素和距离因素的最大值。
     *
     * 过滤后返回餐馆的 id，按照 rating 从高到低排序。如果 rating 相同，那么按 id 从高到低排序。简单起见，
     * veganFriendlyi 和 veganFriendly 为 true 时取值为 1，为 false 时，取值为 0 。
     */

    public static void main(String[] args) {

        //[[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]]

        int[][] restaurants = {{1,4,1,40,10},{2,8,0,50,5},{3,8,1,30,4},{4,10,0,10,3},{5,1,1,15,1}};

        Solution solution = new Solution();

        System.out.println(solution.filterRestaurants(restaurants, 1, 50, 10));
    }



    static
    class Solution {
        public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
            int n = restaurants.length;
            List<int[]> suitable = new ArrayList<int[]>();
            for (int i = 0; i < n; i++) {
                if (restaurants[i][3] <= maxPrice && restaurants[i][4] <= maxDistance && !(veganFriendly == 1 && restaurants[i][2] == 0)) {
                    suitable.add(restaurants[i]);
                }
            }
            suitable.sort((a, b) -> {
                if (a[1] != b[1]) {
                    return b[1] - a[1];
                } else {
                    return b[0] - a[0];
                }
            });
            List<Integer> res = new ArrayList<>();

            for (int[] v : suitable) {
                res.add(v[0]);
            }
            return res;
        }
    }





//    class Solution {
//
//        //0      1          2              3         4
//        //idi, ratingi, veganFriendlyi, pricei, distancei
//        public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
//
//            Node[] priceNode = new Node[restaurants.length];
//            for(int i = 0; i < restaurants.length; i++){
//
//                priceNode[i] = new Node(restaurants[i][0], restaurants[i][3] , i);
//            }
//
//
//            Arrays.sort(priceNode , new MyComparator());//完成排序
//
//            List<Node> rateNode = new ArrayList<>();
//
//            //vegan 是1 ，只能包括1 vegan 是0 可以包含0 1 所以 restaurant[i] >= vegan
//            for(Node node : priceNode){
//
//                if(node.val > maxPrice){
//
//                    break;
//                }
//
//                //判断是否满足素食要求
//                boolean key1 = veganFriendly != 1 || restaurants[node.index][2] == 1;
//                boolean key2 = restaurants[node.index][4] <= maxDistance;
//
//                if(key1 && key2){
//
//                    Node newNode = new Node(node.id, restaurants[node.index][1] ,0);
//                    rateNode.add(newNode);
//                }
//            }
//
//
//            rateNode.sort(new MyRateComparator());
//
//            List<Integer> ans = new ArrayList<>();
//
//            for(Node node : rateNode){
//
//                ans.add(node.id);
//            }
//
//
//            return ans;
//
//
//        }
//
//        class MyRateComparator implements Comparator<Node>{
//
//            @Override
//            public int compare(Node o1, Node o2){
//
//                if(o1.val == o2.val){
//
//                    return o2.id - o1.id;
//
//                }
//
//                return o2.val - o1.val;
//
//            }
//
//        }
//
//        class MyComparator implements Comparator<Node>{
//
//            @Override
//            public int compare(Node o1, Node o2){
//
//                return o1.val - o2.val;
//            }
//
//        }
//
//        class Node{
//
//            int id;
//            int val;
//
//            int index;
//
//            Node(int id, int val , int index){
//
//                this.id = id;
//                this.val = val;
//
//                this.index = index;
//            }
//
//        }
//
//
//
//
//
//    }
}
