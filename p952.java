package from_900_to_1000;

public class p952 {

    /**
     * 给定一个由不同正整数的组成的非空数组 nums ，考虑下面的图：
     *
     * 有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记；
     * 只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。
     * 返回 图中最大连通组件的大小 。
     */


    public static void main(String[] args) {

        System.out.println(new Solution().largestComponentSize(new int[]{2,3,6,7,4,12,21,39}));
    }



    static
    class Solution {

        public static int MAXN = 1_00_001;
        public static boolean[] is = new boolean[MAXN];
        public static int[] prime = new int[MAXN];
        public static int realNum = 0;
        static {

            for(int i = 2 ; i < MAXN ; i ++){

                if(!is[i])
                    prime[realNum++] = i;

                for(int j = 0 ; j < realNum ; j ++){

                    if(i * prime[j] >= MAXN)
                        break;
                    is[i * prime[j]] = true;
                    if(i % prime[j] == 0)
                        break;
                }
            }//得到所有的质数
        }

        public static int[] symbol = new int[MAXN];
        public static int[] size = new int[MAXN];
        public static int[] nodeFirstHaveThisPrime = new int[MAXN];//第一个拥有i位置这个质数的是哪个位置的数


        public int largestComponentSize(int[] nums) {

            for(int i = 0 ; i < MAXN ; i ++){

                symbol[i] = i;//最开始每个集合的集合代表点都是自身
                size[i] = 1;
                nodeFirstHaveThisPrime[i] = -1;
            }


            for(int i = 0 ; i < nums.length ; i ++){

                int num = nums[i];

                //进行质因子分解
                for(int primeId = 0, p = prime[primeId] ; p * p <= num ; primeId++ , p = prime[primeId]){

                    //这个是它的质因子
                    if(num % p == 0){

                        //这个质数还没有被找到过
                        if(nodeFirstHaveThisPrime[p] == -1)
                            nodeFirstHaveThisPrime[p] = i;//这个数就是第一个
                        else
                            setSameSet(i , nodeFirstHaveThisPrime[p]);


                        while (num % p == 0)
                            num /= p;
                    }
                }

                //这个数剩下的本身就是一个质数
                if(num > 1){


                    if(nodeFirstHaveThisPrime[num] == -1)
                        nodeFirstHaveThisPrime[num] = i;//这个数就是第一个
                    else
                        setSameSet(i , nodeFirstHaveThisPrime[num]);
                }
            }

            int res = 0;
            for(int i = 0 ; i < nums.length ; i ++){

                if(symbol[i] == i)
                    res = Math.max(res , size[i]);
            }

            return res;
        }

        public int binaryFound(int p){

            int left = 0;
            int right = realNum - 1;
            while (left <= right){

                int mid = (left + right) >> 1;
                if(prime[mid] < p)
                    left = mid + 1;
                else if(p < prime[mid])
                    right = mid - 1;
                else
                    return mid;
            }

            return -1;
        }


        public void setSameSet(int a , int b){

            a = getSymbol(a);
            b = getSymbol(b);
            if(a == b)
                return;
            if(size[a] < size[b]){

                int t = a;
                a = b;
                b = t;
            }//保证a的大小一定大于b
            symbol[b] = a;
            size[a] += size[b];
        }

        public int getSymbol(int a){

            int cur = a;
            while(symbol[cur] != cur)
                cur = symbol[cur];

            while (a != cur){

                int temp = symbol[a];
                symbol[a] = cur;
                a = temp;
            }

            return cur;
        }
    }
}
