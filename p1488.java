import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class p1488 {

    /**
     * 你的国家有无数个湖泊，所有湖泊一开始都是空的。当第 n 个湖泊下雨前是空的，那么它就会装满水。如果第 n 个湖泊下雨前是 满的 ，这个湖泊会发生 洪水 。你的目标是避免任意一个湖泊发生洪水。
     *
     * 给你一个整数数组 rains ，其中：
     *
     * rains[i] > 0 表示第 i 天时，第 rains[i] 个湖泊会下雨。
     * rains[i] == 0 表示第 i 天没有湖泊会下雨，你可以选择 一个 湖泊并 抽干 这个湖泊的水。
     * 请返回一个数组 ans ，满足：
     *
     * ans.length == rains.length
     * 如果 rains[i] > 0 ，那么ans[i] == -1 。
     * 如果 rains[i] == 0 ，ans[i] 是你第 i 天选择抽干的湖泊。
     * 如果有多种可行解，请返回它们中的 任意一个 。如果没办法阻止洪水，请返回一个 空的数组 。
     *
     * 请注意，如果你选择抽干一个装满水的湖泊，它会变成一个空的湖泊。但如果你选择抽干一个空的湖泊，那么将无事发生。
     */


    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().avoidFlood(new int[]{1,0,2,0,3,0,2,0,0,0,1,2,3})));
    }


    class Solution2 {

        public static int MAXN = 1_00_000 + 1;
        //public static int[] record = new int[MAXN];
        //public static int realLength;
        //public static int[] isFull = new int[MAXN];//第几次操作将这个湖泊填满的
        //public static int isFullNum;

//        public static int[] canFunction = new int[MAXN];//能够进行操作的有哪些天
//        public static int canFunctionNum;

//        public static int[][] needFunction = new int[MAXN][3];
//        public static int needFunctionNum;

//        private int binaryFound(int n){
//
//            int left = 0;
//            int right = realLength - 1;
//            while (left <= right){
//
//                int mid = (left + right) >> 1;
//                if(record[mid] < n)
//                    left = mid + 1;
//                else if(record[mid] > n)
//                    right = mid - 1;
//                else
//                    return mid;
//            }
//
//            return -1;
//        }

        public int[] avoidFlood(int[] rains) {

            TreeSet<Integer> st = new TreeSet<Integer>();

            int l = rains.length;
            int[] ans = new int[l];
//            for(int i = 0 ; i < l ; i ++)
//                record[i] = rains[i];
//
//            Arrays.sort(record , 0 , l);
//
//            realLength = 0;
//            for(int i = 1 ; i < l ; i++){
//
//                if(record[realLength] != record[i])
//                    record[++realLength] = record[i];
//            }
//            realLength += 1;



            HashMap<Integer , Integer> map = new HashMap<>();
            //needFunctionNum = isFullNum = canFunctionNum = 0;
            //isFullNum = 0;
            //Arrays.fill(isFull , -1);
            for(int i = 0 ; i < l ; i ++){

                int v = rains[i];
                if(v == 0) {//这天不下雨，可以选择抽或者不抽
//                    if (isFullNum != 0)
                    if (!map.isEmpty())
                        //canFunction[canFunctionNum++] = i;//这天是可以操作的
                        st.add(i);
                }else{

                    ans[i] = -1;
                    //int index = binaryFound(v);//找到这是第几号湖泊
                    if(map.containsKey(v)){//这个湖泊是满的，需要找办法解决

//                        int[] t = needFunction[needFunctionNum++];
//                        t[0] = isFull[index];//需要在之前这天到现在这天的某天之内解决这个湖泊
//                        t[1] = i;//这天之内解决
//                        t[2] = index;//需要在第i天解决index号湖泊
//
//                        isFull[index] = i;
                        //Integer ceiling = st.ceiling(isFull[index]);
                        Integer ceiling = st.ceiling(map.get(v));
                        if(ceiling == null)
                            return new int[]{};

                        st.remove(ceiling);
                        ans[ceiling] = v;
                        //isFull[index] = i;
                        map.put(v , i);
                    }else{

//                        isFull[index] = i;
//                        isFullNum++;
                        map.put(v , i);
                    }
                }
            }

            //检查能都解决
//            int index = 0;
//            Arrays.sort(needFunction, 0, needFunctionNum, (o1, o2) -> o1[1] - o2[1]);
//            for(int i = 0 ; i < needFunctionNum ; i ++){
//
//                int[] temp = needFunction[i];
//
//                //找到第一个大于的
//                while(index < canFunctionNum && canFunction[index] < temp[0])
//                    index++;
//
//                //没有找到解决办法，返回空数组
//                if(index == canFunctionNum ||
//                        //不可能等于，因为那天下雨
//                        canFunction[index] > temp[1])
//                    return new int[]{};
//
//                //这一天，解决问题
//                ans[canFunction[index++]] = record[temp[2]];
//            }


            for(int i = 0 ; i < l ; i ++)
                if(ans[i] == 0)
                    ans[i] = 1;


            return ans;
        }
    }


    static
    class Solution {

        public static int MAXN = 1_00_000 + 1;
        public static int[] record = new int[MAXN];
        public static int realLength;
        public static int[] isFull = new int[MAXN];//第几次操作将这个湖泊填满的
        public static int isFullNum;

//        public static int[] canFunction = new int[MAXN];//能够进行操作的有哪些天
//        public static int canFunctionNum;

//        public static int[][] needFunction = new int[MAXN][3];
//        public static int needFunctionNum;

        private int binaryFound(int n){

            int left = 0;
            int right = realLength - 1;
            while (left <= right){

                int mid = (left + right) >> 1;
                if(record[mid] < n)
                    left = mid + 1;
                else if(record[mid] > n)
                    right = mid - 1;
                else
                    return mid;
            }

            return -1;
        }

        public int[] avoidFlood(int[] rains) {

            TreeSet<Integer> st = new TreeSet<Integer>();

            int l = rains.length;
            int[] ans = new int[l];
            for(int i = 0 ; i < l ; i ++)
                record[i] = rains[i];

            Arrays.sort(record , 0 , l);

            realLength = 0;
            for(int i = 1 ; i < l ; i++){

                if(record[realLength] != record[i])
                    record[++realLength] = record[i];
            }
            realLength += 1;



            //needFunctionNum = isFullNum = canFunctionNum = 0;
            isFullNum = 0;
            Arrays.fill(isFull , -1);
            for(int i = 0 ; i < l ; i ++){

                int v = rains[i];
                if(v == 0) {//这天不下雨，可以选择抽或者不抽
                    if (isFullNum != 0)
                        //canFunction[canFunctionNum++] = i;//这天是可以操作的
                        st.add(i);
                }else{

                    ans[i] = -1;
                    int index = binaryFound(v);//找到这是第几号湖泊
                    if(isFull[index] != -1){//这个湖泊是满的，需要找办法解决

//                        int[] t = needFunction[needFunctionNum++];
//                        t[0] = isFull[index];//需要在之前这天到现在这天的某天之内解决这个湖泊
//                        t[1] = i;//这天之内解决
//                        t[2] = index;//需要在第i天解决index号湖泊
//
//                        isFull[index] = i;
                        Integer ceiling = st.ceiling(isFull[index]);
                        if(ceiling == null)
                            return new int[]{};

                        st.remove(ceiling);
                        ans[ceiling] = v;
                        isFull[index] = i;
                    }else{

                        isFull[index] = i;
                        isFullNum++;
                    }
                }
            }

            //检查能都解决
//            int index = 0;
//            Arrays.sort(needFunction, 0, needFunctionNum, (o1, o2) -> o1[1] - o2[1]);
//            for(int i = 0 ; i < needFunctionNum ; i ++){
//
//                int[] temp = needFunction[i];
//
//                //找到第一个大于的
//                while(index < canFunctionNum && canFunction[index] < temp[0])
//                    index++;
//
//                //没有找到解决办法，返回空数组
//                if(index == canFunctionNum ||
//                        //不可能等于，因为那天下雨
//                        canFunction[index] > temp[1])
//                    return new int[]{};
//
//                //这一天，解决问题
//                ans[canFunction[index++]] = record[temp[2]];
//            }


            for(int i = 0 ; i < l ; i ++)
                if(ans[i] == 0)
                    ans[i] = 1;


            return ans;
        }
    }
}
