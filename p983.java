public class p983 {

    /**
     * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。
     * 在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
     *
     * 火车票有 三种不同的销售方式 ：
     *
     * 一张 为期一天 的通行证售价为 costs[0] 美元；
     * 一张 为期七天 的通行证售价为 costs[1] 美元；
     * 一张 为期三十天 的通行证售价为 costs[2] 美元。
     * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
     *
     * 返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费 。
     */



    class Solution {
        public int mincostTickets(int[] days, int[] costs) {
            int n=days.length;
            int[] f=new int[n+1];
            int j=0;//如果上次买的七天票，是在哪里买的
            int k=0;//如果上次买的30天票，是在哪里买的
            for(int dayIndex = 0;dayIndex < n;dayIndex ++){
                int day=days[dayIndex];

                while(days[j]<=day-7){
                    j++;
                }//上次买七天票的位置

                while(days[k]<=day-30){
                    k++;
                }//上次买30天票的位置

                //最小的花钱数量，，三种选择
                f[dayIndex+1]=Math.min(f[dayIndex]+costs[0],Math.min(f[j]+costs[1],f[k]+costs[2]));
            }
            return f[n];
        }
    }

//    class Solution {
//
//        public int mincostTickets(int[] days, int[] costs) {
//
//            return dfs(days , 0 , costs , 0);
//        }
//
//        public int dfs(int[] days,int dayIndex , int[] cost , int lastEffectiveDay){
//
//
//            if(dayIndex == days.length){
//
//                return 0;//不用再买票了
//            }
//
//            if(days[dayIndex] <= lastEffectiveDay){
//
//                //这一天可以不买票
//                return 0 + dfs(days , dayIndex + 1 , cost , lastEffectiveDay);
//            }
//
//            //已经超过有效期了
//
//
//
//            //进行买几张票的选择
//            int cost1 = cost[0] + dfs(days , dayIndex + 1 , cost , days[dayIndex]);
//            int cost2 = cost[1] + dfs(days , dayIndex + 1 , cost , days[dayIndex] + 6);
//            int cost3 = cost[2] + dfs(days , dayIndex + 1 , cost , days[dayIndex] + 29);
//
//
//            return Math.min(Math.min(cost1 , cost2) , cost3);
//        }
//    }
}
