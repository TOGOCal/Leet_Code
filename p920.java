package from_900_to_1000;

public class p920 {

    /**
     * 你的音乐播放器里有 n 首不同的歌，在旅途中，你计划听 goal 首歌（不一定不同，即，允许歌曲重复）。你将会按如下规则创建播放列表：
     *
     * 每首歌 至少播放一次 。
     * 一首歌只有在其他 k 首歌播放完之后才能再次播放。
     * 给你 n、goal 和 k ，返回可以满足要求的播放列表的数量。由于答案可能非常大，请返回对 109 + 7 取余 的结果。
     */


    public static void main(String[] args) {

        System.out.println(new Solution().numMusicPlaylists(16, 19, 5));
    }


    static
    class Solution {

        public static int MAXN = 101;
        public static int mod = 1000000007;
        public static int[] arr = new int[MAXN];//用于计算阶乘取模
        public static int[] rra = new int[MAXN];//用于计算除法逆元
        static {

            arr[1] = 1;
            for(int i = 2 ; i < MAXN ; i ++)
                arr[i] = (int)(arr[i - 1] * (long)i % mod);
            rra[MAXN - 1] = power(arr[MAXN - 1], mod - 2);
            //开始递推
            //inv[i] = ((long)(i + 1) * inv[i + 1]) % mod
            for(int i = MAXN - 2 ; i >= 0 ; i --)
                rra[i] = (int)(
                        ((long)(i + 1) * rra[i + 1]) % mod
                );

        }

        //x的y次方
        public static int power(long x , int y){

            long res = 1;
            while (y > 0){

                if((y & 1) == 1)
                    res = (res * x) % mod;
                x = ((x * x) % mod);
                y >>= 1;
            }

            return (int)res;
        }

        //一共有num首歌，一共播放all首，不要求全部播完，但是一定要间隔k首
        public static int get(int num , int k , int all){

            //前k+1个进行排列，后all - k - 1个可以从num-k个任意选
            //所以结果为 Anum下k+1上 * (num - k)^ (all - k - 1）
            //return (int)((long)A(num , k + 1) * power(num - k , all - k - 1) % mod);
            return (int)(
                    (
                            ((long)arr[num] * rra[num - k] % mod)
                            * power(num - k , all - k)
                    ) % mod);
        }


        //m选n
        public static int C(int m , int n){

            return (int)(
                    (
                            (((long)arr[m] * rra[n]) % mod)
                    * rra[m- n]
            ) % mod
            );
        }

        public int numMusicPlaylists(int n, int goal, int k) {

            int without = get(n , k , goal);//没有限制的情况
            //减去少了一首的，加上少了两手的，减去少了三首的。。。
            for(int i = 1 ; i < n - k ; i++){
                //n - i > k
                //奇数
                int res = (int)((long)get(n - i , k , goal) * C(n , n - i) % mod);
                if((i & 1) == 1){

                    without = (
                            without + mod - res
                            ) % mod;
                }else{
                    without = (
                            without + res
                            ) % mod;
                }
            }

            return without;
        }
    }
}

