import java.util.*;

public class p401 {

    /**
     * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
     *
     * 例如，下面的二进制手表读取 "4:51" 。
     */


    public static void main(String[] args) {

//        String[] my = new String[]{"0:03","0:05","0:06","0:09","0:10","0:12","0:17","0:18","0:20","0:24","0:33","0:34","0:36","0:40","0:48","3:00","5:00","6:00","9:00","10:00","1:01","1:01","1:01","1:01","1:01","1:01","2:02","2:02","2:02","2:02","2:02","2:02","4:04","4:04","4:04","4:04","4:04","4:04","8:08","8:08","8:08","8:08","8:08","8:08"};
//        String[] res = new String[]{"0:03","0:05","0:06","0:09","0:10","0:12","0:17","0:18","0:20","0:24","0:33","0:34","0:36","0:40","0:48","1:01","1:02","1:04","1:08","1:16","1:32","2:01","2:02","2:04","2:08","2:16","2:32","3:00","4:01","4:02","4:04","4:08","4:16","4:32","5:00","6:00","8:01","8:02","8:04","8:08","8:16","8:32","9:00","10:00"};
//
//        HashSet<String> r = new HashSet<>(List.of(res));
//
//        for(String s : my)
//            if(!r.contains(s))
//                System.out.println(s);
//
//        r.clear();
//        r.addAll(List.of(my));
//        for(String s : res)
//            if(!r.contains(s))
//                System.out.println(s);

        System.out.println(new Solution().readBinaryWatch(7));
    }


    static
    class Solution {

        public static int[] hour = {8, 4 , 2 , 1};
        public static int[] minute = {32 , 16 , 8 , 4 , 2 , 1};
//        public static int[] possibleHourWithOneDecide = new int[]{8 , 4, 2, 1};
//        public static int[] possibleHourWithTwoDecide;
//        public static int[] possibleHourWithThreeDecide;
        public static int[][] possibleHourDecide = new int[5][];
        public static int[][] possibleMinuteDecide = new int[7][];
        public static HashSet<Integer> res;

        static{

            possibleHourDecide[0] = new int[]{0};
            possibleHourDecide[1] = new int[]{1 , 2, 4 ,8};

            decide(hour , 2 , 11);
            possibleHourDecide[2] = new int[res.size()];
            int[] now = possibleHourDecide[2];
            int index = 0;
            for(int n : res)
                now[index++] = n;
            Arrays.sort(now);

            decide(hour , 3 , 11);
            possibleHourDecide[3] = new int[res.size()];
            now = possibleHourDecide[3];
            index = 0;
            for(int n : res)
                now[index++] = n;
            Arrays.sort(now);

            possibleMinuteDecide[0] = new int[]{0};
            possibleMinuteDecide[1] = new int[]{1 , 2 , 4 , 8 , 16 , 32};

//            decide(minute , 2 , 59);
//            possibleMinuteDecide[2] = new int[res.size()];
//            now = possibleMinuteDecide[2];
//            for(int i = 0 ; i < res.size() ; i++)
//                now[i] = res.get(i);
//
            for(int i = 2 ; i <= 5 ; i++){

                decide(minute , i , 59);
                possibleMinuteDecide[i] = new int[res.size()];
                now = possibleMinuteDecide[i];
                index = 0;
                for(int n : res)
                    now[index ++] = n;
                Arrays.sort(now);
            }
        }

        public static void decide(int[] arr , int num , int limit){

            res = new HashSet<>();
            recursion(arr , 0 , num , 0 , limit);

        }

        public static void recursion(int[] arr , int index , int lastNum , int nowSum , int limit){

            if(lastNum == 0)
                if(nowSum <= limit)
                    res.add(nowSum);

            if(index == arr.length)
                return;

            //选择这个位置的数
            recursion(arr , index + 1 , lastNum - 1 , nowSum + arr[index] , limit);
            //不选择这个数
            recursion(arr , index + 1 , lastNum , nowSum , limit);
        }


        public static List<String> result;
        public List<String> readBinaryWatch(int turnedOn) {
            result = new ArrayList<>();//分为三种情况：所有都在时，所有都在分，一半在时间，一半在空间

            if(turnedOn > 8)
                return result;
            if(turnedOn == 0){

                result.add("0:00");
                return result;
            }


            if(turnedOn <= 5)
                addMinute(turnedOn);
            if(turnedOn <= 3)//可以全选时间
                addHour(turnedOn);


            for(int hourDecideNum = 1 ; hourDecideNum <=3 ; hourDecideNum ++){

                int minuteDecideNum = turnedOn - hourDecideNum;
                if(minuteDecideNum <= 0 || minuteDecideNum > 5)
                    continue;

                add(hourDecideNum , minuteDecideNum);
            }

            return result;
        }

        public void add(int hourNum , int minuteNum){

            int[] hourDecide = possibleHourDecide[hourNum];
            int[] minuteDecide = possibleMinuteDecide[minuteNum];

            for(int i = 0 ; i < hourDecide.length ; i++){

                int hour = hourDecide[i];
                for(int j = 0 ; j < minuteDecide.length ; j++){

                    String get = toString(hour , minuteDecide[j]);
                    if(get != null)
                        result.add(get);
                }
            }
        }

        public void addHour(int n){

            int[] hourDecide = possibleHourDecide[n];
            for(int i = 0 ; i < hourDecide.length ; i++){

                String get = toString(hourDecide[i] , 0);
                if(get != null)
                    result.add(get);
            }
        }

        public void addMinute(int n){

            int[] minuteDecide = possibleMinuteDecide[n];
            for(int i = 0 ; i < minuteDecide.length ; i ++){

                String get = toString(0 , minuteDecide[i]);
                if(get != null)
                    result.add(get);
            }
        }

        public static String symbol = ":";

        public String toString(int hour , int minute){

            if(hour > 11 || minute > 59)
                return null;

            StringBuilder sb = new StringBuilder();
            sb.append(hour).append(symbol);

            String value = String.valueOf(minute);
            if(value.length() == 1)
                sb.append(0);

            sb.append(minute);


            return sb.toString();
        }
    }
}
