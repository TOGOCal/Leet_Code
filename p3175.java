import java.io.PipedOutputStream;
import java.util.Arrays;

public class p3175 {

    /**
     * 有 n 位玩家在进行比赛，玩家编号依次为 0 到 n - 1 。
     *
     * 给你一个长度为 n 的整数数组 skills 和一个 正 整数 k ，其中 skills[i] 是第 i 位玩家的技能等级。skills 中所有整数 互不相同 。
     *
     * 所有玩家从编号 0 到 n - 1 排成一列。
     *
     * 比赛进行方式如下：
     *
     * 队列中最前面两名玩家进行一场比赛，技能等级 更高 的玩家胜出。
     * 比赛后，获胜者保持在队列的开头，而失败者排到队列的末尾。
     * 这个比赛的赢家是 第一位连续 赢下 k 场比赛的玩家。
     *
     * 请你返回这个比赛的赢家编号。
     */


    public static void main(String[] args) {

        int[] arr = new int[]{16 , 4 , 7 , 17};
        System.out.println(new Solution().findWinningPlayer(arr , 562084119));
    }


    static
    class Solution{

        public int findWinningPlayer(int[] skills, int k) {

            int p1 = 0;
            int p2 = 1;

            int times = 0;
            int maxIndex = 0;

            while(p1 < skills.length && p2 < skills.length){

                if(skills[p1] > skills[p2]) {

                    times++;
                    if(times == k)
                        return p1;
                }else{

                    p1 = p2;
                    times = 1;
                    if(times == k)
                        return p1;
                    maxIndex = p2;
                }

                p2++;
            }

            return maxIndex;
        }
    }


    static
    //方法1：直接模拟（边界处理的时候有些问题）
    class Solution1 {

        public static int MAXN = 100001;

        static class Arr{

            static int[] arr = new int[MAXN];
            static int[] times = new int[MAXN];
            static int begin = 0;
            static int end = MAXN - 1;
            static int nowTop = 0;

            static void init(int[] array){

                Arrays.fill(arr , 0);
                for(int i = 0 ; i < array.length ; i++)
                    arr[i] = i;

                Arrays.fill(times , 0);
                begin = 0;
                end = MAXN - 1;//最后一个有效位置在什么地方
                nowTop = array.length - 1;
            }

            static int checkAndAdd(int k , int[] array){



                //将最前面两个数字进行比较
                int one = array[arr[begin]];
                int two = array[arr[begin + 1]];

                //题目保证没有相同的数字
                if(one > two){//移动第二个数字

                    int possiblePosition = nowTop + 1;
                    if(possiblePosition > end){

                        possiblePosition = possiblePosition % MAXN;//放在第一个位置
                        end = possiblePosition;
                    }

                    //将第一个数字移动到第二个数字的地方
                    times[arr[begin]]++;
                    if(times[arr[begin]] == k)
                        return arr[begin];

                    int temp = arr[begin + 1];
                    arr[begin + 1] = arr[begin];
                    begin ++;//begin的位置提前了一个位置

                    arr[possiblePosition] = temp;//将第二个数移动到对应为hi
                    times[temp] = 0;//胜利数清零
                    nowTop = possiblePosition;

                }else{//移动第一个数字

                    int temp = arr[begin];
                    begin ++;
                    times[arr[begin]]++;
                    if(times[arr[begin]] == k)
                        return arr[begin];

                    int possiblePosition = nowTop + 1;
                    if(possiblePosition > end){

                        possiblePosition = possiblePosition % MAXN;//放在第一个位置
                        end = possiblePosition;
                    }

                    arr[possiblePosition] = temp;
                    times[temp] = 0;

                    nowTop = possiblePosition;
                }

                return -1;
            }
        }

        public int findWinningPlayer(int[] skills, int k) {

            if(k > skills.length){

                k = k % skills.length + skills.length;
            }

            Arr.init(skills);

            int a;
            while ((a = Arr.checkAndAdd(k , skills)) == -1){

            }

            return a;
        }


    }
}
